package com.his.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Income;
import com.his.service.IncomeService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class IncomeController {
	
	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping("/checkincome")
	@ResponseBody
	public Object chekIncome() {
		JSONObject obj = new JSONObject();
		//查找周一到周日的工作收入
		List<Income> list = incomeService.checkIncome();
		obj.put("list", list);
		if(list.size()>0) {
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	@RequestMapping("/addIncome")
	@ResponseBody
	public ResponseResult addIncome() {
		//执行添加当日收入
		int count = incomeService.addIncome();
		if (count>0) {
			return new ResponseResult("true","添加成功");
		}else {
			return new ResponseResult("false","添加失败");
		}	
	}
	
	@RequestMapping("/checkAll")
	@ResponseBody
	public Object checkAll() {
		//执行添加当日收入
		List<Income> list = incomeService.checkAll();
		JSONObject obj = new JSONObject();
		//查找周一到周日的工作收入
		obj.put("list", list);
		if(list.size()>0) {
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
}
