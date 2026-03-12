package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 备份任务简要状态信息列表
*/
public class NET_BACKUP_MANAGER_TASK_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 任务 ID
    */
    public int              nID;
    /**
     * 备份任务执行时间, 从进入 Running 状态开始计时, 单位为秒
    */
    public int              nElapsedTime;
    /**
     * 设备上电时间，单位：秒
    */
    public byte[]           szState = new byte[32];
    /**
     * 任务数据总长度, Double类型, 以字节为单位
    */
    public double           dbTotalSize;
    /**
     * 已写入的数据长度, Double类型, 以字节为单位
    */
    public double           dbWritedSize;
    /**
     * 开始时间,格式："2015-1-21 15:00:00"
    */
    public byte[]           szStartTime = new byte[32];
    /**
     * 需要备份的文件总数
    */
    public int              nTotalFileNum;
    /**
     * 已备份的文件数
    */
    public int              nFinishedFileNum;
    /**
     * 设备名称,处于"Running"状态的device name
    */
    public BYTE_ARRAY_32[]  szWorkingTargetName = new BYTE_ARRAY_32[32];
    /**
     * 设备名称个数
    */
    public int              nWorkingTargetNameNum;
    /**
     * 预留字段
    */
    public byte[]           szResvered = new byte[1020];

    public NET_BACKUP_MANAGER_TASK_INFO() {
        for(int i = 0; i < szWorkingTargetName.length; i++){
            szWorkingTargetName[i] = new BYTE_ARRAY_32();
        }
    }
}

