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
import com.his.entity.History;
import com.his.entity.Staff;
import com.his.service.HistoryService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	@RequestMapping("/addHistory")
	@ResponseBody
	public ResponseResult addHistory(History history,HttpSession session) {
		Staff staff = (Staff) session.getAttribute("staff");
		history.setHistoryStaffId(staff.getStaffId());
		//执行添加病史
		int count = historyService.addHistory(history);
		if (count>0) {
			return new ResponseResult("true","添加病史成功");
		}else {
			return new ResponseResult("false","添加病史失败");
		}
	}
	@RequestMapping("/findHistoryByPatientId")
	@ResponseBody
	public Object findHistoryByPatientId(Integer id) {
		//System.out.println("传来的id"+id);
		JSONObject obj = new JSONObject();
		//根据病人id查询病人病史
		List<History> list = historyService.findHistoryByPatientId(id);
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
