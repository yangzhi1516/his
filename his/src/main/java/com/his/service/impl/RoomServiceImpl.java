package com.his.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dao.IRoomDao;
import com.his.entity.Room;
import com.his.service.RoomService;
@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private IRoomDao iRoomDao;
	
	public void setiRoomDao(IRoomDao iRoomDao) {
		this.iRoomDao = iRoomDao;
	}

	@Override
	public List<Room> findRoomByDepartmentId(Integer id) {
		// TODO Auto-generated method stub
		return iRoomDao.findRoomByDepartmentId(id);
	}

	@Override
	public int updateRoomNumById(Integer roomid) {
		// TODO Auto-generated method stub
		return iRoomDao.updateRoomNumById(roomid);
	}

	@Override
	public int updateReduceRoomNumByDepartmentId(Integer departmentid) {
		// TODO Auto-generated method stub
		return iRoomDao.updateReduceRoomNumByDepartmentId(departmentid);
	}

}
