package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 数据源
*/
public enum EM_LARGE_MODE_SERVER_DATA_SOURCE
{
    /**
     * 未知
    */
    EM_LARGE_MODE_SERVER_DATA_SOURCE_UNKNOWN(0, "未知"),
    /**
     * 定时抓图
    */
    EM_LARGE_MODE_SERVER_DATA_SOURCE_TIME_SNAP(1, "定时抓图"),
    /**
     * 事件抓图
    */
    EM_LARGE_MODE_SERVER_DATA_SOURCE_EVENT_SNAP(2, "事件抓图"),
    /**
     * 导入文件
    */
    EM_LARGE_MODE_SERVER_DATA_SOURCE_EXPORT_FILE(3, "导入文件");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_LARGE_MODE_SERVER_DATA_SOURCE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_LARGE_MODE_SERVER_DATA_SOURCE enumType : EM_LARGE_MODE_SERVER_DATA_SOURCE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_LARGE_MODE_SERVER_DATA_SOURCE enumType : EM_LARGE_MODE_SERVER_DATA_SOURCE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

