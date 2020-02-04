package Core;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class GoogleAPIJava {

final static String apiKey = "Placeholder";
final static String customSearchEngineKey = "Placeholder";

// base url for the search query
final static String searchURL = "https://www.googleapis.com/customsearch/v1?";
	public static String[] search(String A, int qs, String[] links) {
		int inicio = 1;
				String Links[] = links;
				String Link = null;
				
				int q = qs;
					System.out.println("***************** SEARCH **************************");
					System.out.println("");
					
					String result = "";

					result = read(A, inicio, 5);

					JsonParser parser = Json.createParser(new StringReader(result));

						while (parser.hasNext()) {
							Event event = parser.next();

							if (event == Event.KEY_NAME) {

								if (parser.getString().equals("htmlTitle")) {
									Event value = parser.next();

									if (value == Event.VALUE_STRING)
										System.out.println("Title (HTML): "+ parser.getString());
								}

								if (parser.getString().equals("link")) {

									Event value = parser.next();

									if (value == Event.VALUE_STRING)
										 Link =  parser.getString();
										System.out.println("Link: " + Link);
										 
										Links[q] = Link;
										q++;
								}

							}

						}

				

						
						System.out.println("**************************************************");
						
						return Links;
			}
		
	
/*public static void main(String[] args) {

}*/

private static String read(String qSearch, int start, int numOfResults) {
	try {
		String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&q=";
		
		toSearch += qSearch;
		toSearch += "&alt=json";
		toSearch += "&start=" + start;
		toSearch += "&num=" + numOfResults;
		System.out.println(toSearch);
		URL url = new URL(toSearch);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}
}