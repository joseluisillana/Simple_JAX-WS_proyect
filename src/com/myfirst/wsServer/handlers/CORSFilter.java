package com.myfirst.wsServer.handlers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class CORSFilter implements   SOAPHandler<SOAPMessageContext> {

	@Override
	public void close(MessageContext arg0) {
		System.out.println("Client : close()......");

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		System.out.println("Client : handleFault()......");
		return true;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		System.out.println("Client : handleMessage()......");

		Boolean isRequest = (Boolean) arg0
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		// if this is a request, true for outbound messages, false for inbound
		if (isRequest) {

			try {
				SOAPMessage soapMsg = arg0.getMessage();
				SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnv.getHeader();

				// if no header, add one
				if (soapHeader == null) {
					soapHeader = soapEnv.addHeader();
				}

				QName qnAllowOrigin = new QName("Access-Control-Allow-Origin",
						"*");
				QName qnAllowHeaders = new QName(
						"Access-Control-Allow-Headers",
						"origin, content-type, accept, authorization");
				QName qnAllowCredentials = new QName(
						"Access-Control-Allow-Credentials", "true");
				QName qnAllowMethods = new QName(
						"Access-Control-Allow-Methods",
						"GET, POST, PUT, DELETE, OPTIONS, HEAD");
				QName qnAllowMaxAge = new QName("Access-Control-Max-Age",
						"1209600");

				SOAPHeaderElement sHEAllowOrigin = soapHeader.addHeaderElement(qnAllowOrigin);
				sHEAllowOrigin.setActor("Access-Control-Allow-Origin");
				sHEAllowOrigin.addTextNode("*");

				SOAPHeaderElement sHEAllowHeaders = soapHeader.addHeaderElement(qnAllowHeaders);
				sHEAllowHeaders.setActor("Access-Control-Allow-Headers");
				sHEAllowHeaders.addTextNode("origin, content-type, accept, authorization");
				
				SOAPHeaderElement sHEAllowCredentials = soapHeader.addHeaderElement(qnAllowCredentials);
				sHEAllowCredentials.setActor("Access-Control-Allow-Methods");
				sHEAllowCredentials.addTextNode("GET, POST, PUT, DELETE, OPTIONS, HEAD");
				
				SOAPHeaderElement sHEAllowMethods = soapHeader.addHeaderElement(qnAllowMethods);
				sHEAllowMethods.setActor("Access-Control-Allow-Credentials");
				sHEAllowMethods.addTextNode("true");
				
				SOAPHeaderElement sHEAllowMaxAge = soapHeader.addHeaderElement(qnAllowMaxAge);
				sHEAllowHeaders.setActor("Access-Control-Max-Age");
				sHEAllowHeaders.addTextNode("1209600");
				soapMsg.saveChanges();

				// tracking
				soapMsg.writeTo(System.out);

			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}

		}

		// continue other handler chain
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("Client : getHeaders()......");
		
		QName qnAllowOrigin = new QName("Access-Control-Allow-Origin",
				"*");
		QName qnAllowHeaders = new QName(
				"Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization");
		QName qnAllowCredentials = new QName(
				"Access-Control-Allow-Credentials", "true");
		QName qnAllowMethods = new QName(
				"Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD");
		QName qnAllowMaxAge = new QName("Access-Control-Max-Age",
				"1209600");
		Set<QName> listaRes = new HashSet<QName>();
		listaRes.add(qnAllowOrigin);
		listaRes.add(qnAllowHeaders);
		listaRes.add(qnAllowCredentials);
		listaRes.add(qnAllowMethods);
		listaRes.add(qnAllowMaxAge);
		return listaRes;
	}
}
