import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String crawlStorageFolder = "/Users/gauravmakasare/data/crawl";
		 int numberOfCrawlers = 7;
		 CrawlConfig config = new CrawlConfig();
		 config.setCrawlStorageFolder(crawlStorageFolder);
		 
		 config.setPolitenessDelay(800);
		 config.setMaxDepthOfCrawling(16);
		 config.setMaxPagesToFetch(20000);
		 config.setIncludeBinaryContentInCrawling(true);
		 config.setResumableCrawling(false);
		 /*
		 * Instantiate the controller for this crawl.
		 */
		 PageFetcher pageFetcher = new PageFetcher(config);
		 RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		 RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		 CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		 /*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these page2
		 */
		 
		 
		 controller.addSeed("https://www.latimes.com/");
		 
		 
		 File file1 = new File("/Users/gauravmakasare/data/fetch_latimes.csv");
		 try {
		        // create FileWriter object with file as parameter
		        FileWriter outputfile = new FileWriter(file1, true);
		  
		        // create CSVWriter object filewriter object as parameter
		        CSVWriter writer = new CSVWriter(outputfile);
		  
		        // adding header to csv
//		        String[] header = { "URL", "OK or Not OK"};
		        String[] header = { "URL", "Status Code"};
		        writer.writeNext(header);
		        // closing writer connection
		        writer.close();
		  
		    }
		    catch (IOException e) {
		        // TODO Auto-generated catch block
//		        e.printStackTrace();
		    }
		 
		 
		 File file2 = new File("/Users/gauravmakasare/data/visit_latimes.csv");
		 try {
		        // create FileWriter object with file as parameter
		        FileWriter outputfile = new FileWriter(file2, true);
		  
		        // create CSVWriter object filewriter object as parameter
		        CSVWriter writer = new CSVWriter(outputfile);
		  
		        // adding header to csv
//		        String[] header = { "URL", "OK or Not OK"};
		        String[] header = { "URL", "Type", "Size in Bytes", "No. of Outgoing Links"};
		        writer.writeNext(header);
		        // closing writer connection
		        writer.close();
		  
		    }
		    catch (IOException e) {
		        // TODO Auto-generated catch block
//		        e.printStackTrace();
		    }
		 
		 
		 
		 File file3 = new File("/Users/gauravmakasare/data/urls_latimes.csv");
		 try {
		        // create FileWriter object with file as parameter
		        FileWriter outputfile = new FileWriter(file3, true);
		  
		        // create CSVWriter object filewriter object as parameter
		        CSVWriter writer = new CSVWriter(outputfile);
		  
		        // adding header to csv
//		        String[] header = { "URL", "OK or Not OK"};
		        String[] header = { "URL", "OK or NOT OK"};
		        writer.writeNext(header);
		        // closing writer connection
		        writer.close();
		  
		    }
		    catch (IOException e) {
		        // TODO Auto-generated catch block
//		        e.printStackTrace();
		    }
		 controller.start(MyCrawl.class, numberOfCrawlers);

	}

}
