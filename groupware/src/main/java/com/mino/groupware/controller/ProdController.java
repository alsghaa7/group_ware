package com.mino.groupware.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mino.groupware.service.ProdService;
import com.mino.groupware.vo.Products;

@Controller
public class ProdController{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private final ProdService prodService;
	
	@Autowired
	public ProdController(ProdService prodService) {
		this.prodService = prodService;
	}
	
	@RequestMapping(value = "/returnProdInsert.do")
	public String prodInsertJsp(Model model) {
		
		logger.info(" return prodInsertJsp ");
		return "prodInsert";
	}
	
	@RequestMapping(value = "/returnDetail.do")
	public String returnDetail(Model model, @RequestParam("prod_no") String no) {
		
		
		model.addAttribute("dto", prodService.prodDetail(no));
		
		logger.info("detail_no: {}", no);
		return "adminProdDetail";
	}
	
	@RequestMapping(value = "/prodInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public int prodInsertProc(@RequestBody Products product) {
		logger.info("prodController Proc");
		
		int prod = prodService.prodInsert(product);
		return prod;
	}
	
	@RequestMapping(value = "/prodList.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List> getProdList(Model model){
		
		Map<String, List> result = new HashMap<String, List>();
		
		result.put("list", prodService.prodList());
		
		return result;
	}
	
	@RequestMapping(value = "/categoryList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getCategoryList(Model model){
		
		List<String> catList = new ArrayList<>();
		
		catList = prodService.categoryList();
		
		return catList;
	}
	
	@RequestMapping(value = "/sortByCategory.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List> getSortedList(Model model, @RequestBody Map<String, String> cate){
		
		Map<String, List> result = new HashMap<String, List>();
		
		result.put("sort", prodService.sortByCategory(cate));
		
		logger.info("getSortedList: {}", cate);
		
		return result;
	}
	
	@RequestMapping(value = "/searchList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List> getSearchList(Model model, @RequestBody Map<String, String> keyword){
		Map<String, List> result = new HashMap<String, List>();
		
		result.put("list", prodService.searchList(keyword));
		
		logger.info("getSearchList: {}", keyword);
		
		return result;	
	}
	
	@RequestMapping(value = "/updateProd.do", method = RequestMethod.POST)
	@ResponseBody
	public int updateProd(@RequestBody Products product) {
	
		logger.info("update: {}", product);
		int udt = prodService.updateProd(product);
		return udt;
	}
	
	@RequestMapping(value = "/deleteProd.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteProc(@RequestBody Map<String, String> delno) {
		
		logger.info("Controller-delete: {}", delno);
		
		return prodService.deleteProd(delno);
	}
	
}