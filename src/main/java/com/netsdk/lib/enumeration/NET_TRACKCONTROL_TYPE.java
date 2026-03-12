package com.netsdk.lib.enumeration;
import com.netsdk.lib.NetSDKLib;
/**
 * 控制类型
*/
public enum NET_TRACKCONTROL_TYPE
{
    /**
     * 标定场景, 对应于DHDEV_INTELLI_SCENE_INFO结构体
    */
    NET_TRACK_MARKSCENE(0, "标定场景, 对应于DHDEV_INTELLI_SCENE_INFO结构体"),
    /**
     * 转动到场景,对应于DHDEV_INTELLI_SCENE_INFO结构体
    */
    NET_TRACK_GOTOSCENE(1, "转动到场景,对应于DHDEV_INTELLI_SCENE_INFO结构体"),
    /**
     * 标定场景边界限位,对应于DHDEV_INTELLI_SCENELIMIT_INFO结构体
    */
    NET_TRACK_MARKSCENELIMIT(2, "标定场景边界限位,对应于DHDEV_INTELLI_SCENELIMIT_INFO结构体"),
    /**
     * 转动到场景边界限位处,对应于DHDEV_INTELLI_SCENELIMIT_INFO结构体
    */
    NET_TRACK_GOTOSCENELIMIT(3, "转动到场景边界限位处,对应于DHDEV_INTELLI_SCENELIMIT_INFO结构体"),
    /**
     * 标定场景最大跟踪倍率, 对应于DHDEV_INTELLI_SCENE_INFO结构体
    */
    NET_TRACK_MARKSCENEMAXZOOM(4, "标定场景最大跟踪倍率, 对应于DHDEV_INTELLI_SCENE_INFO结构体"),
    /**
     * 选中场景中的跟踪目标,对应于DHDEV_INTELLI_TRACKOBJECT_INFO机构体
    */
    NET_TRACK_OBJECT(5, "选中场景中的跟踪目标,对应于DHDEV_INTELLI_TRACKOBJECT_INFO机构体"),
    /**
     * 开始智能跟踪,无需指定参数信息
    */
    NET_TRACK_START(6, "开始智能跟踪,无需指定参数信息"),
    /**
     * 停止智能跟踪,无需指定参数信息
    */
    NET_TRACK_STOP(7, "停止智能跟踪,无需指定参数信息"),
    NET_TRACK_TYPE_NUM(8, "");

    private int value;
    private String note;

    public String getNote() {
        return note;
    }

    public int getValue() {
        return value;
    }

    NET_TRACKCONTROL_TYPE(int givenValue, String note) {
        this.value = givenValue;
        this.note = note;
    }

    public static String getNoteByValue(int givenValue) {
        for (NET_TRACKCONTROL_TYPE enumType : NET_TRACKCONTROL_TYPE.values()) {
            if (givenValue == enumType.getValue()) {
                return enumType.getNote();
            }
        }
        return null;
    }

    public static int getValueByNote(String givenNote) {
        for (NET_TRACKCONTROL_TYPE enumType : NET_TRACKCONTROL_TYPE.values()) {
            if (givenNote.equals(enumType.getNote())) {
                return enumType.getValue();
            }
        }
        return -1;
    }
}

