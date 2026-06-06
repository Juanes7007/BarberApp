
package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionServicios {

    private static final String FILE_PATH = "servicios.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static List<Servicio> obtenerServicios() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            List<Servicio> servicios = new ArrayList<>();

            servicios.add(new Servicio("1s","Corte",15000));
            servicios.add(new Servicio("1sd","Barba",10000));

            guardarServicios(servicios);

            return servicios;
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<Servicio>>(){}.getType();

            List<Servicio> lista =
                    gson.fromJson(reader,listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarServicios(List<Servicio> servicios) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(servicios,writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarServicio(Servicio servicio) {

        List<Servicio> servicios = obtenerServicios();

        for (Servicio s : servicios) {

            if (s.getNombre().equalsIgnoreCase(servicio.getNombre())) {
                return false;
            }
        }

        servicios.add(servicio);

        guardarServicios(servicios);

        return true;
    }
}