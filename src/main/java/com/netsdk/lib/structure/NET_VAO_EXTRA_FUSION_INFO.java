package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 雷达融合目标信息
*/
public class NET_VAO_EXTRA_FUSION_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 雷达目标在极坐标系中长度，单位是m，倍化1000倍的结果
    */
    public int              nRadarDistance;
    /**
     * 雷达目标在极坐标系中角度，单位是度，倍化1000倍的结果
    */
    public int              nRadarAngle;
    /**
     * 雷达目标ID
    */
    public int              nRadarID;
    /**
     * 目标融合类型: -1:未知, 0:视频检测; 1:雷达检测; 2:融合检测
    */
    public int              nObjectSource;
    /**
     * 目标运动速度大小， 单位:km/h
    */
    public double           dbSpeed;
    /**
     * 雷达目标类型的掩码
    */
    public int              nRadarObjectType;
    /**
     * 目标在雷达坐标系的X轴坐标，单位：米, 设备视角：右手方向为X轴正向，正前方为Y轴正向
    */
    public float            fCartPosX;
    /**
     * 目标在雷达坐标系的Y轴坐标，单位：米, 设备视角：右手方向为X轴正向，正前方为Y轴正向
    */
    public float            fCartPosY;
    /**
     * 目标横向运动速度, 单位:m/s, 设备视角：右手方向为x正向
    */
    public float            fSpeedX;
    /**
     * 目标纵向运动速度, 单位:m/s, 设备视角：正前方为y正向
    */
    public float            fSpeedY;
    /**
     * 雷达目标加速度ax,设备视角：右手方向为x正向；取值范围：-10~10，单位为m/s2
    */
    public float            fAccelerationX;
    /**
     * 雷达目标加速度ay,设备视角：正前方为y正向；取值范围：-10~10，单位为m/s2
    */
    public float            fAccelerationY;
    /**
     * 航向角(顺时针), 单位: 角度, 范围[0, 360)
    */
    public float            fHeadingAngle;
    /**
     * 经度数值，东经为正，西经为负。取值范围-179.9999999~180.0000000
    */
    public double           dbLongitude;
    /**
     * 纬度数值，北纬为正，南纬为负。取值范围-90.0000000~90.0000000
    */
    public double           dbLatitude;
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[1024];

    public NET_VAO_EXTRA_FUSION_INFO() {
    }
}

