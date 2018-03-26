package com.pvxup.CSVReader;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CSV {
	
	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';
	
	String csvFile = "";
	Map<Integer, String> authors = new HashMap<Integer, String>();
	
	public CSV(String csvFile) {
		this.csvFile = csvFile;
	}
	
	
	 public static List<String> parseLine(String csvLine) {

	        List<String> result = new ArrayList<>();

	        //if empty, return!
	        if (csvLine == null && csvLine.isEmpty()) {
	            return result;
	        }

	        StringBuffer curVal = new StringBuffer();
	        boolean inQuotes = false;
	        boolean doubleQuotesInColumn = false;

	        char[] chars = csvLine.toCharArray();

	        for (char ch : chars) {

	            if (inQuotes) {
	                if (ch == DEFAULT_SEPARATOR) {
	                    inQuotes = false;
	                    doubleQuotesInColumn = false;
	                } else {

	                    //Fixed : allow "" in custom quote enclosed
	                    if (ch == DEFAULT_QUOTE) {
	                        if (!doubleQuotesInColumn) {
	                            curVal.append(ch);
	                            doubleQuotesInColumn = true;
	                        }
	                    } else {
	                        curVal.append(ch);
	                    }

	                }
	            } else {
	                if (ch == DEFAULT_SEPARATOR) {

	                    inQuotes = true;

	                } else if (ch == DEFAULT_SEPARATOR) {

	                    result.add(curVal.toString());

	                    curVal = new StringBuffer();

	                } else if (ch == '\n') {
	                    break;				//the end, break!
	                } else {
	                    curVal.append(ch);
	                }
	            }

	        }        
	        
	        result.add(curVal.toString());
	        
	        return result;
	    }
	 
	 private static String formatToLastName(String author) {
			String auth = author;				// we don't want to change param author content
			boolean moreThan1Word = false;		
			int indexOfSpace = 0;				
												   
			for (int i = 0; i < auth.length(); i++) {
				char letter = auth.charAt(i);
				if (letter == ' ') {
					moreThan1Word = true;
					indexOfSpace = i;
					break;
				}
			}
			if (!moreThan1Word) 
				return auth.substring(0, auth.length());	
			else 
				return auth.substring(indexOfSpace+1, auth.length());	
		}
	
	public void CSVAddAuthors(Map<Integer, String> hm) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File(csvFile));
		int key = 0;
		
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (!(this.authors.containsValue(formatToLastName(line.get(2))))) {
				this.authors.put(key, formatToLastName(line.get(2)));
				key++;
            }
            //System.out.println("Last name: " + formatToLastName(line.get(2)));
            if (hm.containsValue(formatToLastName(line.get(2))))
            	System.out.println(line.get(2) + " is on the wishlist");
            		
        }
        scanner.close();

    }
}

