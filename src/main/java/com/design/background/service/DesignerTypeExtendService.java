package com.design.background.service;

import java.util.ArrayList;

import com.design.background.entity.DesigntypeExtend;

public interface DesignerTypeExtendService {
	/**
	 * 根据id更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(DesigntypeExtend record);
	
	 /**
	  * 根据搜索设计师专业信息
	  * @param userid
	  * @return
	  */
    DesigntypeExtend selectByUserid(Long userid);
    /**
     * 获取设计师种类
     * @param id
     * @return
     */
    ArrayList getResult(Long id);

}
