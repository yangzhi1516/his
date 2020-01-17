package com.his.dao;

import java.util.List;

import com.his.entity.Room;

public interface IRoomDao {
	/**
	 *  根据科室编号查询病房
	 * @param id
	 * @return
	 */
	List<Room> findRoomByDepartmentId(Integer id);
	
	/**
	 * 	根据房间id修改房间使用数量
	 * @param roomid
	 * @return
	 */
	int updateRoomNumById(Integer roomid);
	
	/**
	 * 	用户出院时根据科室编号修改房间使用数量
	 * @param departmentid
	 * @return
	 */
	int updateReduceRoomNumByDepartmentId(Integer departmentid);
}
