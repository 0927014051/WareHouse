package com.javaweb.controller.staff;

import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.entity.prediction;
import com.javaweb.service.impl.GoodsReceiptServiceImpl;
import com.javaweb.service.impl.PredictServiceImpl;


@Controller
public class AI {
	
	@Autowired
	PredictServiceImpl predictServiceImpl;
	
	@Autowired
	GoodsReceiptServiceImpl goodsReceiptServiceImpl;
	   
}