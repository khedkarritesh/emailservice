package com.example;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.dto.EmailReq;
import com.example.dto.EmailRes;
import com.example.email.process.EmailCounter;

/**
 * Root resource (exposed at "email" path)
 */
@Path("email")
@Consumes(MediaType.APPLICATION_JSON)
public class EmailService {
    
    @Path("countUnique")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmailRes countUnique(final EmailReq emailReq) {
    	List<String> options = emailReq.getOptions();
    	boolean skipValidCharCheck = false;
    	boolean skipMaxLenCheck = false;
    	if (options != null) {
    		for (String opt: options) {
    			if ("skipValidCharCheck".equals(opt)) {
    				skipValidCharCheck = true;
    			} else if ("skipMaxLenCheck".equals(opt)) {
    				skipMaxLenCheck = true;
    			}
    		}
    	}
    	long start = System.currentTimeMillis();
        int count = 0;
        count = EmailCounter.INSTANCE.countUniqueEmailIDs(emailReq.getEmailIDs(), skipValidCharCheck, skipMaxLenCheck);
        long end = System.currentTimeMillis();
		System.out.println("Time Taken for req size: " + emailReq.getEmailIDs().size() + " is: " + (end-start) + " millis. Uniques: " + count);
		EmailRes res = new EmailRes();
		res.setCount(count);
		res.setResultCode(0);
		res.setResultMsg("success");
		return res;
		
    }
    
    @Path("bulkCountUnique")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String bulkCountUnique(final EmailReq emailReq) {
    	
    	long start = System.currentTimeMillis();
        int count = 0;
        count = EmailCounter.INSTANCE.bulkCountUniqueEmailIDs(emailReq.getEmailIDs(), false, false);
        long end = System.currentTimeMillis();
		System.out.println("Bulk Time Taken for req size: " + emailReq.getEmailIDs().size() + " is: " + (end-start) + " millis. Uniques: " + count);
        return "{count: " + count + "}";
		
    }
    
}
