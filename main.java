package jsoupTutta3;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.apache.commons.io.FileUtils;
import org.jsoup.*;

import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;


public class main {
	public static ArrayList<String> outputDownloadableList = new ArrayList<String>();
	public static ArrayList<String> outputNOTDownloadableList = new ArrayList<String>();
	public static ArrayList<String> outputREJECTEDDownloadableList = new ArrayList<String>();
	public static ArrayList<String> output_TO_BE_DownloadableList = new ArrayList<String>();


	public static void main(String[] args) {
		
//		Begin this program by running the function seed()
		outputDownloadableList = getURLsFromFile("Acceptable");
		outputNOTDownloadableList = getURLsFromFile("Retry");
		outputREJECTEDDownloadableList = getURLsFromFile("Rejectable");
		output_TO_BE_DownloadableList = getURLsFromFile("alrightRoundTwo");
		
		System.out.println("Number of albums accepted:" + outputDownloadableList.size() );
		System.out.println("Number of albums retriable:" + outputNOTDownloadableList.size() );
		System.out.println("Number of albums rejected:" + outputREJECTEDDownloadableList.size() );
		System.out.println("Number of albums to be:" + output_TO_BE_DownloadableList.size() );
		
		int GrandTotal = outputDownloadableList.size() + outputNOTDownloadableList.size() + outputREJECTEDDownloadableList.size() + output_TO_BE_DownloadableList.size();
		System.out.println("Total number of URLs found:" + GrandTotal);
		if(GrandTotal == 0){
			seed("http://imgsrc.ru/main/search.php?str=horse&nopass=on&cat=");
			downloadListToFile();
		}
		
//		output_TO_BE_DownloadableList = getURLsFromFile();
//		System.out.println(output_TO_BE_DownloadableList.get(0));
//		while(output_TO_BE_DownloadableList.size() >  0) {
//			getImageURLFromURL(output_TO_BE_DownloadableList.get(0));
//			downloadListToFile();
//			output_TO_BE_DownloadableList.remove(0);			
//		}


//		Print not downloaded URLs to file
//		downloadListToFile();
		
//		Make a new search with a URL like 
//		http://imgsrc.ru/main/search.php?str=boy&nopass=on&cat=
//		Into the file retry2
//		expander();
		
	}
	
	public static void seed(String SeedString) {
		try {
		Document doc = null;
		Document doc2 = null;
		doc = Jsoup.connect(SeedString).get();
			for (Element thumb : doc.select("a")) {
	    		String image = thumb.attr("href");
	    		if(image.indexOf("&page=") > 0){   
	    		System.out.println(image);
	    		doc2 = Jsoup.connect("http://imgsrc.ru" + image).get();
	    			for (Element thumb2 : doc2.select("a")) {
	    				String image2 = thumb2.attr("href");
	    				if(image2.indexOf("?ad=") > 0){   
	    					System.out.println(image2);
	    					outputDownloadableList.add(image2);
	    				}}
	    		}	 
	    		if(image.indexOf("?ad=") > 0){   
		    		System.out.println(image);
		    		outputDownloadableList.add(image + "&pwd=");
		    		}	    		
			}
		}
		catch(IOException e) {
			System.out.println("This is not a valid search URL:" + SeedString);
		}
		
	
		
		
	}
	
	public static void downloadListToFile() {
//		Print lists to files
		System.out.println("Number of albums accepted:" + outputDownloadableList.size() );
		System.out.println("Number of albums retriable:" + outputNOTDownloadableList.size() );
		System.out.println("Number of albums rejected:" + outputREJECTEDDownloadableList.size() );
		System.out.println("Number of albums to be:" + output_TO_BE_DownloadableList.size() );
		
		try {
		PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\pc\\git\\Acceptable"));
		int i = 0;
		while (outputDownloadableList.size() > i) {	

			String randomStrings = outputDownloadableList.get(i).toString();				   
			pw.println(randomStrings);	
//			outputDownloadableList.remove(0);
			i++;
		} 
		pw.close();
		} catch(FileNotFoundException e) {
			System.out.println("Cannot sleep right now");
		}
		
		try {
			int i = 0;
			PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\pc\\git\\Rejectable"));
			while (outputREJECTEDDownloadableList.size() > i) {	

				String randomStrings = outputREJECTEDDownloadableList.get(i).toString();				   
				pw.println(randomStrings);	
//				outputREJECTEDDownloadableList.remove(0);
				i++;
			} 
			pw.close();
			} catch(FileNotFoundException e) {
				System.out.println("Cannot sleep right now");
			}
		
		try {
			int i = 0;
			PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\pc\\git\\Retry"));
			while (outputNOTDownloadableList.size() > i) {	

				String randomStrings = outputNOTDownloadableList.get(i).toString();				   
				pw.println(randomStrings);	
//				outputNOTDownloadableList.remove(0);
				i++;
			} 
			pw.close();
			} catch(FileNotFoundException e) {
				System.out.println("Cannot sleep right now");
			}
		
		try {
			int i = 0;
			PrintWriter pw = new PrintWriter(new FileOutputStream("C:\\Users\\pc\\git\\alrightRoundTwo"));
			while (output_TO_BE_DownloadableList.size() > i) {	

				String randomStrings = output_TO_BE_DownloadableList.get(i).toString();				   
				pw.println(randomStrings);	
//				outputNOTDownloadableList.remove(0);
				i++;
			} 
			pw.close();
			} catch(FileNotFoundException e) {
				System.out.println("Cannot sleep right now");
			}
	}
		
