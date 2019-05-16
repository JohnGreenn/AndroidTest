package com.apple.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Api api;
    private Button button;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.186:8003/kura-service/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

         api =retrofit.create(Api.class);


    }

    public static OkHttpClient getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {

                      String SECRECT = "227ca84aad90e172218183e970ee0516";
                      String APPID = "Android-1488435881";

                        int NONCE = ((int) ((Math.random() * 9 + 1) * 100000));
                        String CURTIME = getTime1();

                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("pragma")
                                .cacheControl(CacheControl.FORCE_NETWORK)
                                .header("CLIENT", "android")
                                .header("APPID", APPID)
                                .header("CURTIME", CURTIME)
                                .header("NONCE", NONCE + "")
                                .header("OPENKEY", md5(APPID + NONCE + CURTIME + SECRECT))
                                .build();

                        return chain.proceed(request);
                    }
                })
                .connectTimeout(3,TimeUnit.MINUTES)
                .build();

        return httpClient;

    }

    /*
    md5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?",e);
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?",e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for(byte b : hash) {
            if((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    /*
    获取当前时间
    */
    private static String getTime1() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d1 = new Date(time);
        String t1 = format.format(d1);
        return t1;
    }

    private void init() {

        phone = (EditText) findViewById(R.id.phone);
        String telphone = phone.getText().toString();


        button = (Button) findViewById(R.id.getCode);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getCode:
                getSms(phone.getText().toString());
                break;
        }


    }

    public void requestAPI(View view){
        api.getUserInfo().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                    Toast.makeText(MainActivity.this,"Result="+response.body().getResult(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    public void userlogin(View view) {

        api.userLogin("15555182321","qq123456").enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                Toast.makeText(MainActivity.this, "账号="+response.body().getData().getList().getUSERNAME()+"登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.i("cc",t.getMessage());
            }
        });

    }



    public void login(View view){

        api.login(new UserParam("15555182321","qq123456")).enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                if(response.isSuccessful()){

                    String msg = response.body().getMsg();


                }
            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {

            }
        });

    }

//    public void getSms() {
//        api.getSms("15555182321").enqueue(new Callback<GetSMS>() {
//            @Override
//            public void onResponse(Call<GetSMS> call, Response<GetSMS> response) {
//                if(response.body().getResult().getCode()==100){
//                    Toast.makeText(MainActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetSMS> call, Throwable t) {
//                Log.i("failure",t.getMessage());
//            }
//        });
//    }

  public void getSms(String phone){
        api.getSms(phone).enqueue(new Callback<GetSMS>() {
            @Override
            public void onResponse(Call<GetSMS> call, Response<GetSMS> response) {
                if(response.body().getResult().getCode()==100){
                    Toast.makeText(MainActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetSMS> call, Throwable t) {
                Log.i("failure",t.getMessage());
            }
        });
    }


}
