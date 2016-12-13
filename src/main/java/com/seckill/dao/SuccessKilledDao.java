package com.seckill.dao;

import com.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by TONG on 2016/10/19.
 */
public interface SuccessKilledDao {
    int insertSuccessKilled (@Param("seckillId") long seckillId, @Param("userPhone") long userPhone );

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
