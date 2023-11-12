package com.javaweb.service;
import java.util.List;

import com.javaweb.entity.goods_order_detail;
import com.javaweb.entity.goods_receipt;
import com.javaweb.entity.goods_receipt_detail;
public interface GoodsReceiptDetailService {
	List<goods_receipt_detail> findDetailByRecieptId(Long id);
	
	<S extends goods_receipt_detail> Iterable<S> saveAll(Iterable<S> entities);
}
