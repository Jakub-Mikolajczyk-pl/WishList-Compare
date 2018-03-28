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
import com.pvxup.CSVReader.CSV;

public class Reader {

	Map<Integer, String> authors = new HashMap<Integer, String>();
	Map<Integer, String> titles = new HashMap<Integer, String>();
	
	String urlLink = "http://lektury.gov.pl/api/book/";
	
	public void addAuthorsAndTitles() {
		String authorjson;
		int key = 0;
		for (int it = 100; it <= 835; it++) {		
			JSONObject json;
			try {
				json = new JSONObject(IOUtils.toString(new URL(urlLink + it), Charset.forName("UTF-8")));
				authorjson = CSV.formatToLastName(json.optString("authors"),false);
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
