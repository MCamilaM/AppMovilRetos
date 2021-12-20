package usa.sesion10.reto4_grupo35.Vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ProgressBar;

import usa.sesion10.reto4_grupo35.Controlador.MainActivity;
import usa.sesion10.reto4_grupo35.R;

public class SplashScreen extends AppCompatActivity implements Runnable{

    Thread h1;
    Handler p1;

    ProgressBar barraProgreso;

    ImageView splashIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        splashIcon = (ImageView)findViewById(R.id.splashIcon);
        splashIcon.setBackgroundResource(R.drawable.icono1);

        barraProgreso = (ProgressBar) findViewById(R.id.progressBar);

        barraProgreso.setProgress(0);

       /* AnimationDrawable ejecutarAnimacion = (AnimationDrawable)iconoSplash.getBackground();
        ejecutarAnimacion.start();*/

        //***********************
        h1= new Thread(this);
        h1.start();
        //***********************
        p1 = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                barraProgreso.incrementProgressBy(msg.what);
            }
        };

    }

    @Override
    public void run() {
        try {
            barraProgreso.setProgress(0);
            for (int i = 1; i<=5; i++){
                Thread.sleep(1000);
                p1.sendMessage(p1.obtainMessage((i*20)/i));
            }
            Intent pasarPantalla = new Intent(getApplicationContext(), MainActivity.class );
            startActivity(pasarPantalla);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            barraProgreso.setProgress(0);
            for (int i = 1; i<=5; i++){
                Thread.sleep(1000);
                p1.sendMessage(p1.obtainMessage((i*20)/i));
            }
            Intent pasarPantalla = new Intent(getApplicationContext(), MainActivity.class );
            startActivity(pasarPantalla);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

