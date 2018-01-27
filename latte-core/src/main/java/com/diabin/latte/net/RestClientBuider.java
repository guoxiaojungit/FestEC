package com.diabin.latte.net;

import android.content.Context;

import com.diabin.latte.net.CallBack.IError;
import com.diabin.latte.net.CallBack.IFailure;
import com.diabin.latte.net.CallBack.IRequest;
import com.diabin.latte.net.CallBack.ISuccess;
import com.diabin.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2018/1/26.
 */

public class RestClientBuider {
    private  String mUrl=null;
    private  static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();
    private  IRequest mIRequest=null;
    private  ISuccess mISuccess=null;
    private  IFailure mIFailure=null;
    private  IError mIError=null;
    private  RequestBody mBody=null;
    private Context mContext=null;
    private LoaderStyle mLoaderStyle=null;
    RestClientBuider(){

    }
    public final RestClientBuider url(String url){
        this.mUrl=url;
        return this;
    }
    public final RestClientBuider parame(WeakHashMap<String,Object>params){
       PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuider params(String key,Object value){
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuider raw(String raw){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuider onRequest(IRequest iRequest){
        this.mIRequest=iRequest;
        return this;
    }
    public final RestClientBuider success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return this;
    }
    public final RestClientBuider failure(IFailure iFailure){
        this.mIFailure=iFailure;
        return this;
    }
    public final RestClientBuider error(IError iError){
        this.mIError=iError;
        return this;
    }
    public final RestClientBuider loader(Context context,LoaderStyle style){
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }
    public final RestClientBuider loader(Context context){
        this.mContext=context;
        this.mLoaderStyle=LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody,mContext,mLoaderStyle);
    }
}
