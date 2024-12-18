package com.assessment.model;

import com.assessment.configuration.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class PurchaseOrderRequest {

	private String recordId;
	//private int recordId;
	private String taskId;
	private int partsPrice;
	private int labourPrice;
	private int amount;
	private String action;
	private String status;
	private String reviewComments;
	private String createdBy;
	private String updatedBy;

	public PurchaseOrderRequest(
			String recordId, 
			//int recordId,
			String taskId, int partsPrice, int labourPrice, int amount,
			String status, String reviewComments, String createdBy, String updatedBy, String action) {
		super();
		this.recordId = recordId;
		this.taskId = taskId;
		this.partsPrice = partsPrice;
		this.labourPrice = labourPrice;
		this.amount = amount;
		this.status = status;
		this.reviewComments = reviewComments;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.action = action;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
//	public int getRecordId() {
//		return recordId;
//	}
//	
//	public void setRecordId(int recordId) {
//		this.recordId = recordId;
//	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getPartsPrice() {
		return partsPrice;
	}

	public void setPartsPrice(int partsPrice) {
		this.partsPrice = partsPrice;
	}

	public int getLabourPrice() {
		return labourPrice;
	}

	public void setLabourPrice(int labourPrice) {
		this.labourPrice = labourPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "PurchaseOrderRequest [recordId=" + recordId + ", taskId=" + taskId + ", partsPrice=" + partsPrice
				+ ", labourPrice=" + labourPrice + ", amount=" + amount + ", action=" + action + ", status=" + status
				+ ", reviewComments=" + reviewComments + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

	
	public static void createPurchaseOrderRequest () throws Exception{
		PurchaseOrderRequest req = new PurchaseOrderRequest("3123", "replace pump", 150, 50, 200, null, null, null, null, Constants.ACTION_CREATE);
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(req);
		System.out.println(json);
	}
	
	public static void main(String[] args) throws Exception {
		createPurchaseOrderRequest();
	}
	

}
