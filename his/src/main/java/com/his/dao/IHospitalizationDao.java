package com.his.dao;

import java.util.List;

import com.his.entity.Hospitalization;

public interface IHospitalizationDao {

	/**
	 * 门诊医生根据病人id创建住院信息表
	 * @param patientId
	 * @return
	 */
	int addHospitalization(Hospitalization hospitalization);
	
	/**
	 * 住院医生修改病人的住院信息
	 * @param hospitalization
	 * @return
	 */
	int updateHospitalization(Hospitalization hospitalization);
	
	/**
	 * 病人已缴纳的住院费用，修改住院信息
	 * @param hospitalization
	 * @return
	 */
	int patientUpdateHospitalization(Hospitalization hospitalization);
	
	/**
	 * 根据病人id查询所有的住院信息
	 * @param patientId
	 * @return
	 */
	List<Hospitalization> findAll(Integer patientId);
	
	/**
	 * 住院医生修改病人是否出院
	 * @param patientId
	 * @return
	 */
	int staffUpdateHospitalization(Integer patientId);
	
	/**
	 * 查询所有未出院的病人
	 * @return
	 */
	List<Hospitalization> findHospitalization();
	
	/**
	 *  查询所有申请住院的病人
	 * @return
	 */
	List<Hospitalization> findApplyHospitalization();
}