	public static void expander() {
		Document doc = null;
		int indexNominus = 0;
		Integer maxCounterAlbumPages;

//		Variabler utilized
		ArrayList<String> list = new ArrayList<String>();
		List<Integer> numberofAlbumPagesList = new ArrayList<Integer>();	 
		
		
		
		try {
		Scanner s = new Scanner(new File("C:\\Users\\pc\\git\\retry2"));

		while (s.hasNextLine()){
			list.add(s.nextLine());
		}
		s.close();
		System.out.println("Size of list:" + list.size());
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
	
		int i = 0;
		System.out.println("Number i:" + i);
		System.out.println("list.size():" + list.size());
		while (list.size() > i) {	
			System.out.println("Page number " + i);
			try {
			doc = Jsoup.connect(list.get(i)).get(); }
			catch(IOException e) {
//				a DOWNLOADABLE PAGE WAS not found.
				System.out.println("file NOT found: " + list.get(i));
				outputREJECTEDDownloadableList.add(list.get(i));
				numberofAlbumPagesList.clear();
				i++;
				continue;
			}
			
//			Is the page downloadable?
			for (Element thumb : doc.select("a")) {
				String urlExtender = thumb.attr("href");
				if(urlExtender.indexOf("&skp=") > 0){    		   		
					String urlstrings = urlExtender.toString();
					Pattern p = Pattern.compile("\\&skp=(.*?)\\&pwd=");
					Matcher m = p.matcher(urlstrings);    				

					if(m.find()) {   
//						A downloadable page was found						
						String totalPageString;
						totalPageString = m.group();    					
						Scanner in = new Scanner(totalPageString).useDelimiter("[^0-9]+");
						int integer = in.nextInt();
						numberofAlbumPagesList.add(integer);
						
					} 
				} 		 
		    } 
			if(numberofAlbumPagesList.size()>0) {
			maxCounterAlbumPages = Collections.max(numberofAlbumPagesList);
			} else{
				maxCounterAlbumPages = 0;
			}
			System.out.println("file found" + maxCounterAlbumPages);
			if (maxCounterAlbumPages > 0) {
//				A downloadable page was found
				indexNominus = 0; 
			    	while (indexNominus <= maxCounterAlbumPages) {
			    		
			    		outputDownloadableList.add(list.get(i) + "&skp=" + indexNominus);
			    		indexNominus = indexNominus + 12;		
			    	} 
			    	numberofAlbumPagesList.clear();				 			
			} else {
				outputNOTDownloadableList.add(list.get(i));
				numberofAlbumPagesList.clear();
			}
			
			i++;	
		
		}
			
		downloadListToFile();

	}

	public static ArrayList<String> getURLsFromFile(String FileString) {
//		Variabler utilized
		ArrayList<String> list = new ArrayList<String>();
		
		try {
		Scanner s = new Scanner(new File("C:\\Users\\pc\\git\\" + FileString));

		while (s.hasNextLine()){
			list.add(s.nextLine());
		}
		s.close();
		System.out.println("Size of list:" + list.size());
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
			
 	System.out.println("getURLsfromFile: DONE");
 	return list;
	}
	
	public static void downloadImage(String urlString) {
		// Albumname
		Pattern p = Pattern.compile("\\/[a-zA-Z]\\/(.*?)\\/");
		Matcher m = p.matcher(urlString);    						
		
		// Filename
		Pattern q = Pattern.compile("imgsrc\\.ru_(.*)");
		Matcher n = q.matcher(urlString);
	
		String PATH = "C:\\Users\\pc\\git\\images\\";
		if (m.find()) {
			if (n.find()) {
				System.out.println(PATH + m.group(1) + "\\" + n.group(1));
		}
	}
/*		try {   
            URL url = new URL(urlString);           
            InputStream ins = url.openStream();
            OutputStream ous = new FileOutputStream(PATH + m.group(1) + "\\" + n.group(1));
            final byte[] b = new byte[2048];
            int length;

                while ((length = ins.read(b)) != -1) {
                       ous.write(b, 0, length);
                 }

                   ins.close();
                   ous.close();
            
    
            
        } catch (IOException e) {
		}
*/		try {
		    File file = new File(PATH + m.group(1) + "\\" + n.group(1));
		    URL url = new URL(urlString);
		    FileUtils.copyURLToFile(url, file);

		}catch(MalformedURLException e) {
			
			System.out.println("Something is wrong with file download " + urlString);
		}catch(IOException ex) {
			System.out.println("Something is wrong with file download " + urlString);
		}catch(IllegalStateException exc) {
			System.out.println("Exception in thread \\main\\ java.lang.IllegalStateException: No match found: " + urlString);
		}
	}
	
	public static void getImageURLFromURL(String url) {
		try {
		boolean downloadable = false;
		Document doc = null;
		doc = Jsoup.connect(url).get();
			for (Element thumb : doc.select("img")) {
	    		String image = thumb.attr("class");
	    		if (image.equals("big")) {
	    			image = thumb.attr("src");
	        		if(image.length() > 0){
	        			downloadImage(image);
	        			downloadable = true;
	        		}
	    		}
	        }

	    	for (Element thumb : doc.select("img")) {
	    		String image = thumb.attr("data-src");
	    		if(image.length() > 0){
	    			downloadImage(image);
	    			downloadable = true;
	    		}
	        }
	    if(downloadable) {
	    	System.out.println("An Image was downloadable at:" + url);
	    	outputDownloadableList.add(url);
	    }else {
	    	outputNOTDownloadableList.add(url);
	    }
		} 
		catch(IOException e) {
		System.out.println("An Image was not downloadable at:" + url);
		outputNOTDownloadableList.add(url);
		try {
				Thread.sleep(600000);              		    
			} catch(InterruptedException ex) {
				System.out.println("Cannot sleep right now");
			} 
		}
		
	}
	
}	
