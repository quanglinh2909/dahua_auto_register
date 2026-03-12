package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * @beirf 修正信息
*/
public class NET_SPEED_MODIFY_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 速度修正起始值，km/h
    */
    public int              nRangeBegin;
    /**
     * 速度修正结束值，需要大于同组速度修正起始值，km/h
    */
    public int              nRangeEnd;
    /**
     * 速度修正值，km/h，范围是[-10,10]
    */
    public int              nSpeed;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[68];

    public NET_SPEED_MODIFY_INFO() {
    }
}

