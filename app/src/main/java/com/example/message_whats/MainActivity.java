package com.example.message_whats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button = findViewById(R.id.enviar_mensagem);
        EditText editText_mobile = findViewById(R.id.edit_mobile_no);
        EditText editText_msg = findViewById(R.id.edit_message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = editText_mobile.getText().toString();
                String message = editText_msg.getText().toString();


                boolean installed = appInstalledOrNot("com.whatsapp");

                if(installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+55"+ mobileNumber + "&text="+message));
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this, "Whatsapp não está instalado no seu celular", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean appInstalledOrNot(String url){
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;

    }
}