package com.example.david.practicaevaluable4botones;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ActividadSecundaria extends AppCompatActivity implements DialogoFragmento.OnOpcionesPartida, View.OnClickListener{

    private DialogoFragmento dialogo;
    private TextView textViewTitulo;
    private TextView textViewNivel;
    private TextView textViewProgreso;
    private Button primerBoton;
    private Button segundoBoton;
    private Button tercerBoton;
    private Button cuartoBoton;
    private int progreso;
    private int nivel;
    private int dificultad;
    private ArrayList<Button> listaBotones;
    private ProgressBar barraProgreso;
    private HilosBotones hilos;
    private int contadorBotones;
    private String nombreJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_secundaria);

        //MOSTRAMOS EL FRAGMENTO
        FragmentTransaction inflador = getFragmentManager().beginTransaction();
        DialogoFragmento dialogo = new DialogoFragmento();


        dialogo.show(inflador,"Opciones");

        //NOS SUSCRIBIMOS AL FRAGMENTO DEL DIALOGO
        dialogo.setOpcionesPartida(this);
       // dialogo.show(getFragmentManager(), "Dialogo Opciones");

        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);

        //INFLA LOS TEXTVIEW

        textViewNivel = (TextView) findViewById(R.id.textView_nivel);
        textViewProgreso = (TextView) findViewById(R.id.textView_progreso);

        //INFLA LOS BOTONES
        primerBoton = (Button)findViewById(R.id.boton_primero);
        segundoBoton = (Button) findViewById(R.id.boton_segundo);
        tercerBoton = (Button) findViewById(R.id.boton_tercero);
        cuartoBoton = (Button) findViewById(R.id.boton_cuarto);

        //LISTENERS DE LOS BOTONES

        primerBoton.setOnClickListener(this);
        segundoBoton.setOnClickListener(this);
        tercerBoton.setOnClickListener(this);
        cuartoBoton.setOnClickListener(this);

        //METODO PARA AÑADIR Y ALTERAR EL ARRAYLIST DE BOTONES
        anyadirArray();
        desordenarArray();
        numerarBotones(listaBotones);


    }

    private void aumentarNivel(int dificultad) {
        contadorBotones = 0;

        switch (dificultad){
            case 1:
                new HilosBotones().execute(200, 0);
                break;
            case 2:
                new HilosBotones().execute(100, 0);
                break;
            case 3:
               new HilosBotones().execute(50,0);
                break;
        }


    }

    private void anyadirArray() {

        //AÑADE LOS BOTONES AL ARRAYLIST
        listaBotones = new ArrayList<>();

        listaBotones.add(primerBoton);
        listaBotones.add(segundoBoton);
        listaBotones.add(tercerBoton);
        listaBotones.add(cuartoBoton);

        //DESORDENA EL ARRAYLIST
        Collections.shuffle(listaBotones);

    }
    private void desordenarArray(){
        Collections.shuffle(listaBotones);
    }

    private void numerarBotones(ArrayList<Button> listaBotones) {

        for (int i=0;i<listaBotones.size();i++) {
            listaBotones.get(i).setEnabled(true);
            listaBotones.get(i).setText(String.valueOf(i));
        }
    }

    @Override
    public void onOpciones(String nombre, int nivel) {
        //Instanciamos el textView del titulo una vez hemos aceptado el dialogo
        textViewTitulo = (TextView) findViewById(R.id.nombre_ActividadSecundaria);

        //Le colocamos el nombre del jugador
        nombreJugador = nombre;
        textViewTitulo.setText(nombreJugador);

        //Asignamos la dificultad según el jugador haya pulsado en el dialogo
        switch(nivel){
            case 0:
                dificultad = 1;
                break;
            case 1:
                dificultad = 2;
                break;
            case 2:
                dificultad = 3;
                break;
        }

        aumentarNivel(dificultad);
    }

    @Override
    public void onClick(View vista) {
        Button botonPulsado = (Button) vista;
        HilosBotones hilos = new HilosBotones();


        if (Integer.parseInt(botonPulsado.getText().toString()) == contadorBotones){
            botonPulsado.setEnabled(false);
            contadorBotones ++;
            if (contadorBotones == 4){
                hilos.cancel(false);

            }
        }
    }

    private class HilosBotones extends AsyncTask<Integer , Integer, Integer> {
        @Override
        protected void onPreExecute() {
            desordenarArray();
            numerarBotones(listaBotones);

            nivel = nivel + 1;

            textViewNivel.setText("Nivel "+String.valueOf(nivel));
            //Desactivar iniciar y activar cancelar
            primerBoton.setEnabled(true);
            segundoBoton.setEnabled(true);
            tercerBoton.setEnabled(true);
            cuartoBoton.setEnabled(true);
            super.onPreExecute();

        }

        @Override
        protected Integer doInBackground(Integer... parametros) {
            int i;
            //Dormir 100 ms
            i = parametros[1];
            while (i <= 100) {
                publishProgress(i);
                try {
                 //   publishProgress(i);
                    Thread.sleep(parametros[0]);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }


                if (isCancelled() == true) {
                    break;
                }
                i++;
            }
            return i;
        }
        @Override
        protected void onProgressUpdate(Integer... progresoBarra) {
            //Aumentar progressBar segun

            barraProgreso.setProgress(progresoBarra[0]);
            textViewProgreso.setText(progresoBarra[0] + "/ 100");

            super.onProgressUpdate(progresoBarra);
        }
        @Override
        protected void onPostExecute(Integer integer) {

            FinalizaFragmento frag = new FinalizaFragmento();
            frag.pasaDatos(nombreJugador, nivel);
            FragmentTransaction inflador = getFragmentManager().beginTransaction();
            FinalizaFragmento dialogo = new FinalizaFragmento();
            dialogo.show(inflador,"Opciones");
        }

        //Si se ha cancelado la tarea se ejecutará este método con el valor devuelto por doInBackgoround
        @Override
        protected void onCancelled(Integer integer) {
            //hilos.cancel(true);
            aumentarNivel(dificultad);
    }
    }
        }


