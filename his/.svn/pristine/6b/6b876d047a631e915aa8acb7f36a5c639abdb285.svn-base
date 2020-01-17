package com.his.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Advice;
import com.his.entity.Staff;
import com.his.service.AdviceService;
import com.his.service.PatientService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class AdviceController {

	@Autowired
	private AdviceService adviceService;
	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/addAdvice")
	@ResponseBody
	public ResponseResult addAdvice(Advice advice,HttpSession session) {
		Staff staff = (Staff)session.getAttribute("staff");
		System.out.println(staff);
		System.out.println(advice);
		advice.setAdviceStaffId(staff.getStaffId());
		//调用添加医嘱的方法
		int count = adviceService.addAdvice(advice);
		if(count>0) {
			//设置'添加医嘱id到病人表'需要的参数
			Map<String, Integer> updatepMap = new HashMap<String, Integer>();
			updatepMap.put("adviceId", advice.getAdviceId());
			updatepMap.put("patientId", advice.getAdvicePatientId());
			//添加医嘱id到病人表
			int count2 = patientService.updatePatientInAdviceid(updatepMap);
			if(count2>0) {
				return new ResponseResult("true", "添加成功");
			}
			return new ResponseResult("false", "添加失败");
		}else {
			return new ResponseResult("false", "添加失败");
		}
	}
	
	@RequestMapping("/updateAdviceByNursStaff")
	@ResponseBody
	public ResponseResult updateAdviceByNursStaff(Advice advice,HttpSession session) {
		//得到护士的对象
		Staff staff = (Staff) session.getAttribute("staff");
		advice.setAdviceNurseId(staff.getStaffId());
		//护士执行医嘱
		int count = adviceService.updateAdviceByNursStaff(advice);
		if(count>0) {
			return new ResponseResult("true", "修改成功");
		}else {
			return new ResponseResult("false", "修改失败");
		}
	}
	@RequestMapping("/findAdviceByState")
	@ResponseBody
	public Object findAdviceByState() {
		JSONObject obj = new JSONObject();
		List<Advice> list = adviceService.findAdviceByState();
		if (list.size()!=0) {
			obj.put("list", list);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		
		return obj;
	}
	
	public void setAdviceService(AdviceService adviceService) {
		this.adviceService = adviceService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}
	
}
