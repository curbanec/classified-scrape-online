package com.crawler;

import java.net.URLEncoder;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ThreadSafeAlert {
	
	@SuppressWarnings({ "unchecked", "resource" })
	public void runPrimary(String region, String customSearchQuery)  {
		// FileWriter fileWriter = new FileWriter("craigsfile.txt", true);
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		try{
			
			String searchUrl = "https://" + "chicago" + ".craigslist.org/search/" + region + "/sss?sort=rel&query=" + URLEncoder.encode(customSearchQuery, "UTF-8");

			HtmlPage page = client.getPage(searchUrl);

			boolean enoughPages = true; // hack TODO fix

			List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page.getByXPath("//*[@id='sortable-results']/ul/li");
			if (sortableResultsUnorderedList.isEmpty()) {
				System.out.println("No items found !");
			} else if (enoughPages) {
				for (HtmlListItem item : sortableResultsUnorderedList) {
					HtmlAnchor description = (HtmlAnchor) item.getFirstByXPath(".//p/a");
					HtmlAnchor price = (HtmlAnchor) item.getFirstByXPath(".//a");
					
					System.out.println(description.asText());
					System.out.println(price.asText());
					
					/*fileWriter.write(description.asText());
					fileWriter.write(" at price: ");
					fileWriter.write(price.asText());
					fileWriter.write(System.lineSeparator());*/

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
