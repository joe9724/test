package homevideo.bitekun.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                upload();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this);


    }

    //
    private void upload()
    {
        String url = "http://tingting-api.bitekun.xin/nanjingyouzi/TingtingApi/1.0.0/member/register";
        OkHttpClient client = new OkHttpClient();
        //RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody multipartBody = new MultipartBody.Builder()
                // 设置type为"multipart/form-data"，不然无法上传参数
                .setType(MultipartBody.FORM)
                //.addFormDataPart("avatar", "xxx.png", requestBody)
                .addFormDataPart("phoneNumber", "18963601113")
                .addFormDataPart("validateCode", "654321")
                .addFormDataPart("password", "654321")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("response",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("response",response.toString());
                System.out.println("上传返回：\n" + response.body().string());
            }

        });
    }
}
