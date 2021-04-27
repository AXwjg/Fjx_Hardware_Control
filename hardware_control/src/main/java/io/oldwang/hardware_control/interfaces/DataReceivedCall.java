package io.oldwang.hardware_control.interfaces;

import io.oldwang.hardware_control.bean.ComBean;

/**
 * 硬件数据响应
 * @author OldWang
 * @date 2019/3/19.
 */
public interface DataReceivedCall {

    void onDataReceived(ComBean comBean);

}
