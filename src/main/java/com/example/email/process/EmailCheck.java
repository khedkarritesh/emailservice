package com.example.email.process;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.example.email.model.EmailData;

public class EmailCheck {
	public static EmailCheck INSTANCE = new EmailCheck();
	
	public static final Set<Character> Valid_Email_Chars;
	static {
		Set<Character> charSet = new HashSet<Character>();
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] numbers = {'0','1','2', '3','4','5','6','7','8','9'};
		char[] special = "!#$%&'*+-/=?^_`{|}~.".toCharArray();
		for (char ch: alphabet) {
			charSet.add(ch);
			charSet.add(Character.toUpperCase(ch));
		}
		
		for (char ch: numbers) {
			charSet.add(ch);
		}
		
		for (char ch: special) {
			charSet.add(ch);
		}
		Valid_Email_Chars = Collections.unmodifiableSet(charSet);
	}
	
	private EmailCheck() {}

	public EmailData resolveEmailID(String emailID) {
		return resolveEmailID(emailID, false, false);
	}
	
	public EmailData resolveEmailID(String emailID, boolean skipValidCharCheck, boolean skipMaxLenCheck) {
		
		StringBuilder sb = new StringBuilder();
		String emailName = null;
		String domain = null;
		int partitionIndex = -1;
		boolean isValid = true;
		if (!skipMaxLenCheck && emailID.length() > 1000) {
			EmailData email = new EmailData();
			email.setValid(false);
			return email;
		}
		
		boolean skipUntilDomain = false;
		for (int i = 0; i < emailID.length() && isValid; i++) {
			char ch = emailID.charAt(i);
			
			if (ch == '@') {
				
				if (partitionIndex != -1 || sb.length() == 0) {
					isValid = false;
					continue;
				}
				
				partitionIndex = i;
				
				emailName = sb.toString();
				sb = new StringBuilder();
				
			} else {
				if (!skipValidCharCheck && !Valid_Email_Chars.contains(ch)) {
					isValid = false;
					continue;
				}
				
				if (emailName == null) {
					if (skipUntilDomain) {
						continue;
					}
					
					if (ch == '.') {
						// In the emailName, we ignore the '.' character. It's valid though.
						continue;
					} else if (ch == '+') {
						// In the emailName, we must ignore the '+' character and any other characters until the '@' character is reached.
						skipUntilDomain = true;
						continue;
					} else {
						sb.append(ch);
					}
				} else {
					sb.append(ch);
				}
			}
		}
		
		if (isValid) {
			if (emailName == null || emailName.length() == 0 || sb.length() <= 0) {
				isValid = false;
			} else {
				domain = sb.toString();
			}
		}
		
		EmailData email = new EmailData();
		email.setValid(isValid);
		if (isValid) {
			email.setName(emailName.toLowerCase());
			email.setDomain(domain.toLowerCase());
		}
		return email;
	}
	

}
