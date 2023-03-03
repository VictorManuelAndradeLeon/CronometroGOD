package com.example.cronmetrogod;

import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list_laps;
    Button btn_start,btn_stop,btn_reset;
    Chronometer chronometro;
    Boolean correr=false;
    long detenerse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start=findViewById(R.id.btn_start);
        btn_stop=findViewById(R.id.btn_stop);
        btn_reset=findViewById(R.id.btn_reset);
        Button btnLap=findViewById(R.id.btn_lap);
        chronometro=findViewById(R.id.chronometro);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometro();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChronometro();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetChronometro();
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarVuelta();
            }
        });
    }

    private void resetChronometro() {
        chronometro.setBase(SystemClock.elapsedRealtime());
        detenerse=0;
        laps.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laps);
        ListView listLaps = findViewById(R.id.list_laps);
        listLaps.setAdapter(adapter);
    }

    private void stopChronometro() {
        if (correr){
            chronometro.stop();
            detenerse = SystemClock.elapsedRealtime() - chronometro.getBase();
            correr=false;
        }
    }

    private void startChronometro() {
        if(!correr){
            chronometro.setBase(SystemClock.elapsedRealtime() - detenerse);
            chronometro.start();
            correr=true;
        }
    }

    // Declarar ArrayList para almacenar las vueltas
    ArrayList<String> laps = new ArrayList<>();

    // Función para registrar una vuelta
    public void registrarVuelta() {
        // Obtener el tiempo actual del cronómetro
        String tiempoActual = chronometro.getText().toString();

        // Agregar el tiempo actual a la lista de vueltas
        laps.add(tiempoActual);

        // Actualizar el ListView correspondiente para mostrar las vueltas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, laps);
        ListView listLaps = findViewById(R.id.list_laps);
        listLaps.setAdapter(adapter);
    }

}