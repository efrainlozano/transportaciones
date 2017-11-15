package com.transporte.cicese.transportaciones_cicese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.transporte.cicese.transportaciones_cicese.adapters.customSpinnerAdapter;
import com.transporte.cicese.transportaciones_cicese.funciones.funcionesGeneradoras;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;

public class InicioPasajeroActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        InicioFragment.OnFragmentInteractionListener,
        ServiciosFragment.OnFragmentInteractionListener,
        AboutFragment.OnFragmentInteractionListener,
        LogoutFragment.OnFragmentInteractionListener{

    funcionesGeneradoras fG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_pasajero);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new InicioPasajeroActivity.obtenerIdentificadorSolicitud().execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Abiendo el chat", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(InicioPasajeroActivity.this, chatActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio_pasajero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Mensaje",
                    Toast.LENGTH_LONG).show();
            /*Intent i = new Intent(InicioPasajeroActivity.this, chatActivity.class);
            startActivity(i);*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Boolean fragSelect = false;
        String title = "";


        if (id == R.id.nav_inicio) {
            // Handle the inicio action
            fragment =  new InicioFragment();
            title="Inicio";
            fragSelect = true;
        } else if (id == R.id.nav_servicio) {
            //fragment =  new ServiciosFragment();
            title="Servicios";
            /*fragSelect = true;*/
            Intent i = new Intent(InicioPasajeroActivity.this,ServiciosActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            fragment =  new LogoutFragment();
            title="Cerrar sesión";
            fragSelect = true;
        } else if (id == R.id.nav_about) {
            fragment =  new AboutFragment();
            title="Acerca de";
            fragSelect = true;
        } else if (id == R.id.nav_help) {

        }

        if(fragSelect){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ContenedorMain, fragment).commit();
            getSupportActionBar().setTitle(title);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class obtenerIdentificadorSolicitud extends AsyncTask<String,Void,ArrayList> {
        @Override
        protected ArrayList doInBackground(String... urls) {
            fG = new funcionesGeneradoras(getApplicationContext());
            SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
            int id_usuario=settings.getInt("idUsuario", 0);
            ArrayList fields = new ArrayList(Arrays.asList("id_usuario", "tipo"));
            ArrayList values = new ArrayList(Arrays.asList(String.valueOf(id_usuario), "p"));
            return fG.functionGetRequest("gSolicitudes", fields, values);
        }

        @Override
        protected void onPostExecute(ArrayList responseResult) {
            int responseCode = (Integer) responseResult.get(0);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String result = responseResult.get(1).toString();
                try {
                    JSONArray solicitudesResult = new JSONArray(result);
                    for (int i = 0; i < solicitudesResult.length(); i++) {
                        JSONObject oneObject = solicitudesResult.getJSONObject(i);
                        SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("solicitudPasajero", oneObject.getString("id_solicitud"));
                        editor.commit();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "No cuentas con solicitudes asignadas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
