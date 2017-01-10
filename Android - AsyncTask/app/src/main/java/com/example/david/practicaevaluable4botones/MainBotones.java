package com.example.david.practicaevaluable4botones;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainBotones extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonImagen;
    private ImageButton botonInstrucciones;
    private InstruccionesFragmento dialogoInstrucciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_botones);

        botonImagen = (ImageButton) findViewById(R.id.botonImagen);
        botonInstrucciones = (ImageButton) findViewById(R.id.imagenBotonInstrucciones);

        botonImagen.setOnClickListener(this);
        botonInstrucciones.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.botonImagen){
            Intent intent = new Intent(this, ActividadSecundaria.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.imagenBotonInstrucciones){
            FragmentTransaction inflador = getFragmentManager().beginTransaction();
            InstruccionesFragmento dialogoInstrucciones = new InstruccionesFragmento();


            dialogoInstrucciones.show(inflador,"Opciones");

        }

    }
}
