package io.oldwang.hardware_control.constant;

/**
 * 串口
 *
 * @author OldWang
 * @date 2021/4/16
 */
public class SerialPort {
    // 子板
    public final static String DaughterBoardPort = "/dev/ttyS3";
    public final static String DaughterBoardBaudRate = "9600";
    // 扫码器
    public final static String QCodePort = "/dev/ttyS2";
    public final static String QCodeBaudRate = "9600";
    // IC卡
    public final static String ICPort = "/dev/ttyS1";
    public final static String ICBaudRate = "9600";
    // 打印机
    public final static String PrintPort = "/dev/ttyS0";
    public final static String PrintBaudRate = "115200";



}
