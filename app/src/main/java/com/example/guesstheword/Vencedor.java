package com.example.guesstheword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Vencedor extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vencedor);

        Intent intent = getIntent();
        int tentativas = intent.getIntExtra("tentativas", 999);

        TextView vencedor = findViewById(R.id.txt_vencedor);
        vencedor.setText("Parabéns!!! Venceste em " +tentativas+" tentativas!");

        Button voltar = findViewById(R.id.btn_voltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
