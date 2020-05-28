package com.example.email.model;

public class EmailData {
	
	private boolean isValid;
	private String name;
	private String domain;
	
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getResolvedEmailID() {
		return new StringBuilder().append(this.name).append("@").append(this.domain).toString();
	}
}
