package com.example.david.practicaevaluable4botones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by David on 10/01/2017.
 */

public class InstruccionesFragmento extends DialogFragment implements View.OnClickListener{

    private Button botonInstrucciones;

    public Dialog onCreateDialog(Bundle savedInstanceState) {


        //CONSTRUCTOR
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //PONE TITULO AL DIALOGO
        builder.setTitle("Instrucciones de Partida");
        //ASIGNA EL LAYOUT A NUESTRO FRAGMENTO
        View layoutCustomDialog = getActivity().getLayoutInflater().inflate(R.layout.layoutfragmentoinstrucciones, null);

        botonInstrucciones = (Button) getActivity().findViewById(R.id.botonInstrucciones);

        //AÑADE UN ESCUCHADOR A LOS BOTONES
        layoutCustomDialog.findViewById(R.id.botonInstrucciones).setOnClickListener(this);


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
}
