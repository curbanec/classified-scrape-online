package com.app;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import org.springframework.stereotype.Component;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class CraigsListCrawlerClass {
	
	// String defaultSearchQuery = "Speakers" ;
	WebClient client = new WebClient(BrowserVersion.CHROME); 
	@SuppressWarnings("unchecked")
	public void run(String customSearchQuery) throws IOException{
		FileWriter fileWriter = new FileWriter("craigsfile.txt");	
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {  
		  String searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(customSearchQuery, "UTF-8");
		  HtmlPage page = client.getPage(searchUrl);
		  
		  List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page.getByXPath("//*[@id='sortable-results']/ul/li");
		  if(sortableResultsUnorderedList.isEmpty()){  
		  		System.out.println("No items found !");
		  	}else{
		  		for(HtmlListItem item : sortableResultsUnorderedList){
		  			
		  			HtmlAnchor description = (HtmlAnchor)item.getFirstByXPath(".//p/a");
		  			System.out.println(description.asText());
		  			fileWriter.write(description.asText());
		  			HtmlAnchor price = (HtmlAnchor)item.getFirstByXPath(".//a");
		  			System.out.println(price.asText());
		  			fileWriter.write(price.asText());
		  		}
		  	}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			fileWriter.close();
			}
		}
	
	@SuppressWarnings("unchecked")
	public void run(String customSearchQuery, String pageToQuery) throws IOException{
		FileWriter fileWriter = new FileWriter("craigsfile.txt");
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {  
		  String searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&s=" + 
				  			  pageToQuery + 
				  			 "00&query=" + 
				  			 URLEncoder.encode(customSearchQuery, "UTF-8");
		  
		  HtmlPage page = client.getPage(searchUrl);
		  
		  List<HtmlListItem> sortableResultsUnorderedList = (List<HtmlListItem>) page.getByXPath("//*[@id='sortable-results']/ul/li");
		  if(sortableResultsUnorderedList.isEmpty()){  
		  		System.out.println("No items found !");
		  	}else{
		  		for(HtmlListItem item : sortableResultsUnorderedList){
		  			
		  			HtmlAnchor description = (HtmlAnchor)item.getFirstByXPath(".//p/a");
		  			System.out.println(description.asText());
		  			fileWriter.write(description.asText());
		  			HtmlAnchor price = (HtmlAnchor)item.getFirstByXPath(".//a");
		  			System.out.println(price.asText());
		  			fileWriter.write(price.asText());
		  		}
		  	}
		}catch(Exception e){
		  e.printStackTrace();
		}finally{
			fileWriter.close();
		}
	}	
}


