package com.his.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Staff;
import com.his.entity.Work;
import com.his.service.StaffService;
import com.his.service.WorkService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class WorkController {
	@Autowired
	private WorkService workService;
	@Autowired
	private StaffService staffService;
	public void setWorkService(WorkService workService) {
		this.workService = workService;
	}
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping("/updateWorkById")
	@ResponseBody
	public ResponseResult updateWorkById(HttpSession session) {
		//查找该医生的信息
		Staff staff = (Staff) session.getAttribute("staff");
		//查看是否当日第一次工作
		Work work=workService.findTodayWorkById(staff.getStaffId());
		//创建工作表对象
		Work work2= new Work();
		work2.setWorkStaffId(staff.getStaffId());
		if (work==null) {//当日没有工作表
			work2.setWorkIncome(staff.getStaffCost());
			//调用方法，创建当天的工作表
			int num=workService.addWork(work2);
			if (num>0) {
				return new ResponseResult("true","添加成功");
			}else {
				return new ResponseResult("false","添加失败");
			}
		}else {//当日已有工作表，修改工作表即可
			work2.setWorkNum(work.getWorkNum()+1);
			work2.setWorkIncome(work.getWorkIncome()+staff.getStaffCost());
			//调用方法，修改工作表
			int count2=workService.updateWorkById(work2);
			if (count2>0) {
				return new ResponseResult("true","添加成功");
			}else {
				return new ResponseResult("false","添加失败");
			}
		}
	}
	
	@RequestMapping("/findTodayWorkById")
	@ResponseBody
	public ResponseResult findTodayWorkById(Integer id) {
		//调用方法，查询指定id医生当日工作表
		Work work= workService.findTodayWorkById(id);
		
		if (work!=null) {
			return new ResponseResult("true","查询成功",work);
		}else {
			return new ResponseResult("false","查询失败");
		}
	}
	
	@RequestMapping("/addWork")
	@ResponseBody
	public ResponseResult addWork(@RequestBody Work work) {
		//调用方法，添加工作表
		int count = workService.addWork(work);
		if (count>0) {
			return new ResponseResult("true","添加成功");
		}else {
			return new ResponseResult("false","添加失败");
		}
	}
	@RequestMapping("/findWorkById")
	@ResponseBody
	public Object findWorkById(Integer id) {
		JSONObject obj = new JSONObject();
		//查询指定id的医生工作表
		List<Work> worklist = workService.findWorkById(id);
		if (worklist.size()!=0) {
			obj.put("list", worklist);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
}
