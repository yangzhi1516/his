package com.his.dao;

import java.util.List;

import com.his.entity.Prescription;

public interface IPrescriptionDao {
	
	/**
	 * 	添加药方
	 * @return
	 */
	Integer addPrescription(Prescription prescription);
	
	/**
	 * 	查询所有药方信息
	 * @return
	 */
	List<Prescription> findAllPrescription();
	
	/**
	 * 	根据药方编号(UUID),查询药方信息
	 * @param prescriptionCode
	 * @return
	 */
	List<Prescription> findPrescriptionByCode(String prescriptionCode);
	
	/**
	 * 修改药方状态
	 * @param prescriptionCode
	 * @return
	 */
	Integer updatePrescriptionById(String prescriptionCode);
}
