package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * SIM卡信息
*/
public enum EM_SIMINFO_TYPE
{
    /**
     * 获取SIM卡的IMSI值,对应结构体pInParam = NET_IN_SIMINFO_GET_IMSI,pOutParam = NET_OUT_SIMINFO_GET_IMSI,替代CLIENT_MobileGetIMSI接口功能
    */
    EM_SIMINFO_TYPE_GET_IMSI(0, "获取SIM卡的IMSI值,对应结构体pInParam = NET_IN_SIMINFO_GET_IMSI,pOutParam = NET_OUT_SIMINFO_GET_IMSI,替代CLIENT_MobileGetIMSI接口功能"),
    /**
     * 获取SIM卡的状态类型,对应结构体pInParam = NET_IN_SIMINFO_GET_SIMSTATE,pOutParam = NET_OUT_SIMINFO_GET_SIMSTATE
    */
    EM_SIMINFO_TYPE_GET_SIMState(1, "获取SIM卡的状态类型,对应结构体pInParam = NET_IN_SIMINFO_GET_SIMSTATE,pOutParam = NET_OUT_SIMINFO_GET_SIMSTATE");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_SIMINFO_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_SIMINFO_TYPE enumType : EM_SIMINFO_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_SIMINFO_TYPE enumType : EM_SIMINFO_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

