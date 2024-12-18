package com.assessment.respository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assessment.model.PurchaseOrderRequest;

@Repository
public class PurchaseOrderRepository {
	
	public static List<PurchaseOrderRequest> purchaseOrderList = new ArrayList<>();

	public PurchaseOrderRequest createPurchaseOrder(PurchaseOrderRequest request, String role, String user) {
		purchaseOrderList.add(request);
		return request;
	}

	public PurchaseOrderRequest updatePurchaseOrder (PurchaseOrderRequest request, String role, String user) {
		purchaseOrderList.add(request);
		return request;
	}

	public List<PurchaseOrderRequest> getPurchaseOrderRequests(String role, String user) {
		return purchaseOrderList;
	}

}
