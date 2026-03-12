package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型 EVENT_IVS_HEATMAP/EVENT_IVS_HEATMAP_PLAN(热度图/热度图计划)对应规则配置
*/
public class CFG_IVS_HEATMAP_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 规则名称,不同规则不能重名
    */
    public byte[]           szRuleName = new byte[128];
    /**
     * 规则使能
    */
    public byte             bRuleEnable;
    /**
     * 保留字段
    */
    public byte[]           bReserved = new byte[3];
    /**
     * 相应物体类型个数
    */
    public int              nObjectTypeNum;
    /**
     * 相应物体类型列表
    */
    public BYTE_ARRAY_128[] szObjectTypes = new BYTE_ARRAY_128[16];
    /**
     * 云台预置点编号	0~65535
    */
    public int              nPtzPresetId;
    /**
     * 报警联动,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.CFG_ALARM_MSG_HANDLE}
    */
    public NetSDKLib.CFG_ALARM_MSG_HANDLE stuEventHandler = new NetSDKLib.CFG_ALARM_MSG_HANDLE();
    /**
     * 事件响应时间段,参见结构体定义 {@link com.netsdk.lib.structure.CFG_TIME_SECTION}
    */
    public CFG_TIME_SECTION_ARRAY_10[] stuTimeSection = new CFG_TIME_SECTION_ARRAY_10[7];
    /**
     * 警戒区顶点数
    */
    public int              nDetectRegionPoint;
    /**
     * 警戒区,多边形类型，多边形中每个顶点的坐标归一化到[0,8192)区间,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.CFG_POLYGON}
    */
    public NetSDKLib.CFG_POLYGON[] stuDetectRegion = new NetSDKLib.CFG_POLYGON[20];
    /**
     * 规则特定的尺寸过滤器是否有效
    */
    public int              bSizeFileter;
    /**
     * 规则特定的尺寸过滤器,参见结构体定义 {@link com.netsdk.lib.NetSDKLib.CFG_SIZEFILTER_INFO}
    */
    public NetSDKLib.CFG_SIZEFILTER_INFO stuSizeFileter = new NetSDKLib.CFG_SIZEFILTER_INFO();
    /**
     * 计划ID,仅球机有效,从1开始 (热度图统一之前使用，统一之后使用nPtzPresetId)
    */
    public int              nPlanID;

    public CFG_IVS_HEATMAP_INFO() {
        for(int i = 0; i < szObjectTypes.length; i++){
            szObjectTypes[i] = new BYTE_ARRAY_128();
        }
        for(int i = 0; i < stuTimeSection.length; i++){
            stuTimeSection[i] = new CFG_TIME_SECTION_ARRAY_10();
        }
        for(int i = 0; i < stuDetectRegion.length; i++){
            stuDetectRegion[i] = new NetSDKLib.CFG_POLYGON();
        }
    }
}

