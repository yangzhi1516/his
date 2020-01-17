package com.his.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Nursing;
import com.his.entity.Staff;
import com.his.service.NursingService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class NursingController {

	@Autowired
	private NursingService nursingService;
	@RequestMapping("/addNursing")
	@ResponseBody
	public ResponseResult addNursing(Nursing nursing,HttpSession session) {
		//获取护士的id
		Staff staff = (Staff) session.getAttribute("staff");
	//	System.out.println(staff);
		//添加到对象里
		nursing.setNursingStaffId(staff.getStaffId());
		//调用添加的方法
		int count = nursingService.addNursing(nursing);
		if(count>0) {
			return new ResponseResult("true", "添加成功");
		}else {
			return new ResponseResult("false", "添加失败");
		}
	}
	@RequestMapping("/findNursingByNursId")
	@ResponseBody
	public Object findNursingByNursId(Integer nursId,HttpSession session) {
		JSONObject obj = new JSONObject();
		//调用查询的方法
		List<Nursing> listn = nursingService.findNursingByNursId(nursId);
		if(listn.size()>0) {
			obj.put("list", listn);
			obj.put("result",new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result",new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	@RequestMapping("/findNursingByPatientId")
	@ResponseBody
	public Object findNursingByPatientId(Integer nursingPatientId,Model model) {
		JSONObject obj = new JSONObject();
		//按id查询护理
		List<Nursing> nursing =nursingService.findNursingByPatientId(nursingPatientId);
//		System.out.println("传过来的id======"+nursingPatientId);
//		System.out.println("返回list的长度====="+nursing.size());
		if (nursing.size()!=0) {
			obj.put("list", nursing);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
	
	
	public void setNursingService(NursingService nursingService) {
		this.nursingService = nursingService;
	}
	
}
