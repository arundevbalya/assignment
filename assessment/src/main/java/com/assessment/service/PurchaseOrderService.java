package com.assessment.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.UnSupportedOperationException;
import com.assessment.configuration.Constants;
import com.assessment.model.PurchaseOrder;
import com.assessment.model.PurchaseOrderRequest;
import com.assessment.respository.PurchaseOrderRepository;
import com.assessment.respository.PurchaseorderRepositoryInterface;

@Service
public class PurchaseOrderService {
	
	@Autowired
	PurchaseorderRepositoryInterface  purchaseOrderRepository;
	
	public PurchaseOrder processPurchaseRequest(PurchaseOrderRequest request, String role, String user) throws UnsupportedOperationException {
		String action = request.getAction();
		System.out.println("User : " + user + " role : " + role  + "action : " + action);
		PurchaseOrder purchaseOrder = getPurchaseOrder(request);
		switch (role) {
		case Constants.ROLE_PUBLISHER:
			if (action.equals(Constants.ACTION_CREATE )|| action.equals(Constants.ACTION_UPDATE)) {
				if (action.equals(Constants.ACTION_CREATE)) {
					purchaseOrder.setCreatedBy(user);
					purchaseOrder.setStatus(Constants.STATUS_CREATED);
				} else if (action.equals(Constants.ACTION_UPDATE)) {
					purchaseOrder.setCreatedBy(request.getCreatedBy());
					purchaseOrder.setStatus(request.getStatus());
					purchaseOrder.setUpdatedBy(user);
				}			
				
			} else {
				throw new UnsupportedOperationException(user + " with role " + role + " is trying to do operation other than create / update...");
			}
			break;
		case Constants.ROLE_REVIEWER:
			if (action.equals(Constants.ACTION_ACCEPT) ||action.equals(Constants.ACTION_REJECT) ) {
				purchaseOrder.setUpdatedBy(user);
				purchaseOrder.setCreatedBy(request.getCreatedBy());
				if (action.equals(Constants.ACTION_ACCEPT)) {
					purchaseOrder.setStatus(Constants.STATUS_REVIEWED);
				} else if (action.equals(Constants.ACTION_REJECT)) {
					purchaseOrder.setStatus(Constants.STATUS_REWORK);
				}
				
			} else {
				throw new UnsupportedOperationException(user + " with role " + role + " is trying to do operation other than accept / reject ...");
			}
			break;
		case Constants.ROLE_APPROVER:
			if (action.equals(Constants.ACTION_ACCEPT) ||action.equals(Constants.ACTION_REJECT) ) {
				purchaseOrder.setUpdatedBy(user);
				purchaseOrder.setCreatedBy(request.getCreatedBy());
				if (action.equals(Constants.ACTION_ACCEPT)) {
					purchaseOrder.setStatus(Constants.STATUS_APPROVED);
				} else if (action.equals(Constants.ACTION_REJECT)) {
					purchaseOrder.setStatus(Constants.STATUS_REWORK);
				}
			} else {
				throw new UnsupportedOperationException(user + " with role " + role + " is trying to do operation other than accept / reject ...");
			}
			break;
		}
		
		System.out.println("Purchase order : " + purchaseOrder);
		purchaseOrderRepository.save(purchaseOrder);
		System.out.println("Result is : " + purchaseOrder);		
		return purchaseOrder;
	}
	
	
	
	public List<PurchaseOrder> getPurchaseOrderRequests(String role, String user){
		System.out.println("User : " + user + " role : " + role);
		List<PurchaseOrder> resultList = null;
		List<String> statusList = null;
		switch (role) {
		case Constants.ROLE_PUBLISHER:
			statusList = Arrays.asList(Constants.STATUS_CREATED, Constants.STATUS_REWORK);
			resultList = purchaseOrderRepository.findByStatusIn(statusList);
			break;
		case Constants.ROLE_REVIEWER:
			statusList = Arrays.asList(Constants.STATUS_CREATED, Constants.STATUS_REWORK);
			resultList = purchaseOrderRepository.findByStatusIn(statusList);
			break;
		case Constants.ROLE_APPROVER:
			resultList = purchaseOrderRepository.findByStatus(Constants.STATUS_REVIEWED);
			break;
		}
		return resultList;
	}
	
	private PurchaseOrder getPurchaseOrder(PurchaseOrderRequest request) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setAmount(request.getAmount());
		purchaseOrder.setTaskId(request.getTaskId());
		purchaseOrder.setRecordId(request.getRecordId());
		purchaseOrder.setPartsPrice(request.getPartsPrice());
		purchaseOrder.setLabourPrice(request.getLabourPrice());
		purchaseOrder.setReviewComments(request.getReviewComments());
		return purchaseOrder;
	}

}
