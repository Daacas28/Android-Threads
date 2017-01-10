package com.example.david.practicaevaluable4botones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 10/01/2017.
 */

public class FinalizaFragmento extends DialogFragment implements View.OnClickListener{
    private TextView tituloFinalizar;
    private TextView nombreJugador;
    private TextView nivelJugador;
    private Button botonVolver;

    private int nivelFinalizado;
    private String nombreFinalizado;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
    //CONSTRUCTOR
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    //PONE TITULO AL DIALOGO
        builder.setTitle("Partida Finalizada");
    //ASIGNA EL LAYOUT A NUESTRO FRAGMENTO
    View layoutCustomDialog = getActivity().getLayoutInflater().inflate(R.layout.finalizarfragmento, null);

        tituloFinalizar = (TextView) getActivity().findViewById(R.id.textView_finalizar);
        nombreJugador = (TextView) getActivity().findViewById(R.id.textView_nombreFinalizado);
        nivelJugador = (TextView) getActivity().findViewById(R.id.textView_nivelFinalizado);
    botonVolver = (Button) getActivity().findViewById(R.id.boton_repetir);

    //AÑADE UN ESCUCHADOR A LOS BOTONES
    layoutCustomDialog.findViewById(R.id.boton_repetir).setOnClickListener(this);




    builder.setView(layoutCustomDialog);
    //CREA EL DIALOGO
    AlertDialog alertDialog = builder.create();

    //HAGO QUE NO SE PUEDA CANCELAR EL DIALOGO SI NO SE PULSA ALGUNO DE LOS DOS BOTONES
    alertDialog.setCancelable(false);
    return alertDialog;
}
    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void pasaDatos(String nombre, int nivel){
        nivelFinalizado = nivel;
        nombreFinalizado = nombre;
    }
}

