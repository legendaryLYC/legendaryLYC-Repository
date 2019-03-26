package com.design.background.service;

import com.design.background.entity.Application;
import com.design.background.entity.DesignerRelationship;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**

 * @ Author     :高红亮

 * @ Date       :2019/2/18

 * @ Description:这是返回报名详情的service

*/

@Transactional
public interface ApplicationService {

    /**

     * @param     :Application project

     * @return    :List<Application>

     * @Description:选择设计师

     * @Author       :高红亮

     * @Date       :2019/2/25

    */

    List<Application> selectApplication(Application project);

    /**

     * @param     :Long projectId

     * @return    :List<Application>

     * @Description:查看选中设计师

     * @Author       :高红亮

     * @Date       :2019/2/25

    */

    List<Application> selectAccept(Long projectId);

    /**

     * @param     :Long projectId,Long userId

     * @return    :int

     * @Description:接受设计师

     * @Author       :高红亮

     * @Date       :2019/2/25

    */

    int updateAccept(Long projectId,Long userId);


    /**

     * @param     :Long projectId,Long userId

     * @return    :int

     * @Description:拒绝或取消选中设计师的方法

     * @Author       :高红亮

     * @Date       :2019/2/25

    */

    int updateRefuse(Long projectId,Long userId);

    boolean resetLeader (Long projectId);

    boolean updateLeader(Long projectId,Long userId);

    /**
    * @Author: 李永成
    * @Date: 2019/2/27
    * @Description:  选中设计师之后，把同类型的其他设计师拒绝
    * @Param:  Long projectId,Integer typeCode
    * @return: boolean flag
    */
    boolean refuseSameTypeDesigners(Long projectId,Integer typeCode,Long userId);
}
