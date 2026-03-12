package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 加热器
*/
public class CFG_PTZ_HEATER extends NetSDKLib.SdkStructure
{
    /**
     * 是否支持加热器控制
    */
    public int              bSupport;
    /**
     * 是否支持自动关闭控制
    */
    public int              bSupportAutoClose;
    /**
     * 支持的加热器类型个数
    */
    public int              nSupportTypesNum;
    /**
     * 支持的加热器类型:"Total":整机加热, "Thermal":热成像镜头加热器, "Visual":可见光镜头加热器, "Demisting":除雾除冰加热器, "Ptz":云台加热器, "Shield":护罩加热器
    */
    public BYTE_ARRAY_32[]  szSupportTypes = new BYTE_ARRAY_32[8];
    /**
     * 加热器模式个数
    */
    public int              nModeNum;
    /**
     * 加热器模式: "Close":关闭, "Auto":自动, "Manual":手动, "Time":"定时"
    */
    public BYTE_ARRAY_32[]  szMode = new BYTE_ARRAY_32[8];
    /**
     * 是否支持手动控制
    */
    public int              bSupportManualControl;
    /**
     * 手动控制运行时间范围[最小时间，最大时间]单位/分钟
    */
    public int[]            nManualControlPeriod = new int[2];
    /**
     * 开启加热器温度范围[最小温度，最大温度], 单位：0.1摄氏度
    */
    public int[]            nHeaterTempLowRange = new int[2];
    /**
     * 关闭加热器温度范围[最小温度，最大温度], 单位：0.1摄氏度
    */
    public int[]            nHeaterTempHighRange = new int[2];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1020];

    public CFG_PTZ_HEATER() {
        for(int i = 0; i < szSupportTypes.length; i++){
            szSupportTypes[i] = new BYTE_ARRAY_32();
        }
        for(int i = 0; i < szMode.length; i++){
            szMode[i] = new BYTE_ARRAY_32();
        }
    }
}

