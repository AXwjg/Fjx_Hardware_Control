package io.oldwang.hardware_control.bean;

import java.util.Arrays;

import io.oldwang.hardware_control.utils.RubFunc;

/**
 * @author OldWang
 */
public class ComBean {
    public byte[] bRec = null;
    public String sComPort = "";

    private StringBuffer mIcOrQrCode = new StringBuffer();

	public ComBean(String sPort, byte[] buffer, int size) {
        sComPort = sPort;
        bRec = new byte[size];
		System.arraycopy(buffer, 0, bRec, 0, size);
    }

    public byte[] getbRec() {
        return bRec;
    }

	/**
	 * 获取卡号数据
	 */
    public String getIcNumber() {
		mIcOrQrCode.setLength(0);
		byte[] icData = new byte[4];
		if (bRec.length == 28 && bRec[0] == (byte) (0x20) && bRec[27] == (byte) (0x03)) {
			System.arraycopy(bRec, 6, icData, 0, 4);
			mIcOrQrCode.append(RubFunc.ByteArrayToString(icData));
		}
    	return mIcOrQrCode.toString();
	}

	/**
	 *	获取二维码数据
	 */
	public String getQrCodeData() {
		mIcOrQrCode.setLength(0);
		mIcOrQrCode.append(new String(bRec));
		return mIcOrQrCode.toString();
	}

	/**
	 * 子板数据处理
	 * 如数据长度有问题向外抛异常
	 */
	public HardwareStatus getDaughterBoardData() throws Exception {
		HardwareStatus hardwareStatus = new HardwareStatus();
		switch (bRec[2]) {
			case Command.DAUGHTER_BOARD_STATUS:
				if (bRec[3] != (byte)0xFE && bRec[4] != (byte)0xFE) {
					// 判断非错误码
					if (bRec[3] != (byte)0x26 || bRec[4] != (byte)0x0 ||
							bRec[5] != (byte)0x01 || bRec[6] != (byte)0xA9) {
//						float rubbishWeight = ((RubFunc.btou(bRec[3]) << 24)
//								+ (RubFunc.btou(bRec[4]) << 16)
//								+ (RubFunc.btou(bRec[5]) << 8)
//								+ RubFunc.btou(bRec[6])) / 1000.00f;
						hardwareStatus.setWeight(((RubFunc.btou(bRec[3]) << 24)
								+ (RubFunc.btou(bRec[4]) << 16)
								+ (RubFunc.btou(bRec[5]) << 8)
								+ RubFunc.btou(bRec[6])) / 1000.00f);
					}
				}
				hardwareStatus.setOverflowState(bRec[7] == 0x00);
				hardwareStatus.setDeliveryDoorState(bRec[8] == 0x00);
				hardwareStatus.setCleanDoorState(bRec[9] == 0x00);
				break;
			case Command.TEMP_HUM:
				hardwareStatus.setTemperature((bRec[3] + bRec[4]) * 0.01F);
				hardwareStatus.setHumidity((bRec[5] + bRec[6]) * 0.01F);
				hardwareStatus.setOverflowState(bRec[7] == 0x00);
				hardwareStatus.setDeliveryDoorState(bRec[8] == 0x00);
				hardwareStatus.setCleanDoorState(bRec[9] == 0x00);
				break;
			case Command.STATUS:
				hardwareStatus.setOverflowState(bRec[3] == 0x00);
				hardwareStatus.setDeliveryDoorState(bRec[4] == 0x00);
				hardwareStatus.setCleanDoorState(bRec[5] == 0x00);
				break;
			case Command.RESET_BOX_WEIGHT:
				hardwareStatus.setClearWeighState(bRec[3] == 0x01);
				break;
			case Command.Q3:
				hardwareStatus.setOzoneState(bRec[3] == 0x01);
				break;
			case Command.SINGLE_BOX_WEIGHT:
				if (bRec[3] != (byte)0xFE && bRec[4] != (byte)0xFE) {
					// 判断非错误码
					if (bRec[3] != (byte)0x26 || bRec[4] != (byte)0x0 ||
							bRec[5] != (byte)0x01 || bRec[6] != (byte)0xA9) {
//						float rubbishWeight = ((RubFunc.btou(bRec[3]) << 24)
//								+ (RubFunc.btou(bRec[4]) << 16)
//								+ (RubFunc.btou(bRec[5]) << 8)
//								+ RubFunc.btou(bRec[6])) / 1000.00f;
						hardwareStatus.setWeight(((RubFunc.btou(bRec[3]) << 24)
								+ (RubFunc.btou(bRec[4]) << 16)
								+ (RubFunc.btou(bRec[5]) << 8)
								+ RubFunc.btou(bRec[6])) / 1000.00f);
					}
				}
				break;
			case Command.DELIVERY_DOOR:
				hardwareStatus.setDeliveryDoorState(bRec[3] == 0x00);
				hardwareStatus.setCleanDoorState(bRec[4] == 0x00);
				break;
			default:
				break;
		}
		hardwareStatus.setCommand(bRec[2]);
		hardwareStatus.setBoxPosition(bRec[1]);
		return hardwareStatus;
	}

    public String getsComPort() {
        return sComPort;
    }

    @Override
    public String toString() {
        return "ComBean{" +
                "bRec=" + Arrays.toString(bRec) +
                ", sComPort='" + sComPort + '\'' +
                '}';
    }
}