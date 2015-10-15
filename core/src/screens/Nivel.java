package screens;

import java.util.HashMap;

/**
 * Created by Chromz on 10/14/2015.
 */
public class Nivel {

    private String pregunta;
    private String respuesta;
    private String[] posiblesResp;

    public Nivel(String pregunta, String respuesta, String[] posiblesResp){
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.posiblesResp = posiblesResp;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String[] getPosiblesResp(){ return this.posiblesResp;}

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setPosiblesResp(String[] posiblesResp){ this.posiblesResp = posiblesResp; }
}
