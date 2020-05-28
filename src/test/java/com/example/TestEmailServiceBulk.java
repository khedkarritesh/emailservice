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
public class TestEmailServiceBulk {




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
	    public void testBulkFindUnique1() {
	    		
	    	Response response = null;
	    	try {
	    		FileInputStream fis = new FileInputStream(new File("bulkFindUnique-1.json"));
	    		response = target.path("email/countUnique")
	    				.request(MediaType.APPLICATION_JSON_TYPE)
	    				.post(Entity.json(fis));
	    		fis.close();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	assertEquals(200, response.getStatus());
	    	assertEquals("application/json", response.getHeaderString("Content-Type"));
	    	assertEquals("53", response.getHeaderString("Content-Length"));
	    	assertEquals("{\"resultCode\":0,\"resultMsg\":\"success\",\"count\":999999}", response.readEntity(String.class));
	    }
	    
	    @Test
	    public void testBulkFindUnique2() {
	    		
	    	Response response = null;
	    	try {
	    		FileInputStream fis = new FileInputStream(new File("bulkFindUnique-1.json"));
	    		response = target.path("email/bulkCountUnique")
	    				.request(MediaType.APPLICATION_JSON_TYPE)
	    				.post(Entity.json(fis));
	    		fis.close();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	assertEquals(200, response.getStatus());
	    	assertEquals("application/json", response.getHeaderString("Content-Type"));
	    	assertEquals("15", response.getHeaderString("Content-Length"));
	    	assertEquals("{count: 999999}", response.readEntity(String.class));
	    }
}
