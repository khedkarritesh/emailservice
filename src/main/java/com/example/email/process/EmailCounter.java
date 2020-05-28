package com.example.email.process;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.email.model.EmailData;

public class EmailCounter {
	public static EmailCounter INSTANCE = new EmailCounter();
	
	private EmailCounter() {}
	
	/**
	 * This method will count number of unique emailIDs from a list of given emailIDs. If some emailID
	 * is found to be invalid, then it will be ignored. There are two optional check for validity: 
	 * skipValidCharCheck, skipMaxLenCheck.
	 * 
	 * @param emailIDs
	 * @param skipValidCharCheck - If true email IDs contains unexpected characters such as umlauts, will 
	 * not be skipped.
	 * @param skipMaxLenCheck - If true then email IDs longer than 1000 characters will not be skipped.
	 * 
	 * @return int - number of unique emailIDs.
	 */
	public int countUniqueEmailIDs(List<String> emailIDs, boolean skipValidCharCheck, boolean skipMaxLenCheck) {

		if (emailIDs == null || emailIDs.size() == 0) {
			return 0;
		}
		
		Set<String> uniqueEmailIDs = new HashSet<String>();
		
		for (String emailID: emailIDs) {
			
			if (emailID == null) {
				continue;
			}
			
			emailID = emailID.trim();
			
			if (emailID.length() == 0) {
				continue;
			}
			
			EmailData resolved = EmailCheck.INSTANCE.resolveEmailID(emailID, skipValidCharCheck, skipMaxLenCheck);
			if (!resolved.isValid()) {
				continue;
			}
			if (uniqueEmailIDs.contains(resolved.getResolvedEmailID())) {
			}
			uniqueEmailIDs.add(resolved.getResolvedEmailID());
		}
		
		
		return uniqueEmailIDs.size();
	}
	
	/**
	 * This method is similar to countUniqueEmailIDs. But it should be used only when dealing with bulk requests. 
	 * In future it will can different forms for concerns such parallelization, memory usage, etc. 
	 * @param emailIDs
	 * @param skipValidCharCheck
	 * @param skipMaxLenCheck
	 * @return
	 */
	public int bulkCountUniqueEmailIDs(List<String> emailIDs, boolean skipValidCharCheck, boolean skipMaxLenCheck) {
		if (emailIDs == null || emailIDs.size() == 0) {
			return 0;
		}
		
		Map<String, Set<String>> domainEmails = new HashMap<>();
		for (String emailID: emailIDs) {
			
			if (emailID == null) {
				continue;
			}
			
			emailID = emailID.trim();
			
			if (emailID.length() == 0) {
				continue;
			}
			
			EmailData resolved = EmailCheck.INSTANCE.resolveEmailID(emailID, false, false);
			if (!resolved.isValid()) {
				continue;
			}
			
			
			
			Set<String> domainSet = domainEmails.get(resolved.getDomain());
			if (domainSet == null) {
				domainSet = new HashSet<String>();
				domainEmails.put(resolved.getDomain(), domainSet);
			}
			domainSet.add(resolved.getName());
		}
		int count = 0;
		for (Set<String> emailSet: domainEmails.values()) {
			count += emailSet.size();
		}
		
		return count;
	}
}
