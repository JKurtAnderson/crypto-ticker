package com.jason.anderson.web;
import com.jason.anderson.web.Client.ClientException;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class CryptoProxy {
	private static final String METRICS_URL = "https://data.messari.io/api/v1/assets/${token}/metrics";
	private static final String USD_PRICE_PATH = "data.market_data.price_usd";
	private Client client;

	public CryptoProxy() {
		this.client = new Client();
	}

	public float getTokenValueUSD(String token) {
		String responseBody;
		try {
			responseBody = client.get(METRICS_URL.replace("${token}", token.toLowerCase()));

			final DocumentContext jsonContext = JsonPath.parse(responseBody);
			final Object value = jsonContext.read(USD_PRICE_PATH);
	
			return Float.parseFloat(value.toString());
		} catch (ClientException e) {
			return -1;
		}
	}
}
