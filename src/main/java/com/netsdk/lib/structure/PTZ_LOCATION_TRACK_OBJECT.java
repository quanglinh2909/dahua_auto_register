package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 跟踪物体信息
*/
public class PTZ_LOCATION_TRACK_OBJECT extends NetSDKLib.SdkStructure
{
    /**
     * 指令, 0:无效, 1:检测开启,自主跟踪开启, 2:检测开启,自主跟踪关闭, 3:检测关闭,跟踪关闭, 4:检测关闭,普通抓图上报
    */
    public int              nCommand;
    /**
     * 联动物体ID
    */
    public int              nLinkObjectID;
    /**
     * 联动事件ID
    */
    public int              nLinkEventID;
    /**
     * 报警类型, 第0bit位表示是否超速(1:超速, 0:未超速), 第1bit位表示是否AIS匹配, 第2bit位表示是否禁行, 第3bit位表示是否逆行
    */
    public int              nAlarmType;
    /**
     * 速度，单位米/秒，扩大100倍
    */
    public int              nSpeedValue;
    /**
     * 经度，单位百万分之一度
    */
    public int              nLongitude;
    /**
     * 纬度，单位百万分之一度
    */
    public int              nLatitude;
    /**
     * 目标距离，单位米，扩大100倍表示
    */
    public int              nDistance;
    /**
     * 跟踪物体类型
    */
    public byte[]           szObjectType = new byte[16];
    /**
     * 外部设备（如雷达）识别到目标上报报警的时间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.NET_TIME}
    */
    public NetSDKLib.NET_TIME stuLinkRealUTC = new NetSDKLib.NET_TIME();
    /**
     * 航向：正北方向为0° 顺时针为正，范围 0-360°，扩大100倍表示
    */
    public int              nAzimuth;
    /**
     * 目标点唯一id
    */
    public byte[]           szLinkTargetUUID = new byte[32];
    /**
     * 保留字节
    */
    public byte[]           szReserved = new byte[1028];

    public PTZ_LOCATION_TRACK_OBJECT() {
    }
}

