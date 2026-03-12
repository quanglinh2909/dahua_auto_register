package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 视频日夜配置切换模式, 对应枚举 NET_EM_CFG_VIDEO_IN_MODE
*/
public class NET_CFG_VIDEO_IN_MODE_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 结构体大小, 必须赋值
    */
    public int              dwSize;
    /**
     * 当前配置工作模式, 0-全时模式：固定使用某一项配置, 1-定时模式：按照时间段和选中的配置项进行生效，用于兼容，只能日夜配置切换, 2-按ICR(双滤光片切换器)的状态切换日夜, 3-多场景模式：按时间段关联光照条件切换图像场景, 4-临时模式：当用户临时选择一种光照条件时，往往与setTemporaryConfig一起配合使用, 5-环境自适应：按照图像检测到的白天黑夜环境，自动调节
    */
    public int              nMode;
    /**
     * 有效的配置项数量
    */
    public int              nCfgNum;
    /**
     * 当前生效的配置项,Mode为0时uint8[1]。下标0表示全时配置项, Mode为1或2或5时uint8[2]，下标0表示白天的配置项，下标1表示夜晚的配置项。取值[0, 1], 下标2表示清晨/傍晚的配置项。取值 [0, 1, 2] 数组元素个数1或2或3，代表起作用的配置项
    */
    public int[]            nConfig = new int[3];
    /**
     * Mode=1定时模式时有效，[0][0]表示白天时间段, [0][1]表示清晨时间段, [0][2]表示傍晚时间段,参见结构体定义 {@link com.netsdk.lib.structure.NET_TSECT}
    */
    public NET_TSECT_ARRAY_6[] stuTimeSection = new NET_TSECT_ARRAY_6[7];
    /**
     * 有效的第二维, 即一天的时间段规则数量
    */
    public int              nTimeSectionOneDayNum;
    /**
     * 生效的时间段, 第一维表示12个月, 第二维表示一天的时间段规则
    */
    public BYTE_ARRAY_16_40[] stuTimeSectionV2 = new BYTE_ARRAY_16_40[12];
    /**
     * 时间段有效数量数组，12个元素分别代表/控制TimeSectionV2每个月的一天时间段有效数量
    */
    public int[]            nTimeSectionOneDayNumV2 = new int[12];
    /**
     * 是否使用时间段有效数量数组, 获取和设置皆填
    */
    public int              bUseTimeSectionOneDayNumV2;
    /**
     * ICR工作模式："Auto"-自动模式；"Day"-日模式；"Night"-夜模式；"Defog" -透雾模式
    */
    public byte[]           szICRMode = new byte[16];

    public NET_CFG_VIDEO_IN_MODE_INFO() {
        this.dwSize = this.size();
        for(int i = 0; i < stuTimeSection.length; i++){
            stuTimeSection[i] = new NET_TSECT_ARRAY_6();
        }
    }
}

