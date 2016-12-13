package com.seckill.service;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by TONG on 2016/10/22.
 * 业务接口
 * 1. 方法定义粒度，参数，返回类型/异常
 * 2.
 *
 */
public interface SeckillService {
    List<Seckill> getSeckkillList();

    Seckill getById (long seckillId);

    /*
        秒杀开始时输出秒杀地址，否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl (long seckillId);

    SeckillExcution executeSeckill (long seckillId, long userPhone, String md5)
            throws SeckillException,SeckillCloseException, RepeatKillException;
}
