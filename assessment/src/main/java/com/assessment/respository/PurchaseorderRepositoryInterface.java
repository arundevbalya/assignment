package com.assessment.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.PurchaseOrder;


public interface PurchaseorderRepositoryInterface extends JpaRepository<PurchaseOrder, String> {
	
	public List<PurchaseOrder> findByStatusIn(List<String> statusList);
	
	public List<PurchaseOrder> findByStatus(String status);

}
