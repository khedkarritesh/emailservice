package com.example;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestEmailService {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }
    
    @Test
    public void testFindUnique0() {
    		
    	Response response = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File("findUnique-0.json"));
    		response = target.path("email/countUnique")
    				.request(MediaType.APPLICATION_JSON_TYPE)
    				.post(Entity.json(fis));
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	assertEquals(200, response.getStatus());
    	assertEquals("application/json", response.getHeaderString("Content-Type"));
    	assertEquals("48", response.getHeaderString("Content-Length"));
    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":2}", response.readEntity(String.class));
    }
    
    @Test
    public void testFindUnique1() {
    		
    	Response response = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File("findUnique-1.json"));
    		response = target.path("email/countUnique")
    				.request(MediaType.APPLICATION_JSON_TYPE)
    				.post(Entity.json(fis));
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	assertEquals(200, response.getStatus());
    	assertEquals("application/json", response.getHeaderString("Content-Type"));
    	assertEquals("48", response.getHeaderString("Content-Length"));
    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":1}", response.readEntity(String.class));
    }
    @Test
    public void testFindUnique2() {
    		
    	Response response = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File("findUnique-2.json"));
    		response = target.path("email/countUnique")
    				.request(MediaType.APPLICATION_JSON_TYPE)
    				.post(Entity.json(fis));
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	assertEquals(200, response.getStatus());
    	assertEquals("application/json", response.getHeaderString("Content-Type"));
    	assertEquals("49", response.getHeaderString("Content-Length"));
    	
    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":56}", response.readEntity(String.class));
    }
    
    @Test
    public void testFindUnique3() {
    		
    	Response response = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File("findUnique-3.json"));
    		response = target.path("email/countUnique")
    				.request(MediaType.APPLICATION_JSON_TYPE)
    				.post(Entity.json(fis));
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	assertEquals(200, response.getStatus());
    	assertEquals("application/json", response.getHeaderString("Content-Type"));
    	assertEquals("48", response.getHeaderString("Content-Length"));
    	
    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":2}", response.readEntity(String.class));
    }
    @Test
    public void testFindUnique4() {
    		
    	Response response = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File("findUnique-4.json"));
    		response = target.path("email/countUnique")
    				.request(MediaType.APPLICATION_JSON_TYPE)
    				.post(Entity.json(fis));
    		fis.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	assertEquals(200, response.getStatus());
    	assertEquals("application/json", response.getHeaderString("Content-Type"));
    	assertEquals("48", response.getHeaderString("Content-Length"));
    	
    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":0}", response.readEntity(String.class));
    }

    
	/*
	 * public void
	 * givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() throws
	 * IOException { File testMailIDs = new File("testMailIDs.json"); BufferedWriter
	 * bw = new BufferedWriter(new FileWriter(testMailIDs)); for (int index = 0;
	 * index< 1000000; index++) { int leftLimit = 48; // numeral '0' int rightLimit
	 * = 122; // letter 'z' int targetStringLength = 10; Random random = new
	 * Random();
	 * 
	 * String generatedString = random.ints(leftLimit, rightLimit + 1) .filter(i ->
	 * (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) .limit(targetStringLength)
	 * .collect(StringBuilder::new, StringBuilder::appendCodePoint,
	 * StringBuilder::append) .toString();
	 * 
	 * String emailID = "\"" + generatedString;
	 * 
	 * final String[] domains = {"google.com", "hotmail.com", "live.com",
	 * "yahoo.com", "sbcglobal.com", "att.net", "comcast.net"};
	 * 
	 * emailID += "@" + domains[random.nextInt(7)] + "\",";
	 * 
	 * bw.write(emailID);bw.newLine();
	 * 
	 * } bw.close(); System.out.println("testMailIDs.json"); }
	 */
}
