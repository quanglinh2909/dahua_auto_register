package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 智能动态检测配置
*/
public class NET_CFG_SMART_MOTION_DETECT extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小
    */
    public int              dwSize;
    /**
     * 动检使能，开：TRUE 关：FALSE
    */
    public int              bEnable;
    /**
     * 动检敏感级别,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_SMART_MOTION_DETECT_SENSITIVITY_LEVEL}
    */
    public int              emMotionDetectSensitivityLevel;
    /**
     * 智能动态检测对象,参见结构体定义 {@link com.netsdk.lib.structure.NET_SMART_MOTION_DETECT_OBJECT}
    */
    public NET_SMART_MOTION_DETECT_OBJECT stuMotionDetectObject = new NET_SMART_MOTION_DETECT_OBJECT();
    /**
     * 智能跟踪是否开启
    */
    public int              bSmartTrack;
    /**
     * 智能动检模式
    */
    public byte[]           szMode = new byte[16];
    /**
     * 算法支持属性提取
    */
    public int              bFeatureEnable;
    /**
     * 智能动检检测区域个数
    */
    public int              nDetectRegionsNum;
    /**
     * 智能动检检测区域信息,参见结构体定义 {@link com.netsdk.lib.structure.NET_SMART_MOTION_DETECT_REGION}
    */
    public NET_SMART_MOTION_DETECT_REGION[] stuDetectRegions = new NET_SMART_MOTION_DETECT_REGION[8];
    /**
     * 跟踪中是否变倍
    */
    public int              bTrackingZoomEnable;

    public NET_CFG_SMART_MOTION_DETECT() {
        this.dwSize = this.size();
    }
}

