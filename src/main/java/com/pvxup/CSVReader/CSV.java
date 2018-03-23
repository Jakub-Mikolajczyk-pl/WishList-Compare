package com.pvxup.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class CSV {

	String csvFile = "";
	
	public CSV(String csvFile) {
		this.csvFile = csvFile;
	}
	
	public void CSVReading(Map<Integer, String> hm) {
		 //String csvFile = "goodreads_library_export.csv";       
	        String cvsSplitBy = ","; // separator

	        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
	        	
	        	reader.readLine(); // This will read the first line
	        	String line = null; 
	        	
	            while ((line = reader.readLine()) != null) { // loop starts from 2nd line
	            
	                String[] author = line.split(cvsSplitBy);
	                author[3] = author[3].replace("\"", "");
	                if (hm.containsValue(author[3])) {
	                	System.out.println(author[3] + " is on the wishlist");
	                }
	                
	               // System.out.println("Nazwisko: "+author[3]);

	     
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
    public static void main(String[] args) {

    }

}

