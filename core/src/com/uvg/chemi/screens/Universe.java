package com.uvg.chemi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by Chromz(Rodrigo Custodio) on 10/15/2015.
 */
public final class Universe {
    private static HashMap<String, Nivel[]> baseDeDatos;

    public static HashMap<String, Nivel[]> getBaseDeDatos(){

        JsonValue jsonValue = new JsonReader().parse(Gdx.files.local("SalesBinarias.json"));


        return baseDeDatos;

    }

}
