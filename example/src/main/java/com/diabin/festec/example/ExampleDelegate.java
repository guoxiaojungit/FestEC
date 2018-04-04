package com.diabin.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.net.CallBack.IError;
import com.diabin.latte.net.CallBack.IFailure;
import com.diabin.latte.net.CallBack.ISuccess;
import com.diabin.latte.net.RestClient;

/**
 * Created by lenovo on 2018/1/24.
 */

public class ExampleDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("https://news.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
