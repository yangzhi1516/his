package com.his.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.entity.Salary;
import com.his.service.SalaryService;
import com.his.service.StaffService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class SalaryController {
	@Autowired
	private SalaryService salaryService;
	@Autowired
	private StaffService staffService;
	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}
	
	@RequestMapping("/updateSalary")
	@ResponseBody
	public ResponseResult updateSalary() {
		//根据id修改工资
		int count = salaryService.updateSalary();
		if (count>0) {
			return new ResponseResult("true","修改成功");
		}else {
			return new ResponseResult("false","修改失败");
		}
	}
	
	@RequestMapping("/findSalaryById")
	@ResponseBody
	public ResponseResult findSalaryById(Integer id) {
		//在查询之前先更新工资表信息
		int count = salaryService.updateSalary();
		if (count>0) {
			//更新成功后查询工资表
			Salary salary = salaryService.findSalaryById(id);
			if (salary!=null) {
				return new ResponseResult("true","查询成功");
			}else {
				return new ResponseResult("false","查询失败");
			}
		}else {
			return new ResponseResult("false","更新失败");
		}
	}
}
