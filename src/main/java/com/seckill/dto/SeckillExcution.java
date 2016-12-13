package com.seckill.dto;

import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStateEnum;

/**
 * ��װ��ɱִ�к�Ľ��
 * Created by TONG on 2016/10/22.
 */
public class SeckillExcution {
    private long seckillId;

    // ��ɱ״̬���
    private int state;

    // ��ɱ״̬��ʾ
    private String stateInfo;

    private SuccessKilled successKilled;

    // ��ɱ�ɹ����ع���
    public SeckillExcution(long seckillId, SeckillStateEnum state, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
        this.successKilled = successKilled;
    }

    // ��ɱʧ�ܷ��ع���
    public SeckillExcution(long seckillId, SeckillStateEnum state) {
        this.seckillId = seckillId;
        this.stateInfo = state.getStateInfo();
        this.state = state.getState();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
