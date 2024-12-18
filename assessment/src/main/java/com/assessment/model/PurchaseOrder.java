package com.assessment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchaseorder" )
public class PurchaseOrder {
	@Id
	@Column(name = "recordid")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	//@UuidGenerator
	private String recordId;
	//private int recordId;
	
	@Column(name = "taskid")
	private String taskId;
	
	@Column(name = "partsprice")
	private int partsPrice;
	
	@Column(name = "labourprice")
	private int labourPrice;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "reviewcomments")
	private String reviewComments;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "updatedby")
	private String updatedBy;

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
		return "PurchaseOrder [recordId=" + recordId + ", taskId=" + taskId + ", partsPrice=" + partsPrice
				+ ", labourPrice=" + labourPrice + ", amount=" + amount + ", status=" + status + ", reviewComments="
				+ reviewComments + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
