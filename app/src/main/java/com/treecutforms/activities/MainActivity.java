package com.treecutforms.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.treecutforms.R;
import com.treecutforms.adapters.FormItemAdapter;
import com.treecutforms.database.DataBaseHelper;
import com.treecutforms.databinding.ActivityMainBinding;
import com.treecutforms.listeners.OnDataBaseSave;
import com.treecutforms.listeners.OnUploadButtonSelected;
import com.treecutforms.models.DatabaseForm;
import com.treecutforms.network.ApiService;
import com.treecutforms.network.ImageResponse;
import com.treecutforms.utils.PrefsUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.treecutforms.activities.FormActivity.REQUEST_AFTER_PHOTO;
import static com.treecutforms.activities.FormActivity.REQUEST_BEFORE_PHOTO;
import static com.treecutforms.activities.FormActivity.REQUEST_PRESENT_PHOTO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnUploadButtonSelected {

    private ActivityMainBinding binding;
    private ProgressDialog progressDialog;
    private FormItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupProgressBar();
        setupToolbar();
        setupFloatingButton();
        setupAdapter();
        setupRecyclerView();
        setupEmptyViewsTextView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void setupProgressBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.sync_data));
        progressDialog.setCancelable(false);
    }

    /**
     * Setup activity main toolbar
     */
    private void setupToolbar() {
        binding.toolbar.setTitle(getString(R.string.activity_main_toolbar_title));
        binding.toolbar.setTitleTextColor(Color.WHITE);
        binding.toolbar.inflateMenu(R.menu.login);
        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PrefsUtil.getInstance().setLogged(false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return false;
            }
        });
    }

    private void setupFloatingButton() {
        binding.fabNewForm.setOnClickListener(this);
    }

    /**
     * Setup adapter items
     */
    private void setupAdapter() {
        adapter = new FormItemAdapter(this);
        adapter.setItems(DataBaseHelper.getInstance().getForms());
    }

    /**
     * Setup Categories RecyclerView
     */
    private void setupRecyclerView() {
        binding.rvFormList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvFormList.setHasFixedSize(true);
        binding.rvFormList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_new_form:
                launchFormActivity();
                break;
        }
    }

    /**
     * Launch Form Activity
     */
    private void launchFormActivity() {
        startActivity(new Intent(this, FormActivity.class));
    }

    @Override
    public void onButtonClicked(final DatabaseForm form) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.survey_save_alert_msg)
                .setPositiveButton(getString(R.string.survey_save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
                        progressDialog.show();

                        MultipartBody.Part afterPhoto = null, beforePhoto = null, presentPhoto = null;

                        if (form.getAfterPhotoUrl().length() != 0)
                            afterPhoto = obtainPartImageData(new File(form.getAfterPhotoUrl()), REQUEST_AFTER_PHOTO);
                        if (form.getBeforePhotoUrl().length() != 0)
                            beforePhoto = obtainPartImageData(new File(form.getBeforePhotoUrl()), REQUEST_BEFORE_PHOTO);
                        if (form.getPresentPhotoUrl().length() != 0)
                            presentPhoto = obtainPartImageData(new File(form.getPresentPhotoUrl()), REQUEST_PRESENT_PHOTO);

                        apiService.doStoreImage(form.getForm().getBeforeImage(),
                                form.getForm().getAfterImage(), form.getForm().getContrato(),
                                form.getForm().getConsecutivo(), form.getForm().getMovil(),
                                form.getForm().getOrdenTrabajo(), form.getForm().getNroReporte(),
                                form.getForm().getCuadrillero(), form.getForm().getOperarioUno(),
                                form.getForm().getOperarioDos(), form.getForm().getOperarioTres(),
                                form.getForm().getOperarioCuatro(), form.getForm().getFecha(),
                                form.getForm().getHoraSalida(), form.getForm().getHoraInicio(),
                                form.getForm().getHoraFinal(), form.getForm().getHoraLlegada(),
                                form.getForm().getMunicipio(), form.getForm().getZona(),
                                form.getForm().getDireccion(), form.getForm().getBarrio(),
                                form.getForm().getCircuito(), form.getForm().getTension(),
                                form.getForm().getLabor(), form.getForm().getKmInicial(),
                                form.getForm().getKmFinal(), form.getForm().getEspecieArbol(),
                                form.getForm().getTratamiento(), form.getForm().getClasePoda(),
                                form.getForm().getAlturaInicial(), form.getForm().getAlturaFinal(),
                                form.getForm().getBaremo(), form.getForm().getPap(),
                                form.getForm().getEstadoFitosanario(), form.getForm().getDiametroInicialX(),
                                form.getForm().getDiametroIncialY(), form.getForm().getDiametroFinalX(),
                                form.getForm().getDiametroFinalY(), form.getForm().getPlaqueta(),
                                form.getForm().getCoordenadaX(), form.getForm().getCoordenadaY(),
                                form.getForm().getOperarioCinco(), form.getForm().getOperarioSeis(),
                                form.getForm().getPresentDocument(), afterPhoto, beforePhoto, presentPhoto
                        ).enqueue(new Callback<ImageResponse>() {
                            @Override
                            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                                progressDialog.dismiss();
                                DataBaseHelper.getInstance().updateUploadedForm(form.getId(), new OnDataBaseSave() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(MainActivity.this, getString(R.string.info_exitosa), Toast.LENGTH_LONG).show();
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                launchMainActivityAsNewTask();
                                            }
                                        }, Toast.LENGTH_LONG);
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<ImageResponse> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })
                .setNegativeButton(getString(R.string.common_cancel), null)
                .show();
    }

    private void setupEmptyViewsTextView() {
        if (adapter.getItems().isEmpty()) binding.tvEmptyForms.setVisibility(View.VISIBLE);
        else binding.tvEmptyForms.setVisibility(View.GONE);
    }

    private void launchMainActivityAsNewTask() {
        Intent app = new Intent(this, MainActivity.class);
        app.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(app);
    }

    public MultipartBody.Part obtainPartImageData(File imgFile, int requestCode) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        String name = "";

        switch (requestCode) {
            case REQUEST_AFTER_PHOTO:
                name = "fileAfterDocument";
                break;
            case REQUEST_BEFORE_PHOTO:
                name = "fileBeforeDocument";
                break;
            case REQUEST_PRESENT_PHOTO:
                name = "filePresentDocument";
                break;
        }

        return MultipartBody.Part.createFormData(name, imgFile.getName(), requestFile);
    }
}
