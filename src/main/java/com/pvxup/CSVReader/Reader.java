package com.pvxup.CSVReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.pvxup.CSVReader.CSV;

public class Reader {

	Map<Integer, String> authors = new HashMap<Integer, String>();
	Map<Integer, String> titles = new HashMap<Integer, String>();
	Map<String, String> translations = new HashMap<String, String>();
	
	String urlLink = "http://lektury.gov.pl/api/book/";
	
	public void addAuthorsAndTitles() {
		
		translations.put("Arystofones", "Aristophones");
		translations.put("nieznany", "Unknown");
		translations.put("Molnar", "Molnair");
		translations.put("Sofokles", "Sophocles");
		translations.put("Wergiliusz", "Virgil");
		
		String authorjson;
		int key = 0;
		for (int it = 100; it <= 835; it++) {		
			JSONObject json;
			try {
				json = new JSONObject(IOUtils.toString(new URL(urlLink + it), Charset.forName("UTF-8")));
				authorjson = CSV.formatToLastName(json.optString("authors"),false);
				
				//Get the set
				Set set = (Set) translations.entrySet();

				//Create iterator on Set 
				Iterator iterator = set.iterator();

				while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
					
					// Get Key
					String keyValue = (String) mapEntry.getKey();
					if(authorjson == keyValue){
						//Get Value
						String value = (String) mapEntry.getValue();
						authorjson = value;
						System.out.println(authorjson);
					}
					
					
					
				}
				if (!(authors.containsValue(authorjson))) {
						authors.put(key, authorjson);
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