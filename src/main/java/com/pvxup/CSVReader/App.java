package com.pvxup.CSVReader;

public class App {
	
	
	public static void main(String[] args){
		
		Reader reader = new Reader();
		reader.addAuthorsAndTitles();
		
		CSV csv = new CSV("goodreads_library_export.csv");
		csv.CSVReading(reader.authors);
		
		//System.out.println(reader.authors.size());
		//System.out.println(reader.authors.values());
		//System.out.println(Titles.values());
	}
}