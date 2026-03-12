package com.netsdk.lib.structure;
import com.sun.jna.Pointer;

import com.netsdk.lib.NetSDKLib;
/**
 * 雷达检测对象
 *
 * @author ： 251823
 * @since ： Created in 2021/7/21
 */
public class NET_RADAR_DETECT_OBJECT extends NetSDKLib.SdkStructure {
	 /**
     *  物体ID
     */
    public int              nObjectID;
    /**
     *  物体类型 ,参考枚举{ @link EM_RADAR_DETECT_OBJECT_TYPE }
     */
    public int              emObjectType;
    /**
     * 目标类型编号
    */
    public int              nTypeInt;
    /**
     * 目标在全景图中的 X 坐标， 单位像素
    */
    public int              nPositionX;
    /**
     * 目标在全景图中的 Y 坐标， 单位像素
    */
    public int              nPositionY;
    /**
     * 目标框的左上角 X 坐标， 单位像素
    */
    public int              nRectX;
    /**
     * 目标框的左上角 Y 坐标， 单位像素
    */
    public int              nRectY;
    /**
     * 目标卡的宽度，单位像素
    */
    public int              nRectWidth;
    /**
     * 目标卡的高度，单位像素
    */
    public int              nRectHeight;
    /**
     * 目标的方位角，以全景图像 0 度为参考
    */
    public float            fAzimuth;
    /**
     * 目标的俯仰角，以全景图像垂直方向上的中间点为 0 度参考
    */
    public float            fPitchAngle;
    /**
     * 目标的距离，单位米
    */
    public int              nDistance;
    /**
     * 目标所在位置的经度
    */
    public float            fLongitude;
    /**
     * 目标所在位置的纬度
    */
    public float            fLatitude;
    /**
     * 目标的速度， 单位米/秒
    */
    public float            fSpeed;
    /**
     * 目标切片的宽度， 单位像素
    */
    public int              nImageWidth;
    /**
     * 目标切片的高度， 单位像素
    */
    public int              nImageHeight;
    /**
     * 目标切片路径
    */
    public byte[]           szFilePath = new byte[260];
    /**
     * 切片在二进制数据块中的偏移
    */
    public int              nOffset;
    /**
     * 切片大小，单位字节
    */
    public int              nLength;
    /**
     * 目标所属的报警区域
    */
    public byte[]           szArea = new byte[256];
    /**
     * 目标的轨迹点数组，用于显示目标移动路径,参见结构体定义 {@link com.netsdk.lib.structure.NET_POINT_EX2}
    */
    public Pointer          pstuTrajectory;
    /**
     * 目标的轨迹点数组数量
    */
    public int              nTrajectoryNum;
    /**
     *  预留字节 
     */
    public byte[]           byReserved = new byte[436-NetSDKLib.POINTERSIZE];
}

