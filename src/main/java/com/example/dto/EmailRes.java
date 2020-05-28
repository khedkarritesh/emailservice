package com.example.dto;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"resultCode", "resultMsg", "count"})
public class EmailRes {
	private Integer resultCode;
	private String resultMsg;
	private Integer count;
	
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	

}
