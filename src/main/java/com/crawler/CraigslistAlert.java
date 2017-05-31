package com.crawler;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import com.app.Runner;
import com.email.EmailService;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CraigslistAlert {
	
	@SuppressWarnings({ "unchecked", "resource" })
	public void runPrimary(String region, String customSearchQuery, String queryId, String submissionTime)  {
		
		DateFormat dateFormatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm" );
		
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		try{
			
			String searchUrl = "https://" + "chicago" + ".craigslist.org/search/" + region + "/sss?sort=rel&query=" + URLEncoder.encode(customSearchQuery, "UTF-8");

			HtmlPage page = client.getPage(searchUrl);

			List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page.getByXPath("//*[@id='sortable-results']/ul/li");
			if (sortableResultsUnorderedList.isEmpty()) {
				System.out.println("No items found !");
			} else {
				for (HtmlListItem item : sortableResultsUnorderedList) {
					
					String pid = item.getAttribute("data-pid");
				
					HtmlElement postingDateElement = (HtmlElement) item.getFirstByXPath(".//p/time");
					String postingDate = postingDateElement.getAttribute("datetime");
					
					HtmlAnchor description = (HtmlAnchor) item.getFirstByXPath(".//p/a");
					
					HtmlElement price = (HtmlElement) item.getFirstByXPath(".//span[2]/span");

					java.util.Date submissionTimeDate = dateFormatter.parse(submissionTime);
					java.util.Date postingTimeDate = dateFormatter.parse(postingDate);
					
					// String a = dateFormatter.format ( submissionTimeDate );
					// String b = dateFormatter.format ( postingTimeDate );
	
					if(postingTimeDate.after(submissionTimeDate) && !inDatabase(queryId, pid)){
						
						System.out.println("Sending Email");
						
						/*TODO  use the Spring @Configurable annotation along with AspectJ compile-time weaving
						 * @Autowired service is null because of the wat that a new CraigslistAlert is being created in new thread
						 * Spring IOC doesnt know about it and doesnt Autowire
						 * https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null
						 * */
						EmailService emailService = new EmailService();
						emailService.sendEmail(searchUrl, customSearchQuery);
						
						System.out.println("Adding to Database");
						System.out.println(description.asText());
						System.out.println(price.asText());
						System.out.println(postingDate);
						System.out.println(pid);
						Runner.AlertsDatabase.put(queryId + pid, description.asText() + " " + price.asText() + " " + postingDate);
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean inDatabase(String queryId, String pid){
		
		Set<String> keyEntries = (Set<String>) Runner.AlertsDatabase.keySet();
		
		return prune(keyEntries, queryId, pid);
	}
	
	private boolean prune(Set<String> keyEntries, String queryId, String pid){
		
		for (String key : keyEntries){
			if (key.equals(queryId + pid)){
				return true;
			}
		}
		return false;
	}
}
