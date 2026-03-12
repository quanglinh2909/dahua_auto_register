package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 当前门采集状态
*/
public enum NETAEM_CARD_STATE
{
    /**
     * 未知
    */
    EM_CARD_STATE_UNKNOWN(-1, "未知"),
    /**
     * 门禁刷卡
    */
    EM_CARD_STATE_SWIPE(0, "门禁刷卡"),
    /**
     * 门禁采集卡
    */
    EM_CARD_STATE_COLLECTION(1, "门禁采集卡");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NETAEM_CARD_STATE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NETAEM_CARD_STATE enumType : NETAEM_CARD_STATE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NETAEM_CARD_STATE enumType : NETAEM_CARD_STATE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

