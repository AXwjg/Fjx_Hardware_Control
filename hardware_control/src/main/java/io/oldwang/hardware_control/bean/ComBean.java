package io.oldwang.hardware_control.bean;

import java.util.Arrays;

/**
 * @author OldWang
 */
public class ComBean {
		public byte[] bRec=null;
		public String sComPort="";
		public ComBean(String sPort, byte[] buffer, int size){
			sComPort=sPort;
			bRec=new byte[size];
			for (int i = 0; i < size; i++)
			{
				bRec[i]=buffer[i];
			}
		}

	public byte[] getbRec() {
		return bRec;
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