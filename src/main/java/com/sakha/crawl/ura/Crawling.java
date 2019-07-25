package com.sakha.crawl.ura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	List<String> name =new ArrayList<String>();
	//List<ArrayList<Map<String,String>>> nameUrl = new ArrayList<ArrayList<Map<String,String>>>();
		
	
    public static void main( String[] args )
    {
      processPage("https://www.tutorialspoint.com/tutorialslibrary.htm");
    }
    public static void processPage(String URL) {
    	try {
            Document dc=Jsoup.connect(URL).get();
            Elements heading =dc.getElementsByTag("h4");
            
            Map<String, Object> headMap = new HashMap<String, Object>();
            
            for(Element head:heading) {
            	
            	System.out.println(head.text());
                headMap.put("name", head.text());
               
                List<HashMap<String,Object>> listOfHead = new ArrayList<HashMap<String,Object>>();
                Map<String, Object> secHeadMap =new HashMap<String, Object>();
                Elements subhead =dc.getElementsByTag("li");
                Elements h1 =subhead.select("a[title]");
          
                for(Element subhed: h1 ) {
                    
                	secHeadMap.put("Title", subhed.text());
                	secHeadMap.put("URL", subhed.attr("href"));
                	System.out.println("       sub head:"+subhed.text()+"       sub URL:"+subhed.attr("href"));
                    if(subhed.text().startsWith("Learn XStream")) {
                    	break;
                    }
                
                }
            	break;
            }
            
            
           
           
            
		} catch (Exception e) {
	
		}
    	
    }
}
