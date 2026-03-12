package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 车辆行为类型
*/
public enum EM_TRAFFIC_VEHICLE_ACTION
{
    /**
     * 未知
    */
    EM_TRAFFIC_VEHICLE_ACTION_UNKNOWN(0, "未知"),
    /**
     * 伐木盗林
    */
    EM_TRAFFIC_VEHICLE_ACTION_WOOD(1, "伐木盗林"),
    /**
     * 偷盗沙石
    */
    EM_TRAFFIC_VEHICLE_ACTION_STONE(2, "偷盗沙石");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_TRAFFIC_VEHICLE_ACTION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_TRAFFIC_VEHICLE_ACTION enumType : EM_TRAFFIC_VEHICLE_ACTION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_TRAFFIC_VEHICLE_ACTION enumType : EM_TRAFFIC_VEHICLE_ACTION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

