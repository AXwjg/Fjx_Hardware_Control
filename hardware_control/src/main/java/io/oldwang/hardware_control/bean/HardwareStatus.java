package io.oldwang.hardware_control.bean;

/**
 * 子板硬件响应数据的状态
 *
 * @author OldWang
 * @date 2021/4/26
 */
public class HardwareStatus {

    // 命令
    private byte command;
    // 子板位置
    private byte boxPosition;
    // 高温警报状态
    private boolean highTemperatureAlarmState;
    // 红外溢满状态
    private boolean overflowState;
    // 投放门状态
    private boolean deliveryDoorState;
    // 清运门状态
    private boolean cleanDoorState;
    // 温度
    private float temperature;
    // 湿度
    private float humidity;
    // 称清零状态
    private boolean clearWeighState;
    // 臭氧状态
    private boolean ozoneState;
    // 重量
    private float weight;

    public byte getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

    public byte getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(byte boxPosition) {
        this.boxPosition = boxPosition;
    }

    public boolean isHighTemperatureAlarmState() {
        return highTemperatureAlarmState;
    }

    public void setHighTemperatureAlarmState(boolean highTemperatureAlarmState) {
        this.highTemperatureAlarmState = highTemperatureAlarmState;
    }

    public boolean isOverflowState() {
        return overflowState;
    }

    public void setOverflowState(boolean overflowState) {
        this.overflowState = overflowState;
    }

    public boolean isDeliveryDoorState() {
        return deliveryDoorState;
    }

    public void setDeliveryDoorState(boolean deliveryDoorState) {
        this.deliveryDoorState = deliveryDoorState;
    }

    public boolean isCleanDoorState() {
        return cleanDoorState;
    }

    public void setCleanDoorState(boolean cleanDoorState) {
        this.cleanDoorState = cleanDoorState;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public boolean isClearWeighState() {
        return clearWeighState;
    }

    public void setClearWeighState(boolean clearWeighState) {
        this.clearWeighState = clearWeighState;
    }

    public boolean isOzoneState() {
        return ozoneState;
    }

    public void setOzoneState(boolean ozoneState) {
        this.ozoneState = ozoneState;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
