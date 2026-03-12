package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能动态检测对象
*/
public class NET_SMART_MOTION_DETECT_OBJECT extends NetSDKLib.SdkStructure
{
    /**
     * 动态检测人使能
    */
    public int              bHuman;
    /**
     * 动态检测车使能
    */
    public int              bVehicle;
    /**
     * 动态检测动物使能
    */
    public int              bAnimal;
    /**
     * 保留字节
    */
    public byte[]           byReserved = new byte[1016];

    public NET_SMART_MOTION_DETECT_OBJECT() {
    }
}

