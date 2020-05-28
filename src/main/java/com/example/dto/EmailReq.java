package com.example.dto;

import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"emailIDs", "options"})
public class EmailReq {
	
	public EmailReq() {}
	
	private List<String> emailIDs;
	private List<String> options;

	public List<String> getEmailIDs() {
		return emailIDs;
	}

	public void setEmailIDs(List<String> emailIDs) {
		this.emailIDs = emailIDs;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}
}
