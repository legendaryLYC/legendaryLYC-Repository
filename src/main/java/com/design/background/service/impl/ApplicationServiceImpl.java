package com.design.background.service.impl;

import com.design.background.entity.Application;
import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.ProjectDetail;
import com.design.background.mapper.DesignerRelationshipMapper;
import com.design.background.service.ApplicationService;
import com.design.background.service.MessageManageService;
import com.design.background.service.ProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private DesignerRelationshipMapper designerRelationshipMapper;
    @Autowired
    private MessageManageService messageManageService;
    @Autowired
    private ProjectManageService projectManageService;

     /**
     * @ param     :Application project
     * @ return    :List
     * @ Description:选择设计师
     * @Author      :高红亮
     * @ Date       :2019/2/21
     */

    @Override
    public List<Application> selectApplication(Application project) {
        List <Application> result = designerRelationshipMapper.selectApplication(project);
        return result;
    }

     /**
     * @ param     :Application project
     * @ return    :List
     * @ Description:查看已选择设计师
     * @Author      :高红亮
     * @ Date       :2019/2/21
     */

    @Override
    public List<Application> selectAccept(Long projectId) {
        List <Application> result = designerRelationshipMapper.selectAccept(projectId);
        return result;
    }

    /**
     * @ param     :Application project
     * @ return    :List
     * @ Description:接受设计师
     * @Author      :李永成
     * @ Date       :2019/2/21
     */

    @Override
    public int updateAccept(Long projectId,Long userId) {
        // 得到项目名字
        ProjectDetail project = projectManageService.selectByPrimaryKey(projectId);
        String projectName = project.getName();
        // 根据项目id和用户人的id查出该设计师的详细消息
        Application designer = designerRelationshipMapper.selectDesignerByProjectIdAndUserId(projectId,userId);
        // 调用消息接口
        Long type = 1021L;
        Map<String,String> para= new HashMap();
        para.put("${userName}",designer.getUsername());
        para.put("${projectName}",projectName);
        boolean messageResult = messageManageService.allNoticeAdd(designer.getTel(),type, para);
        // 调用接口结束
        // 更新数据库中选中的设计师
        return designerRelationshipMapper.updateAccept(projectId,userId);
    }


    /**
     * @ param     :Application project
     * @ return    :List
     * @ Description:拒绝设计师
     * @Author      :高红亮
     * @ Date       :2019/2/21
     */

    @Override
    public int updateRefuse(Long projectId,Long userId) {
        return designerRelationshipMapper.updateRefuse(projectId,userId);
    }

/*    @Override
    public int updateRemove(Long projectId,Long userId) {
        return designerRelationshipMapper.updateRemove(projectId,userId);
    }*/

    @Override
    public boolean resetLeader(Long projectId) {
        boolean flag = false ;
        int result = designerRelationshipMapper.resetLeader(projectId);
        if(result != 0){
            flag = true;
        }
        return flag;
    }
    @Override
    public boolean updateLeader(Long projectId,Long userId) {
        boolean flag = false ;
        int result = designerRelationshipMapper.updateLeader(projectId,userId);
        if(result != 0){
            flag = true;
        }
        return flag;
    }

    /**
     * @Author: 李永成
     * @Date: 2019/2/27
     * @Description:  选中设计师之后，把同类型的其他设计师拒绝
     * @Param:  Long projectId,Integer typeCode
     * @return: boolean flag
     */
    @Override
    public boolean refuseSameTypeDesigners(Long projectId,Integer typeCode,Long userId){
        // 首先根据projectid，typeCode得到所有该项目的该类型的设计师 查出之后不给id=userid选中的设计师发消息
        List<Application> designers = designerRelationshipMapper.selectDesignersByProjectIdAndTypeCode(projectId,typeCode);
        // 得到项目名字
        ProjectDetail project = projectManageService.selectByPrimaryKey(projectId);
        String projectName = project.getName();
        // 给未被选中的设计师发送消息
        for(int i = 0;i<designers.size();i++){
            if(!designers.get(i).getUserId().equals(userId)){
                // 调用消息接口
                Long type = 1020L;
                Map<String,String> para= new HashMap();
                para.put("${userName}",designers.get(i).getUsername());
                para.put("${projectName}",projectName);
                boolean messageResult = messageManageService.allNoticeAdd(designers.get(i).getTel(),type, para);
                // 调用接口结束
            }
        }
        boolean flag = false;
        Integer count = null;
        count = designerRelationshipMapper.refuseSameTypeDesigners(projectId,typeCode);
        if(count>0){
            flag = true;
        }
        return flag;
    }
}