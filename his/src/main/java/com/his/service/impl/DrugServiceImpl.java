package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IDrugDao;
import com.his.entity.Drug;
import com.his.service.DrugService;

@Service("drugService")
public class DrugServiceImpl implements DrugService{
	@Autowired
	private IDrugDao iDrugDao;

	public void setiDrugDao(IDrugDao iDrugDao) {
		this.iDrugDao = iDrugDao;
	}

	@Override
	public List<Drug> findAllDrug() {
		// TODO Auto-generated method stub
		return iDrugDao.findAllDrug();
	}

	@Override
	public Drug findDrugById(Integer id) {
		// TODO Auto-generated method stub
		return iDrugDao.findDrugById(id);
	}

	@Override
	public Integer updateDrugNumberById(Drug drug) {
		// TODO Auto-generated method stub
		return iDrugDao.updateDrugNumberById(drug);
	}

	@Override
	public Integer deleteDrugById(Integer id) {
		// TODO Auto-generated method stub
		return iDrugDao.deleteDrugById(id);
	}	
}
