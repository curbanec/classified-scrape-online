package com.crawler;

import org.springframework.stereotype.Component;
// TODO just default to amazon crawler for the time being 
@Component
public class CrawlerServiceFactory {
	
	public Crawler retrieveCrawler(String typeOf){
		
		if (typeOf.equals("craigsList")){
			Crawler crawler = (Crawler) new CraigslistCrawler();
			return crawler;
		}else{
			Crawler crawler = (Crawler) new AmazonCrawler();
			return crawler;
		}
	}
}


