package com.design.background.mapper;

import com.design.background.entity.Application;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.DesignerRelationshipForm;
import com.design.background.model.MyProjectModel;
import com.design.background.model.StatisticalSearchUserBalanceModel;

public interface DesignerRelationshipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DesignerRelationship record);

    int insertSelective(DesignerRelationship record);

    DesignerRelationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesignerRelationship record);

    int updateByPrimaryKey(DesignerRelationship record);

    /**
     * @ param     :Application project
     * @ return    :List
     * @ Description:选择设计师
	 * @Author       :高红亮
     * @ Date       :2019/2/21
    */

    List<Application> selectApplication(Application project);

    /**
     * @ param     :Long projectId
     * @ return    :List
     * @ Description:查看选中设计师
	 * @Author       :高红亮
     * @ Date       :2019/2/21
    */

    List<Application> selectAccept(@Param(value = "projectId")Long projectId);

    /**
     * @param     :Long projectId
     * @return    :int
     * @Description:接受设计师
	 * @Author       :高红亮
     * @Date       :2019/2/21
    */

    int updateAccept(@Param(value = "projectId")Long projectId,@Param(value = "userId")Long userId);


    /**
     * @param     :Long projectId
     * @return    :int
     * @Description:拒绝设计师
     * @Author       :高红亮
     * @Date       :2019/2/21
    */

    int updateRefuse(@Param(value = "projectId")Long projectId,@Param(value = "userId")Long userId);

//    int updateRemove(@Param(value = "projectId")Long projectId,@Param(value = "userId")Long userId);

    List<DesignerRelationship> selectByUserId(Long userId);

    /**
     *
     * @Author HRX
     * @Title: selectByUserId
     * @Description: TODO 通过用户的id查询我的项目
     * @param @param userId
     * @param @return
     * @return List<DesignerRelationship>
     * @Date 2019年2月19日 下午7:49:18
     * @throws
     */
    List<MyProjectModel> selectByUserId(@Param(value="project")ProjectDetail project);
    /**

    /**
     * 判断该项目的该种设计师类型是否已经被选中
     * 若已经选中,返回1,否则返回0
     * @author 孟晓冬
     */
    int isSelected(@Param(value="record")DesignerRelationship record);

    int deleteByUserIdAndProjectId(@Param(value = "userId")Long userId,@Param(value = "projectId")Long projectId); // 取消报名

    /**
     * @Description: 用来查询用户是否报名此项目
     * @Param:
     * @return: ProjectForm
     * @Author: 李紫林
     * @Date: 2019/2/19
     */
    DesignerRelationship selectJoinStatus(@Param(value="userId")Long userId,@Param(value = "projectId")Long projectId);
    
    /**
 	 * @description 查找截至到上周包含本周之前每天投标量的总数目
 	 * @return int
 	 * @author 周天
 	 */
 	 int  selectStatisticalTenderLastweek(int lastIndex);
 	 
     /**
  	 * @description 查找截至到本周包含本周之前每天投标量的总数目
  	 * @return int
  	 * @author 周天
  	 */
  	 int  selectStatisticalTenderThisweek(int thisIndex);
  	 
     /**
  	 * @description 查找截至到本月包含本月之前每个月投标量的总数目
  	 * @return int
  	 * @author 周天
  	 */
  	 int  selectStatisticalTenderByMonth(int monthIndex);
  	 
 	 /**
 	 * @description 查找投标量前十的设计师用户名
 	 * @return StatisticalSearchUserBalanceModel
 	 * @author 周天
 	 */
 	List<StatisticalSearchUserBalanceModel> selectStatisticalTenderTopDesign();
 	
 	 /**
 	 * @description 查找被投标量前十的项目名称
 	 * @return List<StatisticalSearchUserCountModel>
 	 * @author 周天
 	 */
 	 List<StatisticalSearchUserBalanceModel> selectStatisticalTenderTopProject();

	List <DesignerRelationship> selectedDesigner(Long projectId);

	int resetLeader(Long projectId);

	int updateLeader(@Param(value = "projectId")Long projectId,@Param(value = "userId")Long userId);

	int selectCountByProjectId(Long projectId);

	/**
	 * 根据项目id获取所有报名信息
	 * @author 孟晓冬
	 * @param projectId
	 * @return
	 */
	List<DesignerRelationshipForm> selectDesignersByProjectId(@Param(value = "projectId")Long projectId);
	
	/**
	 * 根据项目id获取所有报名的类型code值集合
	 * @author 孟晓冬
	 * @param projectId
	 * @return
	 */
	List<Integer> selectTypesByProjectId(@Param(value = "projectId")Long projectId);

	/**
	 * @Author: 李永成
	 * @Date: 2019/2/27
	 * @Description:  选中设计师之后，把同类型的其他设计师拒绝
	 * @Param:  Long projectId,Integer typeCode
	 * @return: boolean flag
	 */
	Integer refuseSameTypeDesigners(@Param(value = "projectId")Long projectId,@Param(value = "typeCode")Integer typeCode);

	/**
	* @Author: 李永成
	* @Date: 2019/3/1
	* @Description:  根据项目id和设计师类型，得到所有某个专业的设计师列表，发送拒绝消息
	* @Param:  projectId,typecode
	* @return:  list<DesignerRelationship>
	*/
	List<Application> selectDesignersByProjectIdAndTypeCode(@Param(value = "projectId")Long projectId,@Param(value = "typeCode")Integer typeCode);

	/**
	* @Author: 李永成
	* @Date: 2019/3/1
	* @Description:
	* @Param:
	* @return:
	*/
	Application selectDesignerByProjectIdAndUserId(@Param(value = "projectId")Long projectId,@Param(value = "userId")Long userId);
}