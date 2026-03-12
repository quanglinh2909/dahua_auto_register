package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 纳雷雷达配置
*/
public class NET_NALEI_RADAR_CONFIG extends NetSDKLib.SdkStructure
{
    /**
     * 道路模式,0:未知,1:路段模式,2:路口模式,3:全息路口模式,4:隧道模式,5:地下车库模式,6:园区测速模式
    */
    public int              nSceneMode;
    /**
     * 道路方向,0:未知,1:来向,2:去向,3:双向
    */
    public int              nDetectMode;
    /**
     * 工作模式,0:未知,1:调试模式,2:正常工作模式
    */
    public int              nWorkMode;
    /**
     * 道路总数,1-8
    */
    public int              nRoadSum;
    /**
     * ID设置,0-4
    */
    public int              nIDset;
    /**
     * 停止目标消失时间,0:关闭,非0时:停止目标消失的时间
    */
    public int              nStopTargetDisappearTime;
    /**
     * 渠化道路开关,0:未知,1:关闭,2:开启
    */
    public int              nCanalizeSwitch;
    /**
     * 渠化道路个数,1-8
    */
    public int              nCanalizeLaneNumber;
    /**
     * 正常道路/渠化道路隔离带位置,0-8(0表示没有隔离带,1表示隔离带在1车道的右侧)
    */
    public int              nDivideIslandLocation;
    /**
     * 有效辐射功率,0:默认,1:欧盟CE认证中无线电设备指令(RED)-2014/53/EU对EIRP的规定
    */
    public int              nEIRP;
    /**
     * 8个车道的宽度,单位m
    */
    public float[]          fLaneWidth = new float[8];
    /**
     * 车道的宽度数量
    */
    public int              nLaneWidthNum;
    /**
     * 水平偏移,单位m
    */
    public float            fHorizonOffset;
    /**
     * 角度修正,单位度
    */
    public float            fAngleModify;
    /**
     * 架设高度,0-25,单位m
    */
    public float            fHeight;
    /**
     * 停止线距离,20-60
    */
    public double           dbStopLine;
    /**
     * 灵敏度设置,0-3
    */
    public double           dbSensitive;
    /**
     * 渠化道路宽度,单位米(m),0.0-20.0
    */
    public double[]         dbCanalizeLaneWidth = new double[8];
    /**
     * 渠化道路水平偏移,单位米(m),渠化道路相对正常道路偏移距离,左负右正
    */
    public double           dbCanalizeHorizontalOffset;
    /**
     * 渠化上边界,单位米(m)
    */
    public double           dbCanalizeUpperBoundary;
    /**
     * 渠化下边界,单位米(m)
    */
    public double           dbCanalizeLowerBoundary;
    /**
     * 渠化隔离带宽度
    */
    public double           dbCanalizeDivideIslandWidth;
    /**
     * 正常道路隔离带宽度
    */
    public double           dbDivideIslandWidth;
    /**
     * 渠化道路宽度数量
    */
    public int              nCanalizeLaneWidthNum;
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1020];

    public NET_NALEI_RADAR_CONFIG() {
    }
}

