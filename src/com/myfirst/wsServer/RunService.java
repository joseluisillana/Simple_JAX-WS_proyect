package com.myfirst.wsServer;

import javax.xml.ws.Endpoint;

public class RunService {

	/**
	 * @param args
	 */

	public static void main(String[] args) {

		System.out.println("SayHello Web Service started.");
		// Endpoint test =
		// Endpoint.publish("http://localhost:80/wsServerExample", new
		// SayHello());

		Endpoint.publish("http://localhost:80/wsServerExample",
				new SayHello());
		

	}
}