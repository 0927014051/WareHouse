package com.javaweb.model;

public class ResultPredict {
	
	private String product_id;
	
	private String product_name;
	
	private int predicted_value;

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

	public int getPredicted_value() {
		return predicted_value;
	}

	public void setPredicted_value(int predicted_value) {
		this.predicted_value = predicted_value;
	}
	public ResultPredict(String product_id, String product_name, int predicted_value) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.predicted_value = predicted_value;
    }
	

}
