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
import com.his.entity.Queue;
import com.his.entity.Staff;
import com.his.service.QueueService;
import com.his.service.RegistrationService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class QueueController {
	@Autowired
	private QueueService queueService;
	@Autowired
	private RegistrationService registrationService;

	@RequestMapping("/addQueue")
	@ResponseBody
	public ResponseResult addQueue(@RequestBody Queue queue) {
		//调用添加挂号队列的方法
		int count = queueService.addQueue(queue);
		if(count>0) {
			return new ResponseResult("true","添加成功");
		}else {
			return new ResponseResult("false","添加失败");
		}
		
	}
	
	@RequestMapping("/findQueueCount")
	@ResponseBody
	public ResponseResult findQueueCount() {
		//调用查询挂号队列的条数的方法
		int count = queueService.findQueueCount();
		if(count>0) {
			return new ResponseResult("true","查询成功",count);
		}else {
			return new ResponseResult("false","查询失败");
		}
		
	}
	
	@RequestMapping("/delQueue")
	@ResponseBody
	public ResponseResult delQueue(@RequestBody String regId,HttpSession session) {
		JSONObject obj = JSONObject.parseObject(regId);
		Integer regid = (Integer)obj.get("regId");
		//从session里面,拿出来医生的id
		Staff staff = (Staff) session.getAttribute("staff");
		Map<String, Object> segMap = new HashMap<>();
		segMap.put("staffId", staff.getStaffId());
		segMap.put("regId", regid);
		//调用医生修改挂号状态方法
		int count = registrationService.updateRegistration(segMap);
		//调用删除挂号队列的方法
		count = queueService.delQueue(regid);
		if(count>0) {
			return new ResponseResult("true","删除成功",count);
		}else {
			return new ResponseResult("false","删除失败");
		}
		
	}
	
	@RequestMapping("/findAllByDepId")
	@ResponseBody
	public Object findAllByDepId(HttpSession session) {
		JSONObject obj = new JSONObject();
		Staff staff = (Staff) session.getAttribute("staff");
		//根据科室编号查询所有挂号单
		List<Queue> listq = queueService.findAllByDepId(staff.getStaffDepartmentId());
		if(listq.size()>0) {
			obj.put("list", listq);
			obj.put("result",new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result",new ResponseResult("false","查询失败"));
		}
		return obj;
		
	}
	
	public void setQueueService(QueueService queueService) {
		this.queueService = queueService;
	}
	
}
