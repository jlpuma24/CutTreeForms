package com.treecutforms.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.treecutforms.R;
import com.treecutforms.network.ApiService;
import com.treecutforms.network.ImageResponse;
import com.treecutforms.utils.GPSTracker;
import com.treecutforms.utils.PrefsUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private final static int REQUEST_BEFORE_PHOTO = 1;
    private final static int REQUEST_AFTER_PHOTO = 2;
    private final static int REQUEST_PRESENT_PHOTO = 3;
    private ImageView imageViewBefore;
    private ImageView imageViewAfter;
    private ImageView imageViewPresent;
    private EditText editTextContrato;
    private EditText editTextConsecutivo;
    private EditText editTextMovil;
    private EditText editTextNroOrden;
    private EditText editTextNroReporte;
    private EditText editTextCuadrillero;
    private EditText editTextOperarioUno;
    private EditText editTextOperarioDos;
    private EditText editTextOperarioTres;
    private EditText editTextOperarioCuatro;
    private EditText editTextFecha;
    private EditText editTextHoraSalida;
    private EditText editTextHoraInicio;
    private EditText editTextHoraFin;
    private EditText editTextHoraLlegada;
    private Spinner spinnerMunicipios;
    private EditText editTextZona;
    private EditText editTextDireccion;
    private EditText editTextBarrioVereda;
    private EditText editTextCircuito;
    private Spinner spinnerTensiones;
    private EditText editTextLaborRealizar;
    private EditText editTextKmInicial;
    private EditText editTextKmFinal;
    private EditText editTextEspecie;
    private Spinner spinnerTratamiento;
    private Spinner spinnerClasePoda;
    private EditText editTextAlturaInicial;
    private EditText editTextAlturaFinal;
    private EditText editTextBaremo;
    private EditText editTextPap;
    private Spinner spinnerEstadoFitosanitario;
    private EditText editTextXDiametroInicial;
    private EditText editTextYDiametroInicial;
    private EditText editTextXDiametroFinal;
    private EditText editTextYDiametroFinal;
    private EditText editTextNroPlaqueta;
    private EditText editTextCoordenadasX;
    private EditText editTextCoordenadasY;
    private EditText editTextOperarioCinco;
    private EditText editTextOperarioSeis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        imageViewBefore = (ImageView) findViewById(R.id.imageViewAntes);
        imageViewAfter = (ImageView) findViewById(R.id.imageViewDespues);
        imageViewPresent = (ImageView) findViewById(R.id.imageViewPresente);
        editTextContrato = (EditText) findViewById(R.id.editTextContrato);
        editTextConsecutivo = (EditText) findViewById(R.id.editTextConsecutivo);
        editTextMovil = (EditText) findViewById(R.id.editTextMovil);
        editTextNroOrden = (EditText) findViewById(R.id.editTextNroOrden);
        editTextNroReporte = (EditText) findViewById(R.id.editTextNroReporte);
        editTextCuadrillero = (EditText) findViewById(R.id.editTextCuadrillero);
        editTextOperarioUno = (EditText) findViewById(R.id.editTextOperarioUno);
        editTextOperarioDos = (EditText) findViewById(R.id.editTextOperarioDos);
        editTextOperarioTres = (EditText) findViewById(R.id.editTextOperarioTres);
        editTextOperarioCuatro = (EditText) findViewById(R.id.editTextOperarioCuatro);
        editTextFecha = (EditText) findViewById(R.id.editTextFecha);
        editTextHoraSalida = (EditText) findViewById(R.id.editTextHoraSalida);
        editTextHoraInicio = (EditText) findViewById(R.id.editTextHoraInicio);
        editTextHoraFin = (EditText) findViewById(R.id.editTextHoraFin);
        editTextHoraLlegada = (EditText) findViewById(R.id.editTextHoraLlegada);
        spinnerMunicipios = (Spinner) findViewById(R.id.spinnerMunicipios);
        editTextZona = (EditText) findViewById(R.id.editTextZona);
        editTextDireccion = (EditText) findViewById(R.id.editTextDireccion);
        editTextBarrioVereda = (EditText) findViewById(R.id.editTextBarrioVereda);
        editTextCircuito = (EditText) findViewById(R.id.editTextCircuito);
        spinnerTensiones = (Spinner) findViewById(R.id.spinnerTensiones);
        editTextLaborRealizar = (EditText) findViewById(R.id.editTextLaborRealizar);
        editTextKmInicial = (EditText) findViewById(R.id.editTextKmInicial);
        editTextKmFinal = (EditText) findViewById(R.id.editTextKmFinal);
        editTextEspecie = (EditText) findViewById(R.id.editTextEspecie);
        spinnerTratamiento = (Spinner) findViewById(R.id.spinnerTratamientos);
        spinnerClasePoda = (Spinner) findViewById(R.id.spinnerPodas);
        editTextAlturaInicial = (EditText) findViewById(R.id.editTextAlturaInicial);
        editTextAlturaFinal = (EditText) findViewById(R.id.editTextAlturaFinal);
        editTextBaremo = (EditText) findViewById(R.id.editTextBaremo);
        editTextPap = (EditText) findViewById(R.id.editTextPap);
        spinnerEstadoFitosanitario = (Spinner) findViewById(R.id.spinnerFitosanario);
        editTextXDiametroInicial = (EditText) findViewById(R.id.editTextXDiametroInicial);
        editTextYDiametroInicial = (EditText) findViewById(R.id.editTextYDiametroInicial);
        editTextXDiametroFinal = (EditText) findViewById(R.id.editTextXDiametroFinal);
        editTextYDiametroFinal = (EditText) findViewById(R.id.editTextYDiametroFinal);
        editTextNroPlaqueta = (EditText) findViewById(R.id.editTextNroPlaqueta);
        editTextCoordenadasX = (EditText) findViewById(R.id.editTextCoordenadasX);
        editTextCoordenadasY = (EditText) findViewById(R.id.editTextCoordenadasY);
        editTextOperarioCinco = (EditText) findViewById(R.id.editTextOperarioCinco);
        editTextOperarioSeis = (EditText) findViewById(R.id.editTextOperarioSeis);

        //Setting default values
        editTextConsecutivo.setText(String.valueOf(PrefsUtil.getInstance().getAutogenerated()));
        editTextContrato.setEnabled(false);
        editTextConsecutivo.setEnabled(false);

        editTextHoraInicio.setFocusable(false);
        editTextHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextHoraInicio.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        editTextHoraFin.setFocusable(false);
        editTextHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextHoraFin.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        editTextHoraLlegada.setFocusable(false);
        editTextHoraLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextHoraLlegada.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        editTextHoraSalida.setFocusable(false);
        editTextHoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editTextHoraSalida.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        editTextFecha.setText(dateFormat.format(new Date()));
        editTextFecha.setEnabled(false);

        setUpToolbar(getString(R.string.ficha_tecnica));
        hideKeyBoard();

        imageViewAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_AFTER_PHOTO);
            }
        });

        imageViewBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_BEFORE_PHOTO);
            }
        });

        imageViewPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_PRESENT_PHOTO);
            }
        });
    }

    private void setUpCoordinates(){
        GPSTracker gps = new GPSTracker(MainActivity.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            int degreesX = (int) latitude;
            int degreesY = (int) longitude;

            int minutesX = ((int)latitude % 1) * 60;
            int minutesY = ((int)longitude % 1) * 60;

            int secondsX = ((int)latitude % 1) * 60;
            int secondsY = ((int)longitude % 1) * 60;

            editTextCoordenadasX.setText(String.format(getString(R.string.format_coordinates), String.valueOf(degreesX), String.valueOf(minutesX), String.valueOf(secondsX)));
            editTextCoordenadasY.setText(String.format(getString(R.string.format_coordinates), String.valueOf(degreesY), String.valueOf(minutesY), String.valueOf(secondsY)));
            editTextCoordenadasX.setEnabled(false);
            editTextCoordenadasY.setEnabled(false);
        }
    }

    private void setUpToolbar(String title) {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setUpCoordinates();
        if (requestCode == REQUEST_AFTER_PHOTO && resultCode == RESULT_OK) {
//            String uri = PathUtils.getPath(this, data.getData());
            setUpImage(imageViewAfter, PrefsUtil.getInstance().getAfterPhoto());
        }

        if (requestCode == REQUEST_BEFORE_PHOTO && resultCode == RESULT_OK) {
  //          String uri = PathUtils.getPath(this, data.getData());
            setUpImage(imageViewBefore, PrefsUtil.getInstance().getBeforePhoto());
        }

        if (requestCode == REQUEST_BEFORE_PHOTO && resultCode == RESULT_OK) {
            //          String uri = PathUtils.getPath(this, data.getData());
            setUpImage(imageViewPresent, PrefsUtil.getInstance().getPresentPhoto());
        }
    }

    private void setUpImage(final ImageView photo, String url){
        photo.setImageBitmap(null);
        Picasso.with(this)
                .load(new File(url))
                .into(photo, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void hideKeyBoard(){
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (NullPointerException e){
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void dispatchTakePictureIntent(int code) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));

                switch (code){
                    case REQUEST_AFTER_PHOTO: PrefsUtil.getInstance().setAfterPhoto(photoFile.getAbsolutePath()); break;
                    case REQUEST_BEFORE_PHOTO: PrefsUtil.getInstance().setBeforePhoto(photoFile.getAbsolutePath()); break;
                    case REQUEST_PRESENT_PHOTO: PrefsUtil.getInstance().setPresentPhoto(photoFile.getAbsolutePath()); break;
                }

                startActivityForResult(takePictureIntent, code);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), getPackageName());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e("FAILS", "Failed to create storage directory.");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_"
                + timeStamp + ".jpg");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mUpload:
                new AlertDialog.Builder(MainActivity.this)
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

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                                progressDialog.setMessage(getString(R.string.sync_data));
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                MultipartBody.Part afterPhoto = obtainPartImageData(new File(PrefsUtil.getInstance().getAfterPhoto()), REQUEST_AFTER_PHOTO);
                                MultipartBody.Part beforePhoto = obtainPartImageData(new File(PrefsUtil.getInstance().getBeforePhoto()), REQUEST_BEFORE_PHOTO);
                                MultipartBody.Part presentPhoto = obtainPartImageData(new File(PrefsUtil.getInstance().getPresentPhoto()), REQUEST_PRESENT_PHOTO);

                                String[] arrayAfterPhoto = PrefsUtil.getInstance().getAfterPhoto().split("/");
                                String[] arrayBeforePhoto = PrefsUtil.getInstance().getBeforePhoto().split("/");
                                String[] arrayPresentPhoto = PrefsUtil.getInstance().getPresentPhoto().split("/");

                                apiService.doStoreImage(arrayBeforePhoto.length != 1 ? arrayBeforePhoto[arrayBeforePhoto.length-1] : "",
                                                        arrayAfterPhoto.length != 1 ? arrayAfterPhoto[arrayAfterPhoto.length-1] : "",
                                                        editTextContrato.getText().toString(),
                                                        editTextConsecutivo.getText().toString(),
                                                        editTextMovil.getText().toString(),
                                                        editTextNroOrden.getText().toString(),
                                                        editTextNroReporte.getText().toString(),
                                                        editTextCuadrillero.getText().toString(),
                                                        editTextOperarioUno.getText().toString(),
                                                        editTextOperarioDos.getText().toString(),
                                                        editTextOperarioTres.getText().toString(),
                                                        editTextOperarioCuatro.getText().toString(),
                                                        editTextFecha.getText().toString(),
                                                        editTextHoraSalida.getText().toString(),
                                                        editTextHoraInicio.getText().toString(),
                                                        editTextHoraFin.getText().toString(),
                                                        editTextHoraLlegada.getText().toString(),
                                                        spinnerMunicipios.getSelectedItem().toString(),
                                                        editTextZona.getText().toString(),
                                                        editTextDireccion.getText().toString(),
                                                        editTextBarrioVereda.getText().toString(),
                                                        editTextCircuito.getText().toString(),
                                                        spinnerTensiones.getSelectedItem().toString(),
                                                        editTextLaborRealizar.getText().toString(),
                                                        editTextKmInicial.getText().toString(),
                                                        editTextKmFinal.getText().toString(),
                                                        editTextEspecie.getText().toString(),
                                                        spinnerTratamiento.getSelectedItem().toString(),
                                                        spinnerClasePoda.getSelectedItem().toString(),
                                                        editTextAlturaInicial.getText().toString(),
                                                        editTextAlturaFinal.getText().toString(),
                                                        editTextBaremo.getText().toString(),
                                                        editTextPap.getText().toString(),
                                                        spinnerEstadoFitosanitario.getSelectedItem().toString(),
                                                        editTextXDiametroInicial.getText().toString(),
                                                        editTextYDiametroInicial.getText().toString(),
                                                        editTextXDiametroFinal.getText().toString(),
                                                        editTextYDiametroFinal.getText().toString(),
                                                        editTextNroPlaqueta.getText().toString(),
                                                        editTextCoordenadasX.getText().toString(),
                                                        editTextCoordenadasY.getText().toString(),
                                                        editTextOperarioCinco.getText().toString(),
                                                        editTextOperarioSeis.getText().toString(),
                                                        arrayPresentPhoto.length != 1 ? arrayPresentPhoto[arrayPresentPhoto.length-1] : "",
                                                        afterPhoto, beforePhoto, presentPhoto
                                        ).enqueue(new Callback<ImageResponse>() {
                                    @Override
                                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                                        progressDialog.dismiss();
                                        PrefsUtil.getInstance().setAutogenerated(PrefsUtil.getInstance().getAutogenerated()+1);
                                        Toast.makeText(MainActivity.this, getString(R.string.info_exitosa) ,Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                                        PrefsUtil.getInstance().clearPhotos();
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Call<ImageResponse> call, Throwable t) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton(getString(R.string.common_cancel), null)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public MultipartBody.Part obtainPartImageData(File imgFile, int requestCode){
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        // MultipartBody.Part is used to send also the actual file name
        String name = "";

        switch (requestCode){
            case REQUEST_AFTER_PHOTO: name =  "fileAfterDocument"; break;
            case REQUEST_BEFORE_PHOTO: name = "fileBeforeDocument"; break;
            case REQUEST_PRESENT_PHOTO: name = "filePresentDocument"; break;
        }

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(name, imgFile.getName(), requestFile);

        return imagePart;
    }
}