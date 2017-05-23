package com.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class CraigslistCrawler implements Crawler {

	WebClient client = new WebClient(BrowserVersion.CHROME);

	boolean enoughPages;

	int totalMaxDepth;

	Response status;

	public void execute(String region, String customSearchQuery, String pagesToQuery, boolean maxDepth) {
		try {
			runPrimary(region, customSearchQuery, pagesToQuery, maxDepth);
			if (enoughPages) {
				status = Response.status(200).build();
								
				if (maxDepth){
					pagesToQuery= Integer.toString(totalMaxDepth);
					}

				for (int i = 1; i < Integer.valueOf(pagesToQuery); i++) {
					run(region, customSearchQuery, String.valueOf(i));
				}
				
			} else {
				status = Response.status(500).build();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void execute(List<String> regions, String customSearchQuery, String pagesToQuery, boolean maxDepth) {

		long startTime = System.nanoTime();
		
		for (String region : regions) {
			execute(region, customSearchQuery, pagesToQuery, maxDepth);
		}
		long endTime = System.nanoTime();
		System.out.println("Total execution time: " + (endTime-startTime) + "ms"); 
	}

	@SuppressWarnings("unchecked")
	public void runPrimary(String region, String customSearchQuery, String pagesToQuery, boolean maxDepth) throws IOException {
		FileWriter fileWriter = new FileWriter("craigsfile.txt", true);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		try {
			String searchUrl = "https://" 
					+ region + ".craigslist.org/search/sss?sort=rel&query="
					+ URLEncoder.encode(customSearchQuery, "UTF-8");

			HtmlPage page = client.getPage(searchUrl);

			enoughPages = validate(page, pagesToQuery);

			List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page.getByXPath("//*[@id='sortable-results']/ul/li");
			if (sortableResultsUnorderedList.isEmpty()) {
				System.out.println("No items found !");
			} else if (enoughPages) {
				for (HtmlListItem item : sortableResultsUnorderedList) {
					HtmlAnchor description = (HtmlAnchor) item.getFirstByXPath(".//p/a");
					HtmlAnchor price = (HtmlAnchor) item.getFirstByXPath(".//a");
					
					/*System.out.println(description.asText());
					System.out.println(price.asText());
					*/
					fileWriter.write(description.asText());
					fileWriter.write(" at price: ");
					fileWriter.write(price.asText());
					fileWriter.write(System.lineSeparator());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void run(String region, String customSearchQuery, String pageToQuery) throws IOException {
		FileWriter fileWriter = new FileWriter("craigsfile.txt", true);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {
			String searchUrl = "https://" + region + ".craigslist.org/search/sss?sort=rel&s=" + pageToQuery
					+ "00&query=" + URLEncoder.encode(customSearchQuery, "UTF-8");

			HtmlPage page = client.getPage(searchUrl);
			List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page
					.getByXPath("//*[@id='sortable-results']/ul/li");
			if (sortableResultsUnorderedList.isEmpty()) {
				System.out.println("No items found !");
			} else {
				for (HtmlListItem item : sortableResultsUnorderedList) {
					HtmlAnchor description = (HtmlAnchor) item.getFirstByXPath(".//p/a");
					HtmlAnchor price = (HtmlAnchor) item.getFirstByXPath(".//a");
					
					/*System.out.println(description.asText());
					System.out.println(price.asText());
					*/
					fileWriter.write(description.asText());
					fileWriter.write(" at price: ");
					fileWriter.write(price.asText());
					fileWriter.write(System.lineSeparator());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
	}

	private boolean validate(HtmlPage page, String pagesToQuery) {
		HtmlElement availablePages = (HtmlElement) page.getFirstByXPath("//*[@id='searchform']/div[5]/div[3]/span[2]/span[3]/span[2]");
		int numPages = Integer.valueOf(availablePages.asText().replaceAll("of ", "")) / 100;
		totalMaxDepth = numPages; 
		if (numPages >= Integer.valueOf(pagesToQuery)) {
			return true;
		}
		return false;
	}

	public Response getStatus() {
		return status;
	}
}
