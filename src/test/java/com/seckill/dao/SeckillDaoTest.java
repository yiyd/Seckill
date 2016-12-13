package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by TONG on 2016/10/19.
 * 需要配置spring 和 junit 的整合
 * junit 启动时回家再SpringIOC容器
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    // 注入Dao依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount = " + updateCount);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        System.out.println("Test");
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> resultSet = seckillDao.queryAll(0, 100);

        for (int i = 0; i < resultSet.size(); i++) {
            System.out.println(resultSet.get(i));
        }
    }
    /* 该错误是因为 接口在定义时Java没有保存形参的记录，我们需要通过注解@Param
    org.mybatis.spring.MyBatisSystemException:
    nested exception is org.apache.ibatis.binding.BindingException:
    Parameter 'offset' not found. Available parameters are [1, 0, param1, param2]
     */
}