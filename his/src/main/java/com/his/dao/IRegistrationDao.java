package com.his.dao;

import java.util.List;
import java.util.Map;

import com.his.entity.Registration;

public interface IRegistrationDao {
	
	/**
	 * 根据挂号编号查询挂号信息
	 * @param id
	 * @return
	 */
	List<Registration> findRegistrationById(Integer id);
	
	/**
	 * 添加挂号单信息
	 * @param registration
	 * @return
	 */
	int addRegistration(Registration registration);
	
	/**
	 * 医生修改挂号状态
	 * @param staffId
	 * @return
	 */
	int updateRegistration(Map<String, Object> regMap);
	
	/**
	 * 病人退号
	 * @param regId
	 * @return
	 */
	int updateReg(Integer regId);
}
