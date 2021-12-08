package com.jason.anderson.web;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client
{
	public String get(String path) throws ClientException {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(path);
			URLConnection yc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
	
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
			}
			in.close();
		}
		catch (Exception e) {
			throw new ClientException(e);
		}
		return result.toString();
	}

	public class ClientException extends Exception {
		ClientException(Exception e) {
			super(e);
		}
	}
}
