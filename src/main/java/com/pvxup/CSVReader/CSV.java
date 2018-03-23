package com.pvxup.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CSV {

    public static void main(String[] args) {

        String csvFile = "goodreads_library_export.csv";       
        String cvsSplitBy = ","; // separator

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
        	
        	reader.readLine(); // This will read the first line
        	String line = null; 
        	
            while ((line = reader.readLine()) != null) { // loop starts from 2nd line
            
                String[] author = line.split(cvsSplitBy);
                author[3] = author[3].replace("\"", "");
                //System.out.println("Nazwisko: "+author[3]);

     
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

