package com.design.background.controller.admin;

import com.design.background.entity.DicProjectType;
import com.design.background.entity.Friendship;
import com.design.background.service.FriendShipService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**

 * @Author     :高红亮

 * @Date       :2019/2/25

 * @Description:管理友情链接的controller

 */

@Controller
@RequestMapping("/admin")
public class FriendShipController {
    @Autowired
    private FriendShipService friendShipService;

    @RequestMapping("/friendship")
    public String shows(Model model,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) // 页面容纳条数
    {
        PageHelper.startPage(pageNo, pageSize);
        List<Friendship> showList = friendShipService.selectList();
        PageInfo<Friendship> pageInfo = new PageInfo<Friendship>(showList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/friendshipLink/linkList";
    }

    /**

     * @param     :Model model

     * @return    :添加页

     * @Description:点击添加友情链接时跳转到添加页

     * @Author       :高红亮

     * @Date       :2019/2/26

     */

    @RequestMapping(value = "/addlinkpage")
    public String addLink(Model model) {
        return "admin/friendshipLink/addLink";
    }
    /**

     * @param     :Model model

     * @return    :

     * @Description:点击编辑跳转到编辑友情链接页面

     * @Author       :高红亮

     * @Date       :2019/2/26

     */
    @RequestMapping(value = "/updatelinkpage")
    public String updateLinkPage(Model model) {
        return "admin/friendshipLink/editorLink";
    }
    /**

     * @param     :String name,String link

     * @return    :

     * @Description:添加一个友情链接

     * @Author       :高红亮

     * @Date       :2019/2/26

     */
    @ResponseBody
    @RequestMapping(value = "/insertlink")
    public String insertLink(Model model,
                             @RequestParam(value = "name", required = false, defaultValue="") String name,
                             @RequestParam(value = "link", required = false, defaultValue="") String link) {
        try {
            Friendship friendship=friendShipService.selectbyLink(name,link);
            if(friendship != null) {
                return "2"; // 代表数据库中已有该链接
            }
            else {
                boolean flag = friendShipService.insert(name,link);
                if(flag == true) {
                    return "1"; //插入成功
                }
            }
        }
        catch (Exception e) {
            return "0"; // 根据两个字段查出了两条数据。
        }
        return "0";
    }
    /**

     * @param     :

     * @return    :

     * @Description:删除一个友情链接

     * @Author       :高红亮

     * @Date       :2019/2/26

     */
    @ResponseBody
    @RequestMapping(value = "/deletelink/{id}")
    public String deletetLink(@ModelAttribute Friendship friendship) {
        String result="0";
        boolean flag=false;
        try {
            flag=friendShipService.deleteList(friendship.getId().longValue());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(flag=true) {
            result="1";
        }
        System.out.println(friendship.getId());
        return result;

    }


    @ResponseBody
    @RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
    public Integer deleteAll(@RequestBody Long[] ids) {
        try {
            for (long id : ids) {
                friendShipService.deleteList(Long.valueOf(id));
            }
            return 1;
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }

    /**

     * @param     :

     * @return    :

     * @Description:编辑一个友情链接

     * @Author       :高红亮

     * @Date       :2019/2/26

     */
//    @RequestMapping(value = "/updatelink")
//    public String updateLink(Model model,Long id,
//                                    @RequestParam(value = "name", required = false, defaultValue="") String name,
//                                    @RequestParam(value = "link", required = false, defaultValue="") String link) {
//        int friendship = friendShipService.updateByPrimaryKeySelective(id,name,link);
//        return "1";
//    }


    @ResponseBody
    @RequestMapping(value = "/updatelink", params = "save=true")
    public String updateById(Model model, @ModelAttribute(value = "friendship") Friendship friendship, HttpServletRequest request) {
        long id = friendship.getId().longValue();
//        Friendship result=new Friendship();
//        try {
////            friendShipService.deleteList(id); // 删除选中的友情链接
//            result.setName(request.getParameter("name1"));
//            result.setId(friendship.getId());
//            result.setLink(request.getParameter("link1")); // 把原来的友情链接记录下来,若更新失败重新插回到数据库中
//            Friendship friendship1=friendShipService.selectbyLink(friendship.getName(),friendship.getLink());
//            if(friendship1==null) {
        friendShipService.updateByPrimaryKeySelective(id,friendship.getName(),friendship.getLink()); // friendship代表更改过后的数据，插入到数据库中
        return "1";
//            }
//            else {
//                friendShipService.insert(friendship.getName(),friendship.getLink()); // 改变之前的值，插入到数据库中
//                return "2";
//            }
    }
//        catch (Exception e) {
//            friendShipService.insert(friendship.getName(),friendship.getLink()); // 改变之前的值，插入到数据库中
//            return "2";
//        }
//    }

    /**

     * @param     :

     * @return    :

     * @Description:根据id查看友情链接

     * @Author       :高红亮

     * @Date       :2019/2/26

     */
    @RequestMapping(value = "/selectlink/{id}")
    public String selectLink(Model model,@ModelAttribute(value = "projecttype") Friendship friendship,
                             @PathVariable("id")Long id,
                             @RequestParam(value = "name", required = false, defaultValue="") String name,
                             @RequestParam(value = "link", required = false, defaultValue="") String link) {
        friendship = friendShipService.selectByPrimaryKey(id);
        model.addAttribute("friendship", friendship);
        return "admin/friendshipLink/editorLink";
    }

}