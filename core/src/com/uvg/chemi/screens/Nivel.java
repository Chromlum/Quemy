package com.uvg.chemi.screens;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chromz on 10/14/2015.
 */
public class Nivel implements Serializable{

    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;
    private ArrayList<String> tips;
    private ArrayList<String[]> posiblesResp;

    public Nivel(){

    }

    public Nivel(ArrayList<String> pregunta, ArrayList<String> respuesta,
                 ArrayList<String[]> posiblesResp, ArrayList<String> tips){
        this.preguntas = pregunta;
        this.respuestas = respuesta;
        this.posiblesResp = posiblesResp;
        this.tips = tips;
    }

    public ArrayList<String> getRespuesta() {
        return respuestas;
    }

    public ArrayList<String> getPregunta() {
        return preguntas;
    }

    public ArrayList<String[]> getPosiblesResp(){ return this.posiblesResp;}

    public ArrayList<String> getTips() {
        return tips;
    }

    public void setTips(ArrayList<String> tips) {
        this.tips = tips;
    }


    public void setPregunta(ArrayList<String> pregunta) {
        this.preguntas = pregunta;
    }

    public void setRespuesta(ArrayList<String> respuesta) {
        this.respuestas = respuesta;
    }

    public void setPosiblesResp(ArrayList<String[]> posiblesResp){ this.posiblesResp = posiblesResp; }
}
