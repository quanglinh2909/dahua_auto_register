package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 远程球机联动使能信息
*/
public class NET_REMOTESDLINK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 被控制的设备ip
    */
    public byte[]           szSDLinkIP = new byte[32];
    /**
     * 联动使能
    */
    public int              bRadarLink;
    /**
     * 被控制设备的通道ID，雷达与联动设备通过IVSS/IVD级联到平台时使用，平台端IP不一定有效。 此处IVSS/IVD转发时补充ChannelID,平台通过ChannelID获取雷达与联动设备绑定关系
    */
    public int              nChannelID;
    /**
     * 雷达和球机校准方式,"GPSCalibration":GPS校准方式,雷达校准自身北向角,球机GPS和PT校准;"IntegratedCalibration":一体安装校准方式,只使用一个校准点,雷达极距和极角校准适用于雷球一体安装;"MultiPointCalibration":多点校准方式,适合雷达和球机分开安装。
    */
    public byte[]           szCalibrationMode = new byte[32];
    /**
     * 球机细节相机通道号
    */
    public int              nSDLinkChannel;
    /**
     * 保留
    */
    public byte[]           byReserved = new byte[436];

    public NET_REMOTESDLINK_INFO() {
    }
}

