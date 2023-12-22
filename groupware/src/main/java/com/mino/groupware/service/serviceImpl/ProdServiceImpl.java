package com.mino.groupware.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mino.groupware.controller.LoginController;
import com.mino.groupware.mapper.ProdMapper;
import com.mino.groupware.service.ProdService;
import com.mino.groupware.vo.Products;

public class ProdServiceImpl implements ProdService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final ProdMapper prodMapper;
	
	public ProdServiceImpl(ProdMapper prodMapper) {
		this.prodMapper = prodMapper;
	}
	
	public int prodInsert(Products product) {
		
		logger.info("[ prodService product insert ]: {}", product);
		return prodMapper.prodInsert(product);
	}
	
	public List<Map<String, String>> prodList(){
		
		return prodMapper.prodList();
	}
	
	public List<String> categoryList() {
		
		return prodMapper.categoryList();
	}
	
	public List<Map<String, String>> sortByCategory(Map<String, String> cate) {
		
		logger.info("prodService: {}", prodMapper.sortByCategory(cate));
		return prodMapper.sortByCategory(cate);
	}
	
	public List<Map<String, String>> searchList(Map<String, String> keyword){
		
		return prodMapper.searchList(keyword);
	}
	
	public Map<String, String> prodDetail(String no) {
		
		return prodMapper.prodDetail(no);
	}
	
	public int updateProd(Products udtProduct) {
		
		logger.info("updateProd: {}", prodMapper.updateProd(udtProduct));
		return prodMapper.updateProd(udtProduct);
	}
	
	public int deleteProd(Map<String, String> delno) {
		
		logger.info("Service-delete : {}", delno);
		
		return prodMapper.deleteProd(delno);
	}
}