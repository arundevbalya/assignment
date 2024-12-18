package com.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.PurchaseOrder;
import com.assessment.model.PurchaseOrderRequest;
import com.assessment.service.PurchaseOrderService;

@RestController
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@PostMapping(path = "/txn", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrder> processPurchaseOrder(@RequestBody PurchaseOrderRequest request){ 
		String role = null, user = null ;
		//setUserDetails(user, role);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		user = authentication.getName();
		role = authentication.getAuthorities().toString().replace('[', ' ').replace(']', ' ').trim();
		System.out.println("Role is : " + role + ", user is : " + user + " , Input req is : " + request);
		PurchaseOrder response = purchaseOrderService.processPurchaseRequest(request, role, user);
		System.out.println("Response is : " + response);
		return new ResponseEntity<PurchaseOrder>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PurchaseOrder>> getPurchaseOrderDetails(){
		String role = null, user = null ;
	//	setUserDetails(user, role);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		user = authentication.getName();
		role = authentication.getAuthorities().toString().replace('[', ' ').replace(']', ' ').trim();
		System.out.println("Role is : " + role + ", user is : " + user);
		List<PurchaseOrder> result = purchaseOrderService.getPurchaseOrderRequests(role, user);
		System.out.println("List Purchase request : " + result);		
		return new ResponseEntity<List<PurchaseOrder>>(result, HttpStatus.OK);
	}
	
	
	private void setUserDetails(String user, String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		user = authentication.getName();
		role = authentication.getAuthorities().toString().replace('[', ' ').replace(']', ' ').trim();
	}
	
	

}
