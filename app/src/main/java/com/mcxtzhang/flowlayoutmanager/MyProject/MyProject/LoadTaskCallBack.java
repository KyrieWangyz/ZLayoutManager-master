package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public interface LoadTaskCallBack<T> {
    void onSuccess(T t);
    void onStart();
    void onFailed();
    void onFinish();
}
