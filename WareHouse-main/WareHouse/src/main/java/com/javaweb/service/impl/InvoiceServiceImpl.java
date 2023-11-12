package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.entity.invoice;
import com.javaweb.reponsitory.InvoiceRepo;
import com.javaweb.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	@Override
	public 	List<invoice> findAll(){
		
		return invoiceRepo.findAll();
	}
	
	@Override
	public invoice findInvoiceById(Long id) {
		return invoiceRepo.findInvoiceById(id);
	}
	
	
	@Override
	public 	<S extends invoice> S save(S entity) {
		return invoiceRepo.save(entity);
	}

	@Override
	public void updateStatusInvoice(Long invoiceId, int status,Long staff_id_accept) {		
		invoiceRepo.updateStatusInvoice(invoiceId, status, staff_id_accept);
	}
	
	@Override
	public 	List<invoice> findAllInvoiceByAccepted(){
		return invoiceRepo.findAllInvoiceByAccepted();
	}
	
	@Override
	public 	Long totalPriceByMonth(int month){
		return invoiceRepo.totalPriceByMonth(month);
	}
}
