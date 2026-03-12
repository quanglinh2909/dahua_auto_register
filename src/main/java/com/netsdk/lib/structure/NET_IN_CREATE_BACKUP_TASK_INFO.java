package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_CreateBackupTask接口输入参数
*/
public class NET_IN_CREATE_BACKUP_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 任务组ID
    */
    public int              nGroupID;
    /**
     * 是否下发备份任务信息id
    */
    public int              bIsUseTaskInfoID;
    /**
     * 备份任务信息，获取一个进行中的备份任务，如果存在TaskInfo存在就以TaskInfo为准，group字段无意义
    */
    public int              nTaskInfoID;

    public NET_IN_CREATE_BACKUP_TASK_INFO() {
        this.dwSize = this.size();
    }
}

