package com.sakha.crawl.ura;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawelUsingWebClient {
	public static final String url="https://www.tutorialspoint.com/tutorialslibrary.htm"; 

	public static void main(String[] args) {
		System.out.println("manin metheod start..");
		webCrawl();
		System.out.println("main metheod end..");
	}
	public static WebClient getWebClientObject() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
	

		try {
			
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return webClient;
		
	}
	
	public static void webCrawl() {
		HtmlPage htmlPage = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			
			htmlPage =getHtmlPageFromLink(url);
			
			if (htmlPage != null) {
				list = getTutorialsLibrary(htmlPage);
				System.out.println("List of industures = " + list);
			}
			
			/*
			 * if (!list.isEmpty()) { getCompanyUrlFromIndustry(list); }
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("End:  ");
		
	}
	public static HtmlPage getHtmlPageFromLink(String linkToHarvest) {

		WebClient webClient = null;
		HtmlPage htmlPage = null;
		
		try {
			
			System.out.println("linkToHarvest = " + linkToHarvest);
			
			webClient = getWebClientObject();
			htmlPage = webClient.getPage(linkToHarvest);
			
		} catch (Exception e) {
			
			htmlPage = null;
			e.printStackTrace();
		}
		
		webClient.close();
		
		return htmlPage;
	}
	public static List<Map<String, Object>> getTutorialsLibrary(HtmlPage htmlPage) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			
			HtmlElement htmlElement = (HtmlElement) htmlPage.getFormByName("mui-content-sub");
			List<HtmlElement> libraries = (List<HtmlElement>) htmlElement.getElementsByAttribute("div","class","featured-box");
			
			for(HtmlElement lib:libraries) {
				
				List<HtmlElement> head = (List<HtmlElement>) lib.getElementsByTagName("h4");
				
				for(HtmlElement mainhead: head ) {
					System.out.println(mainhead.asText());
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list ;
	}
	}

