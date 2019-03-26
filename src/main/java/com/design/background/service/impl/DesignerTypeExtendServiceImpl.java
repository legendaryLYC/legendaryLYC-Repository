package com.design.background.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.DesigntypeExtend;
import com.design.background.mapper.DesigntypeExtendMapper;
import com.design.background.service.DesignerTypeExtendService;

@Service("DesignerTypeExtendService")
public class DesignerTypeExtendServiceImpl implements DesignerTypeExtendService {
	 @Autowired
	 private DesigntypeExtendMapper designtypeExtendMapper;
	
	@Override
	public int updateByPrimaryKeySelective(DesigntypeExtend record) {
		// TODO Auto-generated method stub
		return designtypeExtendMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public DesigntypeExtend selectByUserid(Long userid) {
		// TODO Auto-generated method stub
		return designtypeExtendMapper.selectByUserid(userid);
	}
	  /**
     * 分割代码
     * @param id
     * @return
     */
    public  ArrayList getResult(Long id) {
    	DesigntypeExtend designtypeExtend = designtypeExtendMapper.selectByUserid(id);
		String type = designtypeExtend.getCode();
		ArrayList List = new ArrayList(); 
		if(type == null)
		{
			return List;
		}
		String[] arr = type.split(",");
		for(int i = 0;i<arr.length;i++)
		{	List.add(Integer.parseInt(arr[i]));
		}
		return List;
    }

}
