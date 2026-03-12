package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
import com.sun.jna.Pointer;
/**
 * 均值波动次数超限报警信息
*/
public class NET_VOLATILITY_ALARM_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 报警量化均值
    */
    public float            fAverageValue;
    /**
     * 量化值超限数值总数量，透传值，pfAlarmValue不为空时，一般等于nAlarmValueValidNum, 数组太大的时候只传总数量，即pfAlarmValue为空，且nAlarmValueSize不为0，表示只传数量的情况
    */
    public int              nAlarmValueSize;
    /**
     * 量化值超限数组，内存由NetSDK申请释放
    */
    public Pointer          pfAlarmValue;
    /**
     * 量化值超限数组元素有效个数，关联NetSDK申请的空间，必须用它访问pfAlarmValue
    */
    public int              nAlarmValueValidNum;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_VOLATILITY_ALARM_INFO() {
    }
}

