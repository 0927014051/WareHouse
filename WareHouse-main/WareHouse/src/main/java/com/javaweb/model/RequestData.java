package com.javaweb.model;

import java.util.List;

public class RequestData {
	private String product_id;
	
	private String product_name;
	
	private String object;
	
	private Long category_id;
	
	private Long supplier_id;
	
	private String material;
	
    private List<String> imageList;
	
	private String mfg;
	
	private Long price;

		
    private List<String> spanColorList;
	
    private List<String> spanSizeList;


    public List<String> getSpanSizeList() {
        return spanSizeList;
    }

    public void setSpanSizeList(List<String> spanSizeList ) {
        this.spanSizeList = spanSizeList;
    }

    public List<String> getSpanColorList() {
        return spanColorList;
    }

    public void setSpanColorList(List<String> spanColorList ) {
        this.spanColorList = spanColorList;
    }

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	



	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
		this.mfg = mfg;
	}

}
