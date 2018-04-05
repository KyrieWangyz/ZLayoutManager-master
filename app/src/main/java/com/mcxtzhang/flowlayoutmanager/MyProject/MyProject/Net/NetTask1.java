package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public interface NetTask1<T> {
    void excute(T name,T mailAddress, LoadTaskCallBack callBack, String address);
}
