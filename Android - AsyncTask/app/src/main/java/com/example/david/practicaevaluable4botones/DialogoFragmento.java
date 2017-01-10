package com.example.david.practicaevaluable4botones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by David on 09/01/2017.
 */

public class DialogoFragmento extends DialogFragment implements View.OnClickListener{

    private EditText nombreJugador;
    private  OnOpcionesPartida escuchador;
    private String nombre;
    private int idBotonSeleccionado;
    private RadioButton radioButtonNoob;
    private RadioButton radioButtonNormal;
    private RadioButton radioButtonPro;
    private RadioGroup grupoRadioBoton;
    private int idRadioButtonNoob;
    private int idRadioButtonNormal;
    private int idRadioButtonPro;
    private int dificultad;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //CONSTRUCTOR
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //PONE TITULO AL DIALOGO
        builder.setTitle("Opciones de Partida");

        //ASIGNA EL LAYOUT A NUESTRO FRAGMENTO
        View layoutCustomDialog = getActivity().getLayoutInflater().inflate(R.layout.layout_alertdialogcustom, null);

        //INSTANCIO LOS WIDGETS DEL LAYOUT DE LOS QUE VOY A RECOGER LOS DATOS
        nombreJugador = (EditText)layoutCustomDialog.findViewById(R.id.editText_NombreJugador);

        radioButtonNoob = (RadioButton)layoutCustomDialog.findViewById(R.id.radioButtonNoob);
        idRadioButtonNoob = radioButtonNoob.getId();

        radioButtonNormal = (RadioButton) layoutCustomDialog.findViewById(R.id.radioButtonNormal);
        idRadioButtonNormal = radioButtonNormal.getId();

        radioButtonPro = (RadioButton) layoutCustomDialog.findViewById(R.id.radioButtonPro);
        idRadioButtonPro = radioButtonPro.getId();

        grupoRadioBoton = (RadioGroup) layoutCustomDialog.findViewById(R.id.grupoRadioButtons);

        grupoRadioBoton.setOnClickListener(this);

        //AÑADE UN ESCUCHADOR A LOS BOTONES
        layoutCustomDialog.findViewById(R.id.botonIniciar).setOnClickListener(this);
        layoutCustomDialog.findViewById(R.id.botonCancelar).setOnClickListener(this);



        builder.setView(layoutCustomDialog);
        //CREA EL DIALOGO
        AlertDialog alertDialog = builder.create();

        //HAGO QUE NO SE PUEDA CANCELAR EL DIALOGO SI NO SE PULSA ALGUNO DE LOS DOS BOTONES
        alertDialog.setCancelable(false);
        return alertDialog;
    }
    @Override
    public void onClick(View v) {
        //SI EL BOTÓN CLICKADO HA SIDO EL DE INICIAR, SE OBTIENE EL NOMBRE DEL JUGADOR Y LA DIFICULTAD SELECCIONADA, PASÁNDOSELA A LA ACTIVIDAD SUSCRITA
        if (v.getId() == R.id.botonIniciar){

            nombre = nombreJugador.getText().toString();
            idBotonSeleccionado = grupoRadioBoton.getCheckedRadioButtonId();

            if (!nombre.isEmpty() && idBotonSeleccionado > 0) {
                switch (idBotonSeleccionado){
                    case R.id.radioButtonNoob:
                        dificultad = 0;
                        break;
                    case R.id.radioButtonNormal:
                        dificultad = 1;

                        break;
                    case R.id.radioButtonPro:
                        dificultad = 2;
                }
                System.out.println(dificultad);
                escuchador.onOpciones(nombre, dificultad);
                dismiss();
            }
            //SI NO HA INTRODUCIDO NADA EN EL CAMPO DEL NOMBRE
            else {
                Toast.makeText(getActivity(), "No has escrito un nombre para el jugador",Toast.LENGTH_LONG).show();
            }
        }
        if (v.getId() == R.id.botonCancelar){
            dismiss();
        }
    }

    //Método que permite suscribirse a las actividades a nuestro evento
    public void setOpcionesPartida (OnOpcionesPartida escuchador){

        this.escuchador = escuchador;
    }

    //Construyo una interface que defina la callback onLogin()
    public interface OnOpcionesPartida{
        public void onOpciones (String nombre, int nivel);
    }
}
