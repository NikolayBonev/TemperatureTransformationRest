package rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;

import core.Temperature;

public class TemperatureCalculatorClient {
	private CloseableHttpClient httpClient = null;

	public String tranformTemperature(Temperature temperature) {
		this.createClient();
		
		HttpGet httpGet = null;
		
		try {
			httpGet = createRequest(temperature);
		} catch (URISyntaxException e) {
		}
		
		CloseableHttpResponse httpResponse = null;
		
		try {
			httpResponse = this.httpClient.execute(httpGet);
		} catch (IOException e) {
		}
		
		String jsonResponse = null;
		
		try {
					
			Scanner sc = new Scanner(httpResponse.getEntity().getContent());
					
			while(sc.hasNext()){
				jsonResponse = sc.nextLine();
			}		
		} catch (UnsupportedOperationException | IOException e1) {
		}		
				
		this.closeClient();
		
		return jsonResponse;
	}

	private HttpGet createRequest(Temperature temperature) throws URISyntaxException {
		URIBuilder builder = new URIBuilder();

		builder.setScheme("http").setHost("localhost").setPort(8080)
				.setPath("TemperatureCalculatorRestAPI/rest/temperatureCalculator/query")
				.setParameter("mesure", temperature.getMesure().toLowerCase())
				.setParameter("value", Double.toString(temperature.getTemperature()));

		URI uri = builder.build();

		HttpGet getRequest = new HttpGet(uri);
		getRequest.addHeader("Accept", "application/json");

		return getRequest;
	}

	private void createClient() {
		this.httpClient = HttpClients.createDefault();
	}

	private void closeClient() {
		try {
			this.httpClient.close();
		} catch (IOException e) {
		}
	}
}
