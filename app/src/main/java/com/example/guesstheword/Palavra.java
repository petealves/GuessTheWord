package com.example.guesstheword;

import java.util.ArrayList;

public class Palavra {

    private ArrayList<String> palavras;

    public Palavra(){
        this.palavras = new ArrayList<>();
    }

    public ArrayList<String> getPalavras() {
        return palavras;
    }

    public void adicionarPalavra(String palavra){
        palavras.add(palavra);
    }
}
