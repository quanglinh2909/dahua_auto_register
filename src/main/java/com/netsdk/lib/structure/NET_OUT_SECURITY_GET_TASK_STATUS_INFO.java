package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * CLIENT_SecurityGetTaskStatus接口出参
*/
public class NET_OUT_SECURITY_GET_TASK_STATUS_INFO extends NetSDKLib.SdkStructure
{
    /**
     * 此结构体大小,必须赋值
    */
    public int              dwSize;
    /**
     * 当前任务类型,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_SECURITY_TASK_TYPE}
    */
    public int              nTaskType;
    /**
     * 当前任务状态,参见枚举定义 {@link com.netsdk.lib.enumeration.EM_SECURITY_TASK_STATUS}
    */
    public int              nStatus;
    /**
     * 当任务状态"Status"为"Failed"时, 填写错误信息, 例如:"File content error!" 文件错误
    */
    public byte[]           szErrorCode = new byte[128];
    /**
     * 当前任务执行进度百分比
    */
    public int              nProgress;
    /**
     * 导入导出对应的错误码信息, 10001 没有可以导出的预置点, 10002 导出时生成环境失败, 10003 导出时文件打包失败, 10004 导入时生成环境失败, 10005 导入时预置点矫正比对失败, 10006 导入时预置点比对通过率过低, 10007 导入时解析图片数据失败, 10008 导入时打开文件失败, 10009 导入时写文件失败
    */
    public int              nErrorType;

    public NET_OUT_SECURITY_GET_TASK_STATUS_INFO() {
        this.dwSize = this.size();
    }
}

