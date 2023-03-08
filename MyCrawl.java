import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.regex.Pattern;

import com.opencsv.CSVWriter;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.net.URL;
import java.net.URLConnection;



public class MyCrawl extends WebCrawler {
	
	//private final static Pattern DOC_PATTERN = Pattern.compile(".*(\\.(// |pdf|html|HTML|doc" + "|bmp|gif|jpe?g|png|tiff?))$");
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	
	@Override
	 public boolean shouldVisit(Page referringPage, WebURL url) {
	 String href = url.getURL().toLowerCase();
	 //String[] typesOfpages = {"text/html", "application/pdf", "application/doc", "image/gif", "image/jpeg", "image/png", "image/bmp", "image/tiff"};
	 String typeoffile = referringPage.getContentType();

	 

	 return  !FILTERS.matcher(href).matches() && href.startsWith("https://www.latimes.com/");
	 
	 
 

 }
	
	
	
	@Override
	 public void visit(Page page) {
		 String url = page.getWebURL().getURL();
		 
		 int code = page.getStatusCode();
		 
		 String typeoffile = page.getContentType();
		 String[] tpfilesplit = typeoffile.split(";");
		 
		 System.out.println("URL: " + url);
		 int siz = 0; 
		 
		 siz = page.getContentData().length;
		
		 String sizz = String.valueOf(siz);
		
		 String linkssizestring = "0";
		 
		 if (page.getParseData() instanceof HtmlParseData) {
		 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		 
		
		 
		  
		 String text = htmlParseData.getText();
		 String html = htmlParseData.getHtml();
		 
		 Set<WebURL> links = htmlParseData.getOutgoingUrls();
		 
		 
		 linkssizestring = String.valueOf(links.size());	
		 
		 
		 
		 for(WebURL link: links) {
			 
			 if(link.getURL().toLowerCase().startsWith("https://www.latimes.com/")) {
				 
				 File file3 = new File("/Users/gauravmakasare/data/urls_latimes.csv");
				 try {
				        // create FileWriter object with file as parameter
				        FileWriter outputfile = new FileWriter(file3, true);
				  
				        // create CSVWriter object filewriter object as parameter
				        CSVWriter writer = new CSVWriter(outputfile);
				  
				  
				        // add data to csv
				        String[] data1 = { link.getURL().toString(), "OK"};
				        writer.writeNext(data1);
				        
				  
				        // closing writer connection
				        writer.close();
				    }
				    catch (IOException e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				    }
				 
				 
				 
			 }else {
				 File file3 = new File("/Users/gauravmakasare/data/urls_latimes.csv");
				 try {
				        // create FileWriter object with file as parameter
				        FileWriter outputfile = new FileWriter(file3, true);
				  
				        // create CSVWriter object filewriter object as parameter
				        CSVWriter writer = new CSVWriter(outputfile);
				  
				  
				        // add data to csv
				        String[] data1 = { link.getURL().toString(), "N_OK"};
				        writer.writeNext(data1);
				        
				  
				        // closing writer connection
				        writer.close();
				    }
				    catch (IOException e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				    }
			 }
			 
		 }
		 
		 System.out.println("Text length: " + text.length());
		 System.out.println("Html length: " + html.length());
		 System.out.println("Number of outgoing links: " + links.size());
		 
		 
		 System.out.println("Status: " + code);
		 String c=String.valueOf(code);
		 
		 	 
		 
		}
		 
		 
		 String[] typesOfpages = {"text/html", "application/pdf", "application/doc", "image/gif", "image/jpeg", "image/png", "image/bmp", "image/tiff"};

		 System.out.println(page.getContentType());
		 for(int i=0; i<typesOfpages.length; i++) {
			 if (page.getContentType().contains(typesOfpages[i])) {
				 
				 File file2 = new File("/Users/gauravmakasare/data/visit_latimes.csv");
				    try {
				        // create FileWriter object with file as parameter
				        FileWriter outputfile = new FileWriter(file2, true);
				  
				        // create CSVWriter object filewriter object as parameter
				        CSVWriter writer = new CSVWriter(outputfile);
				  
				  
				        // add data to csv
				        String[] data1 = { url, tpfilesplit[0], sizz, linkssizestring};
				        
				        writer.writeNext(data1);
				        
				  
				        // closing writer connection
				        writer.close();
				    }
				    catch (IOException e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				    }
				 
			 }
		 }
		 
		 
		 
	 
	}
	
	
	@Override
	protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
		
		
			File file1 = new File("/Users/gauravmakasare/data/fetch_latimes.csv");
		    try {
		        // create FileWriter object with file as parameter
		        FileWriter outputfile = new FileWriter(file1, true);
		  
		        // create CSVWriter object filewriter object as parameter
		        CSVWriter writer = new CSVWriter(outputfile);
		  
		  
		        // add data to csv
		        String[] data1 = { String.valueOf(webUrl), String.valueOf(statusCode) };
		        writer.writeNext(data1);
		        
		  
		        // closing writer connection
		        writer.close();
		    }
		    catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		 
		    
		
	}
	
	
	
}


