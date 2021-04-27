package io.oldwang.hardware_control.bean;

/**
 * 请求硬件的命令
 *
 * @author OldWang
 * @date 2021/4/27
 */
public class Command {

    // 子板重量以及门状态信息 0x01
    public static final byte DAUGHTER_BOARD_STATUS = 0x01;
    // 子板温湿度值 0x02
    public static final byte TEMP_HUM = 0x02;
    // 子板状态，溢满，开关门状态等 0x03
    public static final byte STATUS = 0x03;
    // 子板中重量传感器置零 0x04
    public static final byte RESET_BOX_WEIGHT = 0x04;
    // 臭氧发生器 0x05
    public static final byte Q3 = 0x05;
    // 单独子板重量 0x06
    public static final byte SINGLE_BOX_WEIGHT = 0x06;
    // 开投放门 0x08 关闭投放门 0x08 打开清运门 0x08
    public static final byte DELIVERY_DOOR = 0x08;

}
