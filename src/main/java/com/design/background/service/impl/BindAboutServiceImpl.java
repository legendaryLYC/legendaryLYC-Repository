package com.design.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.DicColumn;
import com.design.background.mapper.DicColumnMapper;
import com.design.background.service.BindAboutService;

@Service("bindAboutService")
public class BindAboutServiceImpl implements BindAboutService{
	@Autowired
	private DicColumnMapper dicColumnMapper;
	
	/**
	 * 
	* @Author HRX 
	* @Title: selectAll  
	* @Description: TODO 查询所有的文章
	* @param @param description
	* @param @return      
	* @Date 2019年2月16日 下午2:58:26
	* @throws
	 */
	@Override
	public List<DicColumn> selectAll(String description) {
		// TODO Auto-generated method stub
		return dicColumnMapper.selectAll(description);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: deleteByPrimaryKey  
	* @Description: TODO 通过id删除文章
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午2:59:18
	* @throws
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dicColumnMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: insert  
	* @Description: TODO 插入文章
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午2:59:31
	* @throws
	 */
	@Override
	public int insert(DicColumn record) {
		// TODO Auto-generated method stub
		return dicColumnMapper.insert(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: insertSelective  
	* @Description: TODO 选择性插入文章
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午2:59:45
	* @throws
	 */
	@Override
	public int insertSelective(DicColumn record) {
		// TODO Auto-generated method stub
		return dicColumnMapper.insertSelective(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: selectByPrimaryKey  
	* @Description: TODO 通过主键查询
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午3:00:12
	* @throws
	 */
	@Override
	public DicColumn selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dicColumnMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: updateByPrimaryKeySelective  
	* @Description: TODO 通过id选择性更改除内容以外的字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:00:39
	* @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(DicColumn record) {
		// TODO Auto-generated method stub
		return dicColumnMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: updateByPrimaryKeyWithBLOBs  
	* @Description: TODO 通过id选择性更改所有字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:01:18
	* @throws
	 */
	@Override
	public int updateByPrimaryKeyWithBLOBs(DicColumn record) {
		// TODO Auto-generated method stub
		return dicColumnMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: updateByPrimaryKey  
	* @Description: TODO 通过主键更新除内容以外的其他字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:01:54
	* @throws
	 */
	@Override
	public int updateByPrimaryKey(DicColumn record) {
		// TODO Auto-generated method stub
		return dicColumnMapper.updateByPrimaryKey(record);
	}
}
