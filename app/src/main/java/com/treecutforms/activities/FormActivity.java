package com.treecutforms.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.treecutforms.R;
import com.treecutforms.database.DataBaseHelper;
import com.treecutforms.databinding.ActivityFormBinding;
import com.treecutforms.listeners.OnDataBaseSave;
import com.treecutforms.models.DatabaseForm;
import com.treecutforms.models.Form;
import com.treecutforms.utils.GPSTracker;
import com.treecutforms.utils.PrefsUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {

    private ActivityFormBinding binding;
    public final static int REQUEST_BEFORE_PHOTO = 1;
    public final static int REQUEST_AFTER_PHOTO = 2;
    public final static int REQUEST_PRESENT_PHOTO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form);

        //Setting default values
        binding.editTextConsecutivo.setText(String.valueOf(PrefsUtil.getInstance().getAutogenerated()));
        binding.editTextContrato.setEnabled(false);
        binding.editTextConsecutivo.setEnabled(false);

        binding.editTextHoraInicio.setFocusable(false);
        binding.editTextHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.editTextHoraInicio.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        binding.editTextHoraFin.setFocusable(false);
        binding.editTextHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.editTextHoraFin.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        binding.editTextHoraLlegada.setFocusable(false);
        binding.editTextHoraLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.editTextHoraLlegada.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        binding.editTextHoraSalida.setFocusable(false);
        binding.editTextHoraSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(FormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.editTextHoraSalida.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Seleccione hora");
                mTimePicker.show();
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binding.editTextFecha.setText(dateFormat.format(new Date()));
        binding.editTextFecha.setEnabled(false);

        setUpToolbar(getString(R.string.ficha_tecnica));
        hideKeyBoard();

        binding.imageViewDespues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_AFTER_PHOTO);
            }
        });

        binding.imageViewAntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_BEFORE_PHOTO);
            }
        });

        binding.imageViewPresente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(REQUEST_PRESENT_PHOTO);
            }
        });
    }

    private void setUpCoordinates() {
        GPSTracker gps = new GPSTracker(FormActivity.this);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            int degreesX = (int) latitude;
            int degreesY = (int) longitude;

            int minutesX = ((int) latitude % 1) * 60;
            int minutesY = ((int) longitude % 1) * 60;

            int secondsX = ((int) latitude % 1) * 60;
            int secondsY = ((int) longitude % 1) * 60;

            binding.editTextCoordenadasX.setText(String.format(getString(R.string.format_coordinates), String.valueOf(degreesX), String.valueOf(minutesX), String.valueOf(secondsX)));
            binding.editTextCoordenadasY.setText(String.format(getString(R.string.format_coordinates), String.valueOf(degreesY), String.valueOf(minutesY), String.valueOf(secondsY)));
            binding.editTextCoordenadasX.setEnabled(false);
            binding.editTextCoordenadasY.setEnabled(false);
        }
    }

    private void setUpToolbar(String title) {
        binding.toolbar.setTitle(title);
        binding.toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(binding.toolbar);
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
            setUpImage(binding.imageViewDespues, PrefsUtil.getInstance().getAfterPhoto());
        }

        if (requestCode == REQUEST_BEFORE_PHOTO && resultCode == RESULT_OK) {
            //          String uri = PathUtils.getPath(this, data.getData());
            setUpImage(binding.imageViewAntes, PrefsUtil.getInstance().getBeforePhoto());
        }

        if (requestCode == REQUEST_BEFORE_PHOTO && resultCode == RESULT_OK) {
            //          String uri = PathUtils.getPath(this, data.getData());
            setUpImage(binding.imageViewPresente, PrefsUtil.getInstance().getPresentPhoto());
        }
    }

    private void setUpImage(final ImageView photo, String url) {
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

    private void hideKeyBoard() {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
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

                switch (code) {
                    case REQUEST_AFTER_PHOTO:
                        PrefsUtil.getInstance().setAfterPhoto(photoFile.getAbsolutePath());
                        break;
                    case REQUEST_BEFORE_PHOTO:
                        PrefsUtil.getInstance().setBeforePhoto(photoFile.getAbsolutePath());
                        break;
                    case REQUEST_PRESENT_PHOTO:
                        PrefsUtil.getInstance().setPresentPhoto(photoFile.getAbsolutePath());
                        break;
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
                new AlertDialog.Builder(FormActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage(R.string.survey_save_alert_msg)
                        .setPositiveButton(getString(R.string.survey_save), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog progressDialog = new ProgressDialog(FormActivity.this);
                                progressDialog.setMessage(getString(R.string.sync_data));
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                                String[] arrayAfterPhoto = PrefsUtil.getInstance().getAfterPhoto().split("/");
                                String[] arrayBeforePhoto = PrefsUtil.getInstance().getBeforePhoto().split("/");
                                String[] arrayPresentPhoto = PrefsUtil.getInstance().getPresentPhoto().split("/");

                                DatabaseForm form = new DatabaseForm();
                                form.setForm(new Form(arrayBeforePhoto.length != 1 ? arrayBeforePhoto[arrayBeforePhoto.length - 1] : "",
                                        arrayAfterPhoto.length != 1 ? arrayAfterPhoto[arrayAfterPhoto.length - 1] : "",
                                        binding.editTextContrato.getText().toString(), binding.editTextConsecutivo.getText().toString(),
                                        binding.editTextMovil.getText().toString(), binding.editTextNroOrden.getText().toString(),
                                        binding.editTextNroReporte.getText().toString(), binding.editTextCuadrillero.getText().toString(),
                                        binding.editTextOperarioUno.getText().toString(), binding.editTextOperarioDos.getText().toString(),
                                        binding.editTextOperarioTres.getText().toString(), binding.editTextOperarioCuatro.getText().toString(),
                                        binding.editTextFecha.getText().toString(), binding.editTextHoraSalida.getText().toString(),
                                        binding.editTextHoraInicio.getText().toString(), binding.editTextHoraFin.getText().toString(),
                                        binding.editTextHoraLlegada.getText().toString(), binding.spinnerMunicipios.getSelectedItem().toString(),
                                        binding.editTextZona.getText().toString(), binding.editTextDireccion.getText().toString(),
                                        binding.editTextBarrioVereda.getText().toString(), binding.editTextCircuito.getText().toString(),
                                        binding.spinnerTensiones.getSelectedItem().toString(), binding.editTextLaborRealizar.getText().toString(),
                                        binding.editTextKmInicial.getText().toString(), binding.editTextKmFinal.getText().toString(),
                                        binding.editTextEspecie.getText().toString(), binding.spinnerTratamientos.getSelectedItem().toString(),
                                        binding.spinnerPodas.getSelectedItem().toString(), binding.editTextAlturaInicial.getText().toString(),
                                        binding.editTextAlturaFinal.getText().toString(), binding.editTextBaremo.getText().toString(),
                                        binding.editTextPap.getText().toString(), binding.spinnerFitosanario.getSelectedItem().toString(),
                                        binding.editTextXDiametroInicial.getText().toString(), binding.editTextYDiametroInicial.getText().toString(),
                                        binding.editTextXDiametroFinal.getText().toString(), binding.editTextYDiametroFinal.getText().toString(),
                                        binding.editTextNroPlaqueta.getText().toString(), binding.editTextCoordenadasX.getText().toString(),
                                        binding.editTextCoordenadasY.getText().toString(), binding.editTextOperarioCinco.getText().toString(),
                                        binding.editTextOperarioSeis.getText().toString(),
                                        arrayPresentPhoto.length != 1 ? arrayPresentPhoto[arrayPresentPhoto.length - 1] : ""));
                                form.setId(PrefsUtil.getInstance().getAutogenerated());
                                form.setUploaded(false);
                                form.setAfterPhotoUrl(PrefsUtil.getInstance().getAfterPhoto());
                                form.setBeforePhotoUrl(PrefsUtil.getInstance().getBeforePhoto());
                                form.setPresentPhotoUrl(PrefsUtil.getInstance().getPresentPhoto());

                                DataBaseHelper.getInstance().addForm(form, new OnDataBaseSave() {
                                    @Override
                                    public void onSuccess() {
                                        progressDialog.dismiss();
                                        PrefsUtil.getInstance().clearPhotos();
                                        PrefsUtil.getInstance().setAutogenerated(PrefsUtil.getInstance().getAutogenerated() + 1);
                                        Toast.makeText(FormActivity.this, getString(R.string.info_exitosa), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(FormActivity.this, MainActivity.class));
                                        PrefsUtil.getInstance().clearPhotos();
                                        finish();
                                    }

                                    @Override
                                    public void onError() {
                                        Toast.makeText(FormActivity.this, getString(R.string.info_failed), Toast.LENGTH_SHORT).show();
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

}