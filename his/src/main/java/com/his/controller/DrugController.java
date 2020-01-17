package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Drug;
import com.his.service.DrugService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class DrugController {
	@Autowired
	private DrugService drugService;

	public void setDrugService(DrugService drugService) {
		this.drugService = drugService;
	}
	@RequestMapping("/findAllDrug")
	@ResponseBody
	public Object findAllDrug(Model model) {
		JSONObject obj = new JSONObject();
		//调用方法，查询药品信息列表
		List<Drug> druglist = drugService.findAllDrug();
		if(druglist.size()!=0) {
			model.addAttribute("findAllDrugList", druglist);
			obj.put("list", druglist);
			obj.put("result",new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result",new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
	@RequestMapping("/findDrugById")
	@ResponseBody
	public ResponseResult findDrugById(Integer id,Model model) {
		//调用方法，查询指定id药品信息
		Drug drug = drugService.findDrugById(id);
		model.addAttribute("findDrugById", drug);
		return new ResponseResult("true","查询成功",drug);
	}
	@RequestMapping("/updateDrugNumberById")
	@ResponseBody
	public Object updateDrugNumberById(@RequestBody Drug drug) {
		JSONObject obj = new JSONObject();
		//调用方法，修改药物数量
		Integer count = drugService.updateDrugNumberById(drug);
		//再次查询所有药物信息
		List<Drug> list = drugService.findAllDrug();
		if (count>0) {
			obj.put("list", list);
			obj.put("result",new ResponseResult("true","修改成功"));
		}else {
			obj.put("list", list);
			obj.put("result",new ResponseResult("false","修改失败"));
		}
		return obj;
	}
	
	@RequestMapping("/deleteDrugById")
	@ResponseBody
	public Object deleteDrugById(Integer id) {
		JSONObject obj = new JSONObject();
		//执行删除
		int count = drugService.deleteDrugById(id);
		//再次查询所有药物信息
		List<Drug> list = drugService.findAllDrug();
		if (count>0) {
			obj.put("list", list);
			obj.put("result",new ResponseResult("true","删除成功"));
		}else {
			obj.put("list", list);
			obj.put("result",new ResponseResult("false","删除失败"));
		}
		return obj;
	}
}
