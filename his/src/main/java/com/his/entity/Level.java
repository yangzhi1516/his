package com.his.entity;

import java.io.Serializable;

public class Level implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer levelId;	//学历编号

    private String levelName;	//学历水平

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }
}