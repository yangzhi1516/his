package com.his.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Patient;
import com.his.entity.Room;
import com.his.service.RoomService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class RoomController {
	@Autowired
	private RoomService roomService;

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	@RequestMapping("/findRoomByDepartmentId")
	@ResponseBody
	public Object findRoomByDepartmentId(Integer id) {
		JSONObject obj = new JSONObject();
		//调用方法，查询指定科室的病房
		List<Room> roomlist = roomService.findRoomByDepartmentId(id);
		if (roomlist.size()!=0) {
			obj.put("list", roomlist);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	@RequestMapping("/updateRoomNumById")
	@ResponseBody
	public ResponseResult updateRoomNumById(Integer roomid) {
		//执行方法，剩余可用床位-1，已用床位+1
		int count = roomService.updateRoomNumById(roomid);
		if (count>0) {
			return new ResponseResult("true","修改成功");
		}else {
			return new ResponseResult("false","修改失败");
		}
	}
	
	@RequestMapping("/updateReduceRoomNumByDepartmentId")
	@ResponseBody
	public ResponseResult updateReduceRoomNumByDepartmentId(HttpSession session) {
		//获取病人信息
		Patient patient= (Patient) session.getAttribute("patient");
		//获取病人所在科室编号
		int departmentid = patient.getPatientDepartmentId();
		//执行方法，剩余可用床位+1，已用床位-1
		int count = roomService.updateReduceRoomNumByDepartmentId(departmentid);
		if (count>0) {
			return new ResponseResult("true","修改成功");
		}else {
			return new ResponseResult("false","修改失败");
		}
	}
}
