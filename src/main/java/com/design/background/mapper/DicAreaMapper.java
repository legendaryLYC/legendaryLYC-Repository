package com.design.background.mapper;
import com.design.background.entity.DicArea;
import com.design.background.entity.DicProvince;
import com.design.background.model.AreaModel;

import java.util.List;


public interface DicAreaMapper {
	
	List<DicArea> selectAllArea();

    DicArea selectAreaById(String id);

    List<DicArea> selectAreaWithCity(String id);
    
    /**
     * 
    * @Author HRX 
    * @Title: getProjectArea  
    * @Description: TODO 通过地区的area_id查询所属省市 
    * @param @param areaId
    * @param @return   
    * @return AreaModel    
    * @Date 2019年2月20日 下午6:01:43
    * @throws
     */
    AreaModel getProjectArea(Integer areaId);
    
}
