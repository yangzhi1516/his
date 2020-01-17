package com.his.entity;

import java.io.Serializable;
/**
 * 	药物生产商表
 * @author 杨培源
 *
 */
public class Manufacturer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer manufacturerId;	//药物生产商编号
    private String manufacturerName;	//生产商姓名
    private String manufacturerTelephone;	//聊系电话
    
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName == null ? null : manufacturerName.trim();
    }

    public String getManufacturerTelephone() {
        return manufacturerTelephone;
    }

    public void setManufacturerTelephone(String manufacturerTelephone) {
        this.manufacturerTelephone = manufacturerTelephone == null ? null : manufacturerTelephone.trim();
    }
}