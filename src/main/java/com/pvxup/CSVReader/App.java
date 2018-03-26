package com.pvxup.CSVReader;

import java.io.FileNotFoundException;

public class App {
	
	
	public static void main(String[] args) throws FileNotFoundException{
		
		Reader reader = new Reader();
		reader.addAuthorsAndTitles();
		
		CSV csv = new CSV("goodreads_library_export.csv");
		csv.CSVAddAuthors(reader.authors);
		
		//System.out.println(reader.authors.size());
		//System.out.println(reader.authors.values());
		//System.out.println(Titles.values());
	}
}