package com.pvxup.CSVReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Reader {

	Map<Integer, String> authors = new HashMap<Integer, String>();
	Map<Integer, String> titles = new HashMap<Integer, String>();
	
	String urlLink = "http://lektury.gov.pl/api/book/";
	
	/**
	 * Method replaces first and last author's name with formatted last name
	 * @param author contains data from website about author's name
	 * @return Only author's last name is returned. Quotation marks and square brackets are skipped
	 */
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
			return auth.substring(2, auth.length() -2);	
		else 
			return auth.substring(indexOfSpace+1, auth.length() - 2);	
	}
	
	public void addAuthorsAndTitles() {
		String authorjson;
		int key = 0;
		for (int it = 90; it <= 850; it++) {		
			JSONObject json;
			try {
				json = new JSONObject(IOUtils.toString(new URL(urlLink + it), Charset.forName("UTF-8")));
				authorjson = Reader.formatToLastName(json.optString("authors"));
				if (!(this.authors.containsValue(authorjson))) {
						this.authors.put(key, authorjson);
						key++;
				}
				this.titles.put(it, json.optString("title"));
			} catch (JSONException e) {
				//If site doesn't contain authors, json won't add anything
				} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
}
