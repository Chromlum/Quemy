package screens;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by Chromz on 10/14/2015.
 */
public final class SerializationLevel {
    private static HashMap<LevelScreen.Preguntas, Nivel> baseDeDatos;
    public static void main(String[] args){
        String[] preguntasSB1 = {"Sulfuro Ferroso", "Boruro de Sodio", "Carburo Cálcico",
                "Fosfuro Cobáltico", "Silicuro de Bario", "Yoduro de Zinc", "Aresenicuro Ferroso",
                "Sulfuro de Oro(III)", "Sufuro Niqueloso", "Cloruro Ferroso", "Yoduro Potásico",
                "Telururo de Calcio", "Sulfuro de Manganeso", "Bromuro de Cobre",
                "Cloruro de Aluminio", "Fluoruro de Calcio", "Fluoruro Lítico", "Cloruro de Sodio",
                "Cloruro Ferroso", "Cloruro de Hierro(III)"};
        String[] respuestas = {"FeS", "Na3B", "Ca2C", "CoP", "Ba2Si", "ZnI2", "Fe3As2", "Au2S3",
        ""};
        baseDeDatos = new HashMap<LevelScreen.Preguntas, Nivel>();

        baseDeDatos.put(LevelScreen.Preguntas.SB1, new Nivel(null, null, null));
    }

}
