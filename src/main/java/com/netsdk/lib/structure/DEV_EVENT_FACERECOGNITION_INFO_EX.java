package com.netsdk.lib.structure;
import com.netsdk.lib.NetSDKLib;
/**
 * 事件类型EVENT_IVS_FACERECOGNITION(目标识别)对应的数据块描述信息扩展
*/
public class DEV_EVENT_FACERECOGNITION_INFO_EX extends NetSDKLib.SdkStructure
{
    /**
     * 国芯安全模块ID
    */
    public byte[]           szGMIDNumber = new byte[64];
    /**
     * 人像认证密钥版本
    */
    public byte[]           szFaceKeyVersion = new byte[16];
    /**
     * 人像数据认证码
    */
    public byte[]           szFaceDataHmac = new byte[256];
    /**
     * 预留字节
    */
    public byte[]           szReserved = new byte[2048];

    public DEV_EVENT_FACERECOGNITION_INFO_EX() {
    }
}

