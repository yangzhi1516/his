package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IPrescriptionDao;
import com.his.entity.Prescription;
import com.his.service.PrescriptionService;

@Service("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private IPrescriptionDao iPrescriptionDao;
	
	public void setiPrescriptionDao(IPrescriptionDao iPrescriptionDao) {
		this.iPrescriptionDao = iPrescriptionDao;
	}

	@Override
	public Integer addPrescription(Prescription prescription) {
		// TODO Auto-generated method stub
		return iPrescriptionDao.addPrescription(prescription);
	}

	@Override
	public List<Prescription> findAllPrescription() {
		// TODO Auto-generated method stub
		return iPrescriptionDao.findAllPrescription();
	}

	@Override
	public List<Prescription> findPrescriptionByCode(String prescriptionCode) {
		// TODO Auto-generated method stub
		return iPrescriptionDao.findPrescriptionByCode(prescriptionCode);
	}

	@Override
	public Integer updatePrescriptionById(String prescriptionCode) {
		// TODO Auto-generated method stub
		return iPrescriptionDao.updatePrescriptionById(prescriptionCode);
	}

}
