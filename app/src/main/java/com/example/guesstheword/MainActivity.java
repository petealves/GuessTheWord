package com.example.guesstheword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] palavras = {
            "Programar",
            "Java",
            "Android",
            "Vetores",
            "Jogo",
            "Ciclos",
            "Batalha",
            "Layout",
            "Design",
            "Escola"
    };




    //Button desistir = findViewById(R.id.btn_desistir);

    int tentativas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleGameLogic();

    }

    @Override
    protected void onResume() {
        super.onResume();

        handleGameLogic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu,menu);

        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    private String getPalavraJogo(){

        //gerar numero random para ir buscar um elemento ao array de palavras
        Random r = new Random();
        int indice = r.nextInt(palavras.length - 1);

        //com este indice, podemos ir buscar uma palavra ao array
        String palavra = palavras[indice];

        //baralhar a palavra
        //converter para um array de caracteres
        char x[] = palavra.toCharArray();

        //baralha as letras utilizando um algoritmo "Standard"
        for( int i=0 ; i<x.length ; i++ )
        {
            int j = r.nextInt(x.length);
            // Swap letters
            char temp = x[i]; x[i] = x[j];  x[j] = temp;
        }

        //converter a palavra baralhada para String
        String palavra_baralhada = new String(x);

        //atribuir à textview a palavra baralhada
        TextView letras = findViewById(R.id.txt_letras);
        letras.setText(palavra_baralhada);

        return palavra;
    }

    private void handleGameLogic(){

        final String palavraJogo = getPalavraJogo();

        Button adivinhar = findViewById(R.id.btn_adivinhar);
        adivinhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //verificar se o texto nao está vazio
                TextView tentativa = findViewById(R.id.txt_tentativa);
                tentativa.setMaxEms(palavraJogo.length());

                System.out.println(tentativa.getText().toString());
                System.out.println(palavraJogo);

                if (tentativa.getText().toString().equals("")){
                    tentativa.setError("Não pode estar vazio.");
                }else{
                    //verificar se a palavra inserida é igual à palavra a descobrir
                    if(tentativa.getText().toString().equals(palavraJogo)){

                        //acertou
                        //abre um novo ecra (Actividade) com o nr de tentativas e os parabéns.
                        Intent intent = new Intent(MainActivity.this, Vencedor.class);
                        intent.putExtra("tentativas", tentativas); //Optional parameters
                        MainActivity.this.startActivity(intent);
                    }else {
                        //se errou
                        //adiciona +1 ao nr de tentativas
                        ++tentativas;

                        //mostra uma mensagem
                        Toast.makeText(getApplicationContext(), "Errado!! Tenta novamente.",
                                Toast.LENGTH_LONG).show();
                    }
                }

                tentativa.setText("");

            }

        });


        Button desistir = findViewById(R.id.btn_desistir);
        desistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleGameLogic();
            }
        });
    }
}
