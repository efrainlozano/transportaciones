package com.transporte.cicese.transportaciones_cicese;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class InicioAsistenteActivity extends AppCompatActivity {
    private Button pasajeroRegistro, choferesRegistro, asistenteRegistro, solicitudRegistro, irServices,updServices ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_asistente);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abiendo el chat", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(InicioAsistenteActivity.this, chatActivity.class);
                startActivity(i);
            }
        });


        //Referencia a los botones
        choferesRegistro = (Button) findViewById(R.id.registrarChofer_btn);
        pasajeroRegistro = (Button) findViewById(R.id.registrarPasajero_btn);
        asistenteRegistro = (Button) findViewById(R.id.registrarAsistente_btn);
        solicitudRegistro = (Button) findViewById(R.id.registrarSol_btn);
        updServices = (Button) findViewById(R.id.actualizarSol_btn);
        irServices = (Button) findViewById(R.id.consultarSer_btn);


        //Cuando se selecciona el registro de asistentes
        asistenteRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, RegistroAsistenteActivity.class);
                startActivity(intent);
            }
        });

        //Cuando se selecciona actualizar solicitudes
        updServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, updateSolicitud.class);
                startActivity(intent);
            }
        });


        //Cuando se selecciona el registro de choferes
        choferesRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, RegistroChoferActivity.class);
                startActivity(intent);
            }
        });

        //Cuando se selecciona el registro de pasajeros
        pasajeroRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, RegistroPasajeroActivity.class);
                startActivity(intent);
            }
        });

        //Cuando se selecciona el registro de solicitudes
        solicitudRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, RegistroSolicitudActivity.class);
                startActivity(intent);
            }
        });


        //Cuando se selecciona ir servicios
        irServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioAsistenteActivity.this, ServiciosActivity.class);
                startActivity(intent);
            }
        });
    }
}
