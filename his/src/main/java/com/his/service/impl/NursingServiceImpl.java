package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.INursingDao;
import com.his.entity.Nursing;
import com.his.service.NursingService;

@Service("nursingService")
public class NursingServiceImpl implements NursingService {

	@Autowired
	private INursingDao iNursingDao;
	@Override
	public int addNursing(Nursing nursing) {
		// TODO Auto-generated method stub
		return iNursingDao.addNursing(nursing);
	}
	public void setiNursingDao(INursingDao iNursingDao) {
		this.iNursingDao = iNursingDao;
	}
	@Override
	public List<Nursing> findNursingByNursId(Integer nursId) {
		// TODO Auto-generated method stub
		return iNursingDao.findNursingByNursId(nursId);
	}
	@Override
	public List<Nursing> findNursingByPatientId(Integer id) {
		// TODO Auto-generated method stub
		return iNursingDao.findNursingByPatientId(id);
	}

}
