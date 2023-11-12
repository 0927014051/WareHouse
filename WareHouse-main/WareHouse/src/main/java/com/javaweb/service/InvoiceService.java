package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.invoice;
import com.javaweb.entity.product;

public interface InvoiceService {
	
	List<invoice> findAll();
	invoice findInvoiceById(Long id);
	<S extends invoice> S save(S entity);
	void updateStatusInvoice(Long invoiceId, int status,Long staff_id_accept);
	List<invoice> findAllInvoiceByAccepted();
	Long totalPriceByMonth(int month);
}
