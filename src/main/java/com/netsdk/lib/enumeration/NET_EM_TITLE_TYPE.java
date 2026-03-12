package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 叠加标题用途
*/
public enum NET_EM_TITLE_TYPE
{
    /**
     * 未知
    */
    NET_EM_TITLE_UNKNOWN(0, "未知"),
    /**
     * 实时刻录信息
    */
    NET_EM_TITLE_RTINFO(1, "实时刻录信息"),
    /**
     * 自定义叠加、温湿度叠加
    */
    NET_EM_TITLE_CUSTOM(2, "自定义叠加、温湿度叠加"),
    /**
     * 片头信息
    */
    NET_EM_TITLE_TITLE(3, "片头信息"),
    /**
     * 校验码
    */
    NET_EM_TITLE_CHECK(4, "校验码"),
    /**
     * 测速仪
    */
    NET_EM_TITLE_SPEEDOMETER(5, "测速仪"),
    /**
     * 地理信息
    */
    NET_EM_TITLE_GEOGRAPHY(6, "地理信息"),
    /**
     * ATM卡号信息
    */
    NET_EM_TITLE_ATMCARDINFP(7, "ATM卡号信息"),
    /**
     * 摄像机编号
    */
    NET_EM_TITLE_CAMERAID(8, "摄像机编号");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_EM_TITLE_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_EM_TITLE_TYPE enumType : NET_EM_TITLE_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_EM_TITLE_TYPE enumType : NET_EM_TITLE_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

