package com.transporte.cicese.transportaciones_cicese;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioChoferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_chofer);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abiendo el chat", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(InicioChoferActivity.this, chatActivity.class);
                startActivity(i);
            }
        });

    }
}
