package com.mino.groupware.service;

import java.util.List;
import java.util.Map;

import com.mino.groupware.vo.Products;

public interface ProdService {
	
	public int prodInsert(Products product);
	
	public List<Map<String, String>> prodList();
	
	public List<String> categoryList();
	
	public List<Map<String, String>> sortByCategory(Map<String, String> cate);
	
	public List<Map<String, String>> searchList(Map<String, String> keyword);
	
	public Map<String, String> prodDetail(String no);
	
	public int updateProd(Products udtProduct);
	
	public int deleteProd(Map<String, String> delno);
}
