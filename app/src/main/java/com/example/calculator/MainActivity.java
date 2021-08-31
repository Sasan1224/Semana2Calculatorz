package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView muestras;
    private EditText edit;
    private TextView time;
    private Button buttonAnswer;
    private Button buttonClear;

    private TextView TextPoints;

    private ArrayList<Pregunta> preguntas;

    private Button tryAgain;


    private int points = 0;
    private int contador = 30;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        muestras = findViewById(R.id.muestra);
        edit = findViewById(R.id.editar);
        time = findViewById(R.id.tiempo);
        buttonAnswer = findViewById(R.id.buttonResponder);
        buttonClear = findViewById(R.id.buttonClear);
        TextPoints = findViewById(R.id.TextPuntaje);
        tryAgain = findViewById(R.id.intentarDeNuevo);




        // -- //
        preguntas = new ArrayList<Pregunta>();
        for (int i =0; i < 1; i ++) {
            preguntas.add (new Pregunta ());
            preguntas.get(i).ejercicio();
            muestras.setText(""+ preguntas.get(i).getDuda());
        }



        buttonAnswer.setOnClickListener(
                (View) -> {
                    String responder = edit.getText().toString();
                    for (int i = 0; i < preguntas.size(); i++) {
                        if (preguntas.get(i).getRespuesta().equals(responder)) {
                            preguntas.get(i).ejercicio();
                            muestras.setText("" + preguntas.get(i).getDuda());

                            //aqui se aumenta el puntaje cuando es correcto
                            points += 10;
                            TextPoints.setText("" + points);

                        } else {
                            if(points > 0){
                                points -= 5;
                                TextPoints.setText("" + points);
                            }




                        }

                    }
                }
        );

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.getText().clear();
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < preguntas.size(); i++) {

                        preguntas.get(i).ejercicio();
                        muestras.setText("" + preguntas.get(i).getDuda());
                    }

                points = 0;
                contador = 30;
                TextPoints.setText("" + points);


                new Thread(

                        () -> {
                            while (contador > 0) {
                                contador--;
                                runOnUiThread(() -> {
                                    time.setText("" + contador);
                                    if (contador > 0) {
                                        tryAgain.setVisibility(View.GONE);
                                    } else {
                                        tryAgain.setVisibility(View.VISIBLE);
                                    }

                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).start();
            }
        });



        new Thread(

                () -> {
                    while (contador > 0) {
                        contador--;
                        runOnUiThread(() -> {
                            time.setText("" + contador);
                            if (contador > 0) {
                                tryAgain.setVisibility(View.GONE);
                            } else {
                                tryAgain.setVisibility(View.VISIBLE);
                            }

                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }
}