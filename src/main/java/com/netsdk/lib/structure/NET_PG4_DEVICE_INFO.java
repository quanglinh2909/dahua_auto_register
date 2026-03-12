package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 对应CLIENT_StartSearchDevicesEx接口TTLV回调
*/
public class NET_PG4_DEVICE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * sn
    */
    public byte[]           szSN = new byte[64];
    /**
     * 设备类型
    */
    public byte[]           szDevClass = new byte[16];
    /**
     * 设备子类型
    */
    public byte[]           szSubClass = new byte[16];
    public byte[]           szType = new byte[64];
    /**
     * 厂商
    */
    public byte[]           szVendor = new byte[32];
    public byte[]           szMachineName = new byte[64];
    /**
     * 0未初始化,1已初始化
    */
    public int              bInit;
    /**
     * 参见结构体定义 {@link com.netsdk.lib.structure.NET_PG4_NET_INFO}
    */
    public NET_PG4_NET_INFO stIPv4 = new NET_PG4_NET_INFO();
    /**
     * 参见结构体定义 {@link com.netsdk.lib.structure.NET_PG4_SVC_INFO}
    */
    public NET_PG4_SVC_INFO stSvc = new NET_PG4_SVC_INFO();
    public int              nClientTagNum;
    public int[]            pnClientTag = new int[16];
    /**
     * 设备市场区域信息
    */
    public byte[]           szAbroadInfo = new byte[16];
    /**
     * 初始化状态
    */
    public int              nInitStatus;
    /**
     * 密码找回的版本号
    */
    public int              nFindVersion;
    /**
     * 网络搜索方式，BCast，Uni，Multi，Uni6，Multi6
    */
    public byte[]           szFrom = new byte[16];
    /**
     * 支持的找回密码方式
    */
    public byte[]           szFindPwd = new byte[8];
    /**
     * 设备所属的内部产品系列型号
    */
    public byte[]           szSerialModel = new byte[32];
    /**
     * 设备软件版本
    */
    public byte[]           szVersion = new byte[128];
    /**
     * 本地视频输入通道数
    */
    public int              nVideoInputChannels;
    /**
     * 远程视频输入通道数
    */
    public int              nRemoteVideoInputChannels;
    /**
     * 视频输出通道数
    */
    public int              nVideoOutputChannels;
    /**
     * 传输产品中设备主从角色
    */
    public int              nRole;
    /**
     * 客户端tag标识个数
    */
    public int              nClientTagsCount;
    /**
     * 客户端tag标识, 1: WL，支持WiLynk 系列APP 2: Care，支持DoLynkCare 系列APP
    */
    public int[]            nClientTags = new int[16];
    /**
     * 扩展字段
    */
    public byte[]           szReserved = new byte[1688];

    public NET_PG4_DEVICE_INFO() {
    }
}

