package com.seckill.service.impl;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExcution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by TONG on 2016/10/22.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    // md5�ַ���
    private final String slat = "hsdjlkfjaslkdfjalksdjlk2342347sdjfhjads234@#$%@#Dfg23434sdfgds@#$";

    public List<Seckill> getSeckkillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        //System.out.println(seckill);

        long start = seckill.getStartTime().getTime();
        long end = seckill.getEndTime().getTime();
        long now = new Date().getTime();

        //System.out.println(start + " " + end + " " + now + " " + seckill.getStartTime().getTime());

        if (now < start || now > end) {
            return new Exposer(false, seckillId, now, start, end);
        }

        String md5 = getMD5(seckillId);// Todo
        return new Exposer(true, md5, seckillId);
    }

    @Transactional
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("Seckill data rewrite!");
        }

        // ִ����ɱ�߼�������棬��¼������Ϊ
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillCloseException("Seckill is closed!");
            } else {
                // ��¼������Ϊ
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);

                if (insertCount <= 0) {
                    throw new RepeatKillException("Repeated!");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExcution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("Seckill inner error: " + e.getMessage());
        }

    }

    private String getMD5 (long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());

        return md5;
    }
}
