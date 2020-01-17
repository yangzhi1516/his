package com.his.dao;

import java.util.List;
import java.util.Map;

import com.his.entity.Hospitalization;
import com.his.entity.Patient;

public interface IPatientDao {

	/**
	 * 查找所有病人信息
	 * @return
	 */
	List<Patient> findAllPatient();
	
	/**
	 * 注册
	 * @param patient
	 * @return
	 */
	int register(Patient patient);
	
	/**
	 *     登录
	 * @param patient_code
	 * @param patient_password
	 * @return
	 */
	Patient login(Patient patient);
	
	/**
	 * 根据病人id查看病人信息
	 * @param id
	 * @return
	 */
	Patient findPatientById(Integer id);
	
	/**
	 * 查询所有住院，未出院的病人
	 * @return
	 */
	List<Patient> findPatientByHoszatstate(Patient patient);
	
	/**
	 * 添加医嘱id到病人表
	 * @param updatepMap
	 * @return
	 */
	int updatePatientInAdviceid(Map<String, Integer> updatepMap);
	
	/**
	 * 	按病人id修改病人信息
	 * @param patient
	 * @return
	 */
	int updatePatientById(Patient patient);
	
	
	/**
	 * 病人申请住院
	 * @param patientId
	 * @return
	 */
	int addHospitalization(Hospitalization hospitalization);
}
