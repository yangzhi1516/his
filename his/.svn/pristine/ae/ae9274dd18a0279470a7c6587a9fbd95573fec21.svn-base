package com.his.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.his.entity.Drug;
import com.his.entity.Patient;
import com.his.entity.Prescription;
import com.his.service.DrugService;
import com.his.service.PatientService;
import com.his.service.PrescriptionService;
import com.his.utils.ResponseResult;

@Controller
@RequestMapping("/api")
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DrugService drugService;
	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}
	
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@RequestMapping("/addPrescription")
	@ResponseBody
	public Object addPrescription(@RequestBody String prescription) {
		System.out.println(prescription);
		JSONObject obj1 = JSONObject.parseObject(prescription);
		JSONArray obj2 = obj1.getJSONArray("prescription");
		List<Prescription> list = JSONObject.parseArray(obj2.toJSONString(), Prescription.class);
		Integer count=0;
		Integer patientId = list.get(list.size()-1).getPrescriptionPatientId();
		UUID code = UUID.randomUUID();
		String prescode =code.toString();
		System.out.println(prescode);
		int i=0;
		for (Prescription pres: list) {
			i++;
			if(pres.getPrescriptionDrugId()==null){
				continue;
			}else if(i>=list.size()) {
				break;
			}else {
				pres.setPrescriptionCode(prescode);
				pres.setPrescriptionState(0);//状态
				pres.setPrescriptionPatientId(patientId);
				//执行添加药方
				count = prescriptionService.addPrescription(pres);
				//修改药品库存
				Drug drug = drugService.findDrugById(pres.getPrescriptionDrugId());
				Integer drugNum = drug.getDrugNumber();
				drug.setDrugNumber(drugNum-pres.getPrescriptionDrugnum());
				drugService.updateDrugNumberById(drug);
			}
		}	
		if (count>0) {
			Patient patient = new Patient();
			patient.setPatientPrescriptionCode(prescode);
			patient.setPatientId(patientId);
			//修改病人信息中的要取药方信息
			int count2 = patientService.updatePatientById(patient);
			if (count2>0) {
				return new ResponseResult("true","添加成功");
			}else {
				return new ResponseResult("false","病人信息修改失败");
			}
		}else {
			return new ResponseResult("false","添加失败");
		}
	}
	
	@RequestMapping("/findAllPrescription")
	@ResponseBody
	public Object findAllPrescription(Model model) {
		JSONObject obj = new JSONObject();
		//查询所有药方信息
		List<Prescription> prescriptionlist =prescriptionService.findAllPrescription();
		if(prescriptionlist.size()!=0) {
			model.addAttribute("findAllPrescriptionList", prescriptionlist);
			obj.put("list", prescriptionlist);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
	@RequestMapping("/findPrescriptionByCode")
	@ResponseBody
	public Object findPrescriptionByCode(Model model,HttpSession session) {
		Patient patient = (Patient) session.getAttribute("patient");
		Integer patientId = patient.getPatientId();
		Patient patient2 = patientService.findPatientById(patientId);
		String patientPrescriptionCode = patient2.getPatientPrescriptionCode();
		JSONObject obj = new JSONObject();
		//调用方法，查询指定药方编号的药方信息
		List<Prescription> prescriptionlist=prescriptionService.findPrescriptionByCode(patientPrescriptionCode);
		if (prescriptionlist.size()!=0) {
			model.addAttribute("findPrescriptionByCodeList", prescriptionlist);
			obj.put("list", prescriptionlist);
			obj.put("result", new ResponseResult("true","查询成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","查询失败"));
		}
		return obj;
	}
	
	@RequestMapping("/updatePrescriptionById")
	@ResponseBody
	public Object updatePrescriptionById(String prescriptionCode) {
		JSONObject obj = new JSONObject();
		//调用方法，查询指定药方编号的药方信息
		Integer count = prescriptionService.updatePrescriptionById(prescriptionCode);
		if (count>0) {
			obj.put("list", null);
			obj.put("result", new ResponseResult("true","修改成功"));
		}else {
			obj.put("list", null);
			obj.put("result", new ResponseResult("false","修改失败"));
		}
		return obj;
	}
}
