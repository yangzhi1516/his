package com.his.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Salary;
import com.his.entity.Staff;
import com.his.service.SalaryService;
import com.his.service.StaffService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class StaffController {

	@Autowired
	private StaffService staffService;
	@Autowired
	private SalaryService salaryService;
	
	@RequestMapping("/staffLogin")
	@ResponseBody
	public ResponseResult staffLogin(@RequestBody Staff staff,HttpSession session) {
		//调用登录方法，查询
		Staff staff2 = staffService.staffLogin(staff);
		//判断是否存在该对象
		if(staff2!=null) {
			//如果有，将员工信息保存在session里
			session.setAttribute("staff", staff2); 
			return new ResponseResult("true","登录成功",staff2);
		}else {
			return new ResponseResult("false","用户不存在");
		}
		
	}
	
	@RequestMapping("/findStaffById")
	@ResponseBody
	public ResponseResult findStaffById(Integer id) {
		//调用方法查询指定id员工信息
		Staff staff =staffService.findStaffById(id);
		if(staff!=null) {
			return new ResponseResult("true","用户不存在");
		}else {
			return new ResponseResult("false","用户不存在");
		}
	}
	
	@RequestMapping("/updateStaffById")
	@ResponseBody
	public ResponseResult updateStaffById(@RequestBody Staff staff) {
		//调用方法，修改指定id的员工信息
		int count = staffService.updateStaffById(staff);
		if(count>0) {
			return new ResponseResult("true","修改成功");
		}else {
			return new ResponseResult("false","修改失败");
		}
	}
	@RequestMapping("/addStaff")
	@ResponseBody
	public ResponseResult addStaff(@RequestBody Staff staff,@RequestBody Salary salary) {
		//调用方法，添加员工信息
		int count = staffService.addStaff(staff);
		if(count>0) {
			//创建薪资信息
			int count2 = salaryService.addSalary(salary);
			if(count2>0) {
				//给员工的薪资属性赋值
				staff.setStaffSalaryId(salary.getSalaryId());
				//修改员工信息表（添加工资id）
				int count3 = staffService.updateStaff(staff);
				if(count3>0) {
					return new ResponseResult("true","添加成功");
				}
				return new ResponseResult("false","添加失败");
			}
			return new ResponseResult("false","添加失败");
		}else {
			return new ResponseResult("false","添加失败");
		}
	}
	
	@RequestMapping("/findStaffWork")
	@ResponseBody
	public Object findStaffWork() {
		JSONObject obj = new JSONObject();
		//查询所有职员当日工作表
		List<Staff> list = staffService.findStaffWork();
		if (list.size()!=0) {
			obj.put("list", list);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
}
