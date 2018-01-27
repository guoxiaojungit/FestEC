package com.diabin.latte.net;

import android.content.Context;

import com.diabin.latte.net.CallBack.IError;
import com.diabin.latte.net.CallBack.IFailure;
import com.diabin.latte.net.CallBack.IRequest;
import com.diabin.latte.net.CallBack.ISuccess;
import com.diabin.latte.net.CallBack.RequestCallbacks;
import com.diabin.latte.ui.LatteLoader;
import com.diabin.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lenovo on 2018/1/24.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS=RestCreator .getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private  final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    public RestClient(String url,
                      Map<String,Object>params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle){
        this.URL=url;
       PARAMS.putAll(params);
        this.REQUEST=request;
        this.SUCCESS=success;
        this.FAILURE=failure;
        this.ERROR=error;
        this.BODY=body;
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;
    }
    public static RestClientBuider buider(){
        return new RestClientBuider();
    }
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String>call=null;
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call=service.postRaw(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call=service.putRaw(URL,BODY);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if (call!=null){
           call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }
    public final void get(){
        request(HttpMethod.DELETE.GET);
    }
    public final void post(){
        if (BODY==null){
            request(HttpMethod.DELETE.POST);
        }else {
            if (PARAMS.isEmpty()){
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }

    }
    public final void put(){
        request(HttpMethod.DELETE.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE.DELETE);
    }

}
