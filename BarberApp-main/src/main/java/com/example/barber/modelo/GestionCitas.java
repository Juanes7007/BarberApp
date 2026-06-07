/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Me
 */
public class GestionCitas {

    private static final String FILE_PATH = "citas.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<CitasDTO> obtenerCitas() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
          Type listType = new TypeToken<ArrayList<CitasDTO>>(){}.getType();
            List<CitasDTO> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public static void guardarCitas(List<CitasDTO> listaCitas) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaCitas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean VerificarDisponibilidad(String fecha, String hora) {
        List<CitasDTO> citas = obtenerCitas();
        for (CitasDTO cita : citas) {
            if (cita.getHora().equals(hora) && cita.getFecha().equals(fecha)) {
                return false;
            }

        }
        return true;
    }
    
    public static void actualizarCita(CitasDTO citaActualizada) {

    List<CitasDTO> lista = obtenerCitas();

    for (int i = 0; i < lista.size(); i++) {

        if (lista.get(i).getId() != null &&
            lista.get(i).getId().equals(citaActualizada.getId())) {

            lista.set(i, citaActualizada);
            break;
        }
    }

    guardarCitas(lista);
}    
        

}