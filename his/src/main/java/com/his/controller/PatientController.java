package com.his.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.his.entity.Hospitalization;
import com.his.entity.Patient;
import com.his.service.PatientService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private PatientService patientService;
	@RequestMapping("/find")
	@ResponseBody
	public Object findAllPatient() {
		JSONObject obj = new JSONObject();
		//查找所有的病人信息
		List<Patient> listp = patientService.findAllPatient();
		if(listp.size()>0) {
			obj.put("list", listp);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public ResponseResult register(@RequestBody Patient patient) {
		//查找所有的病人信息
		List<Patient> listp = patientService.findAllPatient();
		for (Patient patient2 : listp) {
			//判断数据库的用户名和用户输入的用户名是否相同
			if(patient2.getPatientCode().equals(patient.getPatientCode())) {
				return new ResponseResult("false","用户已存在");
			}
		}
		//把数据添加到数据库
		int count = patientService.register(patient);
		if(count>0) {
			return new ResponseResult("true","注册成功");
		}else {
			return new ResponseResult("false","参数有误");
		}
		
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public ResponseResult login(@RequestBody Patient patient,Model model,HttpSession session) {
		//调用登录方法，查询
		Patient patient1 = patientService.login(patient);
		System.out.println(patient);
		System.out.println(patient1);
		//判断是否存在该对象
		if(patient1!=null) {
			//如果有，将病人信息保存在session里
			session.setAttribute("patient", patient1); 
			model.addAttribute("patient", patient);
			return new ResponseResult("true","登录成功",patient1);
		}else {
			return new ResponseResult("false","用户不存在");
		}
		
	}
	@RequestMapping("/findPatientById")
	@ResponseBody
	public ResponseResult findPatientById(Integer id,Model model) {
		//根据病人id查看病人信息
		Patient patient = patientService.findPatientById(id);
		if(patient!=null) {
			model.addAttribute("patient", patient);
			return new ResponseResult("true","查询成功",patient);
		}else {
			return new ResponseResult("false","查询失败");
		}
		
	}
	
	@RequestMapping("/table")
	@ResponseBody
	public Object findTable() {
		JSONObject obj = new JSONObject();
		List<Patient> list = new ArrayList<Patient>();
		Patient patient1 = new Patient();
		patient1.setPatientCode("patientcode");
		patient1.setPatientId(1);
		patient1.setPatientName("patientName");
		patient1.setPatientCreatedate(new Date());
		list.add(patient1);
		obj.put("list", list);
		obj.put("pageTotal", 1);
		return obj;
	}
	@RequestMapping("/findPatientByHoszatstate")
	@ResponseBody
	public Object findPatientByHoszatstate(String name) {
		Patient patient = new Patient();
		patient.setPatientName(name);
		JSONObject obj = new JSONObject();
		List<Patient> listpat = patientService.findPatientByHoszatstate(patient);
//		System.out.println("获得的集合长度"+listpat.size());
		if(listpat.size()>0) {
			obj.put("list", listpat);
			obj.put("result",new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", listpat);
			obj.put("result",new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	@RequestMapping("/updatePatientById")
	@ResponseBody
	public ResponseResult updatePatientById(@RequestBody Patient patient) {
		//修改病人信息
		int count = patientService.updatePatientById(patient);
		if(count>0) {
			return new ResponseResult("true","修改成功");
		}else {
			return new ResponseResult("false","修改失败");
		}
	}
	
	@RequestMapping("/addHospitalizationByPatient")
	@ResponseBody
	public ResponseResult addHospitalizationByPatient(Integer patientDepartmentId,HttpSession session,Hospitalization hospitalization) {
		//获取病人信息
		Patient patient = (Patient) session.getAttribute("patient");
		//获取病人编号
		int id = patient.getPatientId();
		hospitalization.setHospitalizationPatientId(id);
		hospitalization.setHospitalizationReason(hospitalization.getHospitalizationReason());
		//调用方法，病人申请住院
		int count = patientService.addHospitalization(hospitalization);
		
		Patient patient2 = new Patient();
		patient2.setPatientId(id);
		patient2.setPatientHospitalizationId(hospitalization.getHospitalizationId());
		patient2.setPatientDepartmentId(patientDepartmentId);
		count = patientService.updatePatientById(patient2);
		if (count>0) {
			return new ResponseResult("true","申请成功");
		}else {
			return new ResponseResult("false","申请失败");
		}
	}
}
