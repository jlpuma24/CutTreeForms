package com.treecutforms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treecutforms.R;
import com.treecutforms.network.ApiService;
import com.treecutforms.network.GenericResponse;
import com.treecutforms.network.LoginRequest;
import com.treecutforms.utils.PrefsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jl.rodriguez
 */
public class LoginActivity extends AppCompatActivity{

    private EditText editTextPassword;
    private EditText editTextUserMail;
    private Button buttonEnter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserMail = (EditText) findViewById(R.id.editTextUserMail);

        if (!PrefsUtil.getInstance().isLogged()) {
            buttonEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonEnterClick();
                }
            });
        }
        else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    public void onButtonEnterClick(){
        HttpLoggingInterceptor interceptor;
        OkHttpClient client;

        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().addInterceptor(interceptor);
        client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://107.170.5.112:8003/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.doLogin(new LoginRequest(editTextUserMail.getText().toString(), editTextPassword.getText().toString())).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.body().getStatus().equals("OK")) {
                    PrefsUtil.getInstance().setLogged(true);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
