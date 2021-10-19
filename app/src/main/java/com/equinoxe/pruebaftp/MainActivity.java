package com.equinoxe.pruebaftp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.equinoxe.pruebaftp.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Button buttonEnviar;

    private ActivityMainBinding binding;
    EnvioFTP upFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onSendClick(View v) {
        //String sFicheroLocal = Environment.getExternalStorageDirectory() + "/TempFile.txt";
        String sFicheroLocal = this.getFilesDir().getAbsolutePath() + "/TempFile.txt";

        upFile = new EnvioFTP("150.214.59.23", 8000, sFicheroLocal);
        upFile.start();
    }
}