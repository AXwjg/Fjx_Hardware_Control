package io.oldwang.hardware_control.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidParameterException;

import io.oldwang.fjx_hardware_control.constant.SerialPort;
import io.oldwang.fjx_hardware_control.interfaces.DataReceivedCall;
import io.oldwang.fjx_hardware_control.serial_helper.SerialHelper;

/**
 * @author OldWang
 * @date 2019/4/9.
 * 串口的打开
 */
public class SerialPortUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialPortUtil.class);

    // 扫码器
    private static SerialHelper QCodeSerialHelper;
    // IC卡
    private static SerialHelper ICSerialHelper;
    // 打印机
    private static SerialHelper PrintSerialHelper;
    // 子板
    private static SerialHelper DaughterBoardScaleHelper;

    private static SerialPortUtil ourInstance;

    public static SerialPortUtil getInstance() {
        if (ourInstance == null) {
            QCodeSerialHelper = new SerialHelper();
            ICSerialHelper = new SerialHelper();
            PrintSerialHelper = new SerialHelper();
            DaughterBoardScaleHelper = new SerialHelper();
            return ourInstance  = new SerialPortUtil();
        } else {
            return ourInstance;
        }
    }

    private SerialPortUtil() {
    }

    public void initPort() {
        QCodeSerialHelper.setPort(SerialPort.QCodePort);
        QCodeSerialHelper.setBaudRate(SerialPort.QCodeBaudRate);
//        QCodeSerialHelper.setDataReceivedCall(dataReceivedCall);
        openComPort(QCodeSerialHelper);

        ICSerialHelper.setPort(SerialPort.ICPort);
        ICSerialHelper.setBaudRate(SerialPort.ICBaudRate);
//        ICSerialHelper.setDataReceivedCall(dataReceivedCall);
        openComPort(ICSerialHelper);

        PrintSerialHelper.setPort(SerialPort.PrintPort);
        PrintSerialHelper.setBaudRate(SerialPort.PrintBaudRate);
//        PrintSerialHelper.setDataReceivedCall(dataReceivedCall);
        openComPort(PrintSerialHelper);

        DaughterBoardScaleHelper.setPort(SerialPort.DaughterBoardPort);
        DaughterBoardScaleHelper.setBaudRate(SerialPort.DaughterBoardBaudRate);
//        DaughterBoardScaleHelper.setDataReceivedCall(dataReceivedCall);
        openComPort(DaughterBoardScaleHelper);
    }

    /**
     * 打开串口
     */
    private void openComPort(SerialHelper comPort) {
        try {
            if (!comPort.isOpen()) {
                comPort.open();
                LOGGER.info("正常打开: {}", comPort.getPort());
            } else {
                LOGGER.info("已经打开: {}", comPort.getPort());
            }
        } catch (SecurityException e) {
            LOGGER.error("打开串口失败:没有串口读/写权限! {}", comPort.getPort());
        } catch (IOException e) {
            LOGGER.error("打开串口失败:未知错误! {}", comPort.getPort());
        } catch (InvalidParameterException e) {
            LOGGER.error("打开串口失败:参数错误! {}", comPort.getPort());
        }
    }

    public SerialHelper getQCodeSerialHelper() {
        return QCodeSerialHelper;
    }

    public SerialHelper getICSerialHelper() {
        return ICSerialHelper;
    }

    public SerialHelper getPrintSerialHelper() {
        return PrintSerialHelper;
    }

    public SerialHelper getDaughterBoardScaleHelper() {
        return DaughterBoardScaleHelper;
    }

    /**
     * 设置数据响应
     */
    public void setDataReceivedCall(DataReceivedCall dataReceivedCall) {
        if (ourInstance != null) {
            QCodeSerialHelper.setDataReceivedCall(dataReceivedCall);
            ICSerialHelper.setDataReceivedCall(dataReceivedCall);
            PrintSerialHelper.setDataReceivedCall(dataReceivedCall);
            DaughterBoardScaleHelper.setDataReceivedCall(dataReceivedCall);
        }
    }

    /**
     * 取消数据响应
     */
    public void removeAllDataReceivedCall() {
        if (ourInstance != null) {
            QCodeSerialHelper.setDataReceivedCall(null);
            ICSerialHelper.setDataReceivedCall(null);
            PrintSerialHelper.setDataReceivedCall(null);
            DaughterBoardScaleHelper.setDataReceivedCall(null);
        }
    }

    // 请求子板重量以及门状态信息 0x01
    public void daughterBoardStatus(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestDaughterBoardStatus(position));
    }

    // 请求子板温湿度值 0x02
    public void tempHum(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestTempHum(position));
    }

    // 请求子板状态，溢满，开关门状态等 0x03
    public void status(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestStatus(position));
    }

    // 请求子板中重量传感器置零 0x04
    public void resetBoxWeight(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestReset(position));
    }

    // 请求打开臭氧发生器，time是打开时长，单位为分钟 0x05
    public void openQ3(int position, int time) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestOpenQ3(position, time));
    }

    // 请求子板重量以及门状态信息 0x06
    public void singleBoxWeight(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestSingleWeight(position));
    }


    // 请求打开臭氧发生器，time是打开时长，单位为分钟 0x07
    public void systemMessage(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestSystemMessage(position));
    }

    // 开投放门 0x08
    public void openDeliveryDoor(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestDoorOperating(position, RequestSheetUtil.TFY));
    }

    // 关闭投放门 0x08
    public void closeDeliveryDoor(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestDoorOperating(position, RequestSheetUtil.CLOSE_THE_DOOR));
    }

    // 打开清运门 0x08
    public void openQingyunDoor(int position) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.requestDoorOperating(position, RequestSheetUtil.QYY));
    }

    // 设置子板的时间 0x10
    public void setDaughterBoardTime(int year, int month, int day, int hour, int minute) {
        DaughterBoardScaleHelper.send(
                RequestSheetUtil.setTime(year, month, day, hour, minute));
    }

    // 设置定时开关机 0x11
    public void requestTiming(int openHour, int openMinute, int closeHour,
                              int closeMinute, boolean isOpenTiming) {
        DaughterBoardScaleHelper.send(RequestSheetUtil.requestTiming(openHour, openMinute,
                closeHour, closeMinute, isOpenTiming));
    }

}