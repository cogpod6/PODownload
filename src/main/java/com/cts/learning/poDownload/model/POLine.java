package com.cts.learning.poDownload.model;

public class POLine {
	
	private Integer id;
	private PO po;
	private Integer poLineNumber;
	private String itemNumber;
	private String itemName;
	private Integer quantity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public PO getPo() {
		return po;
	}
	public void setPo(PO po) {
		this.po = po;
	}
	
	public Integer getPoLineNumber() {
		return poLineNumber;
	}
	public void setPoLineNumber(Integer poLineNumber) {
		this.poLineNumber = poLineNumber;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
