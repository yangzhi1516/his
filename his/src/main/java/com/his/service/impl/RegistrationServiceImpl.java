package com.his.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IRegistrationDao;
import com.his.entity.Registration;
import com.his.service.RegistrationService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private IRegistrationDao iRegistrationDao;
	//根据挂号单编号查询挂号信息
	@Override
	public List<Registration> findRegistrationById(Integer id) {
		// TODO Auto-generated method stub
		return iRegistrationDao.findRegistrationById(id);
	}
	@Override
	public int addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		return iRegistrationDao.addRegistration(registration);
	}
	
	@Override
	public int updateRegistration(Map<String, Object> regMap) {
		// TODO Auto-generated method stub
		return iRegistrationDao.updateRegistration(regMap);
	}
	
	@Override
	public int updateReg(Integer regId) {
		// TODO Auto-generated method stub
		return iRegistrationDao.updateReg(regId);
	}
	
	public void setiRegistrationDao(IRegistrationDao iRegistrationDao) {
		this.iRegistrationDao = iRegistrationDao;
	}

}
