package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 云台连续移动,枪球球联动专用对应结构 DH_EXTPTZ_INTELLI_TRACKMOVE
*/
public class PTZ_CONTROL_INTELLI_TRACKMOVE extends NetSDKLib.SdkStructure
{
    public int              dwSize;
    /**
     * 通道号
    */
    public int              nChannelID;
    /**
     * 移动标识位,0:起始locate定位使用,speed速度无效,position的变倍值有效,1:持续跟踪移动使用,speed速度无效,position的变倍值无效,2:持续跟踪移动使用,speed速度有效,position的变倍值无效
    */
    public int              nFlag;
    /**
     * 云台绝对移动位置,参见结构体定义 {@link com.netsdk.lib.structure.PTZ_LOCATION_SPACE_UNIT}
    */
    public PTZ_LOCATION_SPACE_UNIT stuPosition = new PTZ_LOCATION_SPACE_UNIT();
    /**
     * 云台运行速度,参见结构体定义 {@link com.netsdk.lib.structure.PTZ_LOCATION_SPEED_UNIT}
    */
    public PTZ_LOCATION_SPEED_UNIT stuSpeed = new PTZ_LOCATION_SPEED_UNIT();
    /**
     * 是否下发跟踪物体信息
    */
    public int              bTrackObject;
    /**
     * 跟踪物体信息,参见结构体定义 {@link com.netsdk.lib.structure.PTZ_LOCATION_TRACK_OBJECT}
    */
    public PTZ_LOCATION_TRACK_OBJECT stuTrackObject = new PTZ_LOCATION_TRACK_OBJECT();

    public PTZ_CONTROL_INTELLI_TRACKMOVE() {
        this.dwSize = this.size();
    }
}

