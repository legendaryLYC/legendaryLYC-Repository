package com.design.background.service;

import java.util.List;

import com.design.background.entity.DicDefaultRate;
import com.design.background.entity.MoneyDistribution;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.MoneyListForm;
import com.design.background.model.AreaModel;
import com.design.background.model.MyProjectModel;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MyProjectService {
	int deleteByPrimaryKey(Long id);

    int insert(ProjectDetail record);

    int insertSelective(ProjectDetail record);

    ProjectDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectDetail record);

    int updateByPrimaryKey(ProjectDetail record);
    
    List<MyProjectModel> selectByUserId(ProjectDetail project);
    
    Integer selectCounts(Long userId);
    
    AreaModel getProjectArea(Integer integer);
    
    DicDefaultRate selectRateByPrimaryKey(Long id);
    
    int insertSelective(MoneyDistribution record);

    int updateByProjectIdSelective(MoneyDistribution record);

    List<MoneyDistribution> selectMoneyByPrimaryKey(Long id);

    void creatProject(ProjectDetail project, String[] designerTypes
            , MultipartFile[] files, MoneyListForm moneyListForm,MultipartFile image);

    void updateProject(ProjectDetail project, String[] designerTypes
            , MultipartFile[] files, MoneyListForm moneyListForm,MultipartFile image,String deleteIds);

    void draft(ProjectDetail project, String[] designerTypes
            , MultipartFile[] files, MoneyListForm moneyListForm,MultipartFile image);
}
