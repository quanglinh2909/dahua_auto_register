package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * IP类型
*/
public enum EM_IPVERSION
{
    /**
     * IPv4
    */
    EM_IPVERSION_IPV4(0, "IPv4"),
    /**
     * IPv6
    */
    EM_IPVERSION_IPV6(1, "IPv6");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    EM_IPVERSION(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (EM_IPVERSION enumType : EM_IPVERSION.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (EM_IPVERSION enumType : EM_IPVERSION.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

