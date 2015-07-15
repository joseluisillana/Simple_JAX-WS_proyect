package com.myfirst.wsServer;

import javax.jws.WebService;

@WebService
//@HandlerChain(file="handlerfile1.xml")
public class SayHello {
	
 private static final String SALUTATION = "Hello";
	
 public String getGreeting( String name ) {
  return SALUTATION + " " + name;
 }
}