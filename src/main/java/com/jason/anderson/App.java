package com.jason.anderson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jason.anderson.web.CryptoProxy;
import java.text.NumberFormat;

/**
 * Hello world!
 *
 */
public class App 
{
	private final static int INTERVAL = 3; // wait 3 seconds between fetches

    public static void main( String[] args )
    {
		if (args.length < 1) {
			System.out.println("Please include the crypto token you'd like to watch");
			System.out.println("Usage: crypto-watch {token}");
			System.exit(1);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		CryptoProxy proxy = new CryptoProxy();

		while(true) {
			long startTime = System.currentTimeMillis();

			final float tokenValue = proxy.getTokenValueUSD(args[0]);

			// Something went wrong getting the token value. Don't update the screen until it works.
			if (tokenValue != -1) {
				System.out.print("\033[H\033[2J");  
				System.out.flush();
				System.out.println(fmt.format(tokenValue));
			}

			// Wait up to 3 seconds for the enter key to be pressed
			try {
				while ((System.currentTimeMillis() - startTime) < INTERVAL * 1000 && !in.ready()) {}
				if (in.ready()) {
					break;
				}
			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}
		}
	}
}
