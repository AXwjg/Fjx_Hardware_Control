package io.oldwang.fjx_hardware_control.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author LI
 * @date 2019/4/1.
 * 请求子板数据函数类
 */

public class RequestSheetUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestSheetUtil.class);
    // 投放员
    public final static String TFY = "trafficker";
    // 清运人员
    public final static String QYY = "clearance_personnel";
    // 关门
    public final static String CLOSE_THE_DOOR = "close_the_door";

    // 获取重量的指令
    private static byte[] SheetCode = {(byte) 0x7A, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xBC};

    // 设置定时开关机指令
    private static byte[] timingSheetCode = {(byte) 0x7A, (byte) 0x10, (byte) 0x11, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xBC};

    // 请求子板重量以及门状态信息
    public static byte[] requestDaughterBoardStatus(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x01;
        return SheetCode;
    }

    // 单独请求子板重量
    public static byte[] requestSingleWeight(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x06;
        return SheetCode;
    }

    // 请求子板温湿度值
    public static byte[] requestTempHum(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x02;
        return SheetCode;
    }

    // 请求子板状态，溢满，开关门状态等
    public static byte[] requestStatus(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x03;
        return SheetCode;
    }

    // 请求子板中重量传感器置零
    public static byte[] requestReset(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x04;
        return SheetCode;
    }

    // 请求打开O3发生器，time是打开时长，单位为分钟
    public static byte[] requestOpenQ3(int selectionType, int time) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x05;
        SheetCode[3] = (byte) time;
        return SheetCode;
    }

    // 获取子板上报系统信息
    public static byte[] requestSystemMessage(int selectionType) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x07;
        return SheetCode;
    }

    // 请求打开门指令 type 不同类型 开启不同的门
    public static byte[] requestDoorOperating(int selectionType, String type) {
        SheetCode[1] = returnByType(selectionType);
        SheetCode[2] = (byte) 0x08;
        if (type.equals(TFY)) {
            // 投放人员
            SheetCode[3] = (byte) 0x01;
        } else if (type.equals(QYY)) {
            // 清运人员
            SheetCode[3] = (byte) 0x02;
        } else if (type.equals(CLOSE_THE_DOOR)) {
            // 关门
            SheetCode[3] = (byte) 0x08;
        }
        return SheetCode;
    }

    // 开始瓶罐计数检测
    public static byte[] startBottleCounting() {
        SheetCode[1] = (byte) 0x10;
        SheetCode[2] = (byte) 0x09;
        SheetCode[3] = (byte) 0x01;
        return SheetCode;
    }

    // 结束瓶罐计数检测
    public static byte[] endBottleCounting() {
        SheetCode[1] = (byte) 0x10;
        SheetCode[2] = (byte) 0x09;
        SheetCode[3] = (byte) 0x00;
        return SheetCode;
    }

    // 设置时间设备的时间
    public static byte[] setTime(int year, int month, int day, int hour, int minute) {
        timingSheetCode[2] = (byte) 0x10;
        timingSheetCode[3] = demical2Hex(year);
        timingSheetCode[4] = demical2Hex(month);
        timingSheetCode[5] = demical2Hex(day);
        timingSheetCode[6] = demical2Hex(hour);
        timingSheetCode[7] = demical2Hex(minute);
        return timingSheetCode;
    }

    // 设置定时开关机
    public static byte[] requestTiming(int openHour, int openMinute, int closeHour, int closeMinute, boolean isOpenTiming) {
        timingSheetCode[2] = (byte) 0x11;
        timingSheetCode[3] = demical2Hex(openHour);
        timingSheetCode[4] = demical2Hex(openMinute);
        timingSheetCode[5] = demical2Hex(closeHour);
        timingSheetCode[6] = demical2Hex(closeMinute);
        if (isOpenTiming) {
            timingSheetCode[7] = (byte) 0x01;
        } else {
            timingSheetCode[7] = (byte) 0x00;
        }
        return timingSheetCode;
    }

    /**
     * 10进制转btye 16进制
     */
    public static byte demical2Hex(int i) {
        String str = Integer.toHexString(i);
        return (byte) Integer.parseInt(str, 16);
    }

    // 根据子板序号来操作子板
    private static byte returnByType(int mPlateLocation) {
        byte sheetCode;
        switch (mPlateLocation) {
            case 1:
                sheetCode = (byte) 0x01;
                break;
            case 2:
                sheetCode = (byte) 0x02;
                break;
            case 3:
                sheetCode = (byte) 0x03;
                break;
            case 4:
                sheetCode = (byte) 0x04;
                break;
            case 5:
                sheetCode = (byte) 0x05;
                break;
            case 6:
                sheetCode = (byte) 0x06;
                break;
            case 7:
                sheetCode = (byte) 0x07;
                break;
            case 8:
                sheetCode = (byte) 0x08;
                break;
            case 9:
                sheetCode = (byte) 0x09;
                break;
            case 10:
                sheetCode = (byte) 0x10;
                break;
            case 11:
                sheetCode = (byte) 0x11;
                break;
            case 12:
                sheetCode = (byte) 0x12;
                break;
            case 13:
                sheetCode = (byte) 0x13;
                break;
            case 14:
                sheetCode = (byte) 0x14;
                break;
            case 15:
                sheetCode = (byte) 0x15;
                break;
            case 16:
                sheetCode = (byte) 0x16;
                break;
            case 12138: // 全部
                sheetCode = (byte) 0xff;
                break;
            default:
                sheetCode = (byte) 0x111;
                break;
        }
        return sheetCode;
    }

    // 根据返回的主板位置来判断是哪个类型
    public static int returnById(int sheetCode) {
        int mSelectionType;
        switch (sheetCode) {
            case (byte) 0x01:
                mSelectionType = 1;
                break;
            case (byte) 0x02:
                mSelectionType = 2;
                break;
            case (byte) 0x03:
                mSelectionType = 3;
                break;
            case (byte) 0x04:
                mSelectionType = 4;
                break;
            case (byte) 0x05:
                mSelectionType = 5;
                break;
            case (byte) 0x06:
                mSelectionType = 6;
                break;
            case (byte) 0x07:
                mSelectionType = 7;
                break;
            case (byte) 0x08:
                mSelectionType = 8;
                break;
            case (byte) 0x09:
                mSelectionType = 9;
                break;
            case (byte) 0xA:
                mSelectionType = 10;
                break;
            case (byte) 0xB:
                mSelectionType = 11;
                break;
            default:
                mSelectionType = -1;
                break;
        }
        return mSelectionType;
    }
}

