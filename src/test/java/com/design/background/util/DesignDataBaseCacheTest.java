package com.design.background.util;

import com.design.background.BackgroundApplicationTests;
import com.design.background.common.util.DesignDataBaseCache;
import com.design.background.entity.DicProjectScale;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

/**
 * FileName: DesignDataBaseCacheTest
 * Author:   wpw
 * Date:     19-2-26 上午11:42
 * Description: 数据缓存测试
 * History:
 * <author>          <time>          <version>          <desc>
 * wpw             19-2-26            1.0
 */
public class DesignDataBaseCacheTest extends BackgroundApplicationTests {


    @Test
    public void testGetAllDicProjectScale(){
        List<DicProjectScale> dicProjectScales = DesignDataBaseCache.getAllDicProjectScale();
        dicProjectScales.forEach(s->{
            System.out.println(s);
        });

    }
    @Test
    public void testGetDicProjectScale(){
       System.out.println(DesignDataBaseCache.getDicProjectScale(1000));
    }

    @Test
    public void testsetDicProjectScale(){
        DicProjectScale projectScale = DesignDataBaseCache.getDicProjectScale(1000);
        System.err.println("====1===="+projectScale);
        projectScale.setDescription("测试数据");
        DesignDataBaseCache.setDicProjectScale(projectScale);
        System.err.println("====2===="+DesignDataBaseCache.getDicProjectScale(1000));
    }

    @Test
    public void testRemoveDicProjectScale(){
        DesignDataBaseCache.getAllDicProjectScale().forEach(s->{
            System.err.println(s);
        });
        DesignDataBaseCache.removeDicProjectScale(1000);
        System.err.println("--------------------------------------------");
        DesignDataBaseCache.getAllDicProjectScale().forEach(s->{
            System.err.println(s);
        });
    }
}
 
