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
import com.his.entity.Attendance;
import com.his.entity.Staff;
import com.his.service.AttendanceService;
import com.his.service.StaffService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private StaffService staffService;
	
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@RequestMapping("/staffSignin")
	@ResponseBody
	public ResponseResult staffSignin(HttpSession session) {
		//获取session中员工staff的信息
		Staff staff = (Staff) session.getAttribute("staff");
		//获取签到天数
		Attendance attendance = new Attendance();
		attendance.setAttendanceStaffId(staff.getStaffId());
		attendance.setAttendanceState(1);
		//签到表的签到天数改变
		attendance.setAttendanceDays(staff.getStaffDays()+1);
		//员工表的签到天数改变
		staff.setStaffDays(staff.getStaffDays()+1);
		//调用方法，修改员工表的签到天数
		staffService.updateStaffById(staff);
		
		//调用方法，完成签到
		int num = attendanceService.staffSignin(attendance);
	
		if (num>0) {
			return new ResponseResult("true","签到成功");
		}else {
			return new ResponseResult("false","签到失败");
		}
	}	
	
	@RequestMapping("/checkAttendance")
	@ResponseBody
	public Object checkAttendance(Model model) {
		JSONObject obj = new JSONObject();
		//查询员工签到列表
		List<Staff> stafflist = attendanceService.checkAttendance();
		model.addAttribute("checkAttendancelist", stafflist);
		System.out.println(stafflist.toString());
		obj.put("list", stafflist);
		obj.put("result", new ResponseResult("ture","查询所有"));
		return obj;
	}
}
