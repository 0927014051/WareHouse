package com.javaweb.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.invoice;
@Repository
public interface InvoiceRepo extends JpaRepository<invoice, Long>{
	@Query(value = "SELECT * FROM invoice WHERE invoice_id = ?1  ", nativeQuery = true)
	invoice findInvoiceById(Long id);
	

	@Modifying
    @Transactional
	@Query(value = "UPDATE invoice SET status = ?2, staff_id_accept = ?3 WHERE invoice_id = ?1", nativeQuery = true)
	void updateStatusInvoice(Long invoiceId, int status,Long staff_id_accept);
	
	@Query(value = "SELECT * FROM invoice WHERE status = 2  ", nativeQuery = true)
	List<invoice> findAllInvoiceByAccepted();
	
	@Query(value = "SELECT sum(total_price) FROM warehouse.invoice where status = 2 and month(date) = ?1  ", nativeQuery = true)
	Long totalPriceByMonth(int month);
	
}
