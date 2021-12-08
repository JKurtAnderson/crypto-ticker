# Getting Started with Crypto Ticker

This mock project shows an up-to-date ticker for any crypto-currency. 

## Running it locally

First, build it with Maven:

`mvn compile assembly:single`

Then, run the built jar file:

`java -jar target/crypto-ticker-<version>-jar-with-dependencies.jar <token>`

Example usage:

`java -jar target/crypto-ticker-0.1-jar-with-dependencies.jar btc`
