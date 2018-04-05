package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;


import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public interface NetTask<T> {
    void excute(T data, LoadTaskCallBack callBack,String address);

}
