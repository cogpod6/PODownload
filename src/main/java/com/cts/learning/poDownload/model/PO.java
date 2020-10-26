package com.cts.learning.poDownload.model;

import java.util.List;

public class PO {
	
	private String poNumber;
	private String poDate;
	private String poAddress;
	private Integer orderedQuantity;
	private List<POLine> poLines;
	
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}
	
	public String getPoAddress() {
		return poAddress;
	}
	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}
	
	public Integer getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(Integer orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	public List<POLine> getPoLines() {
		return poLines;
	}
	public void setPoLines(List<POLine> poLines) {
		this.poLines = poLines;
	}
	
	
}
