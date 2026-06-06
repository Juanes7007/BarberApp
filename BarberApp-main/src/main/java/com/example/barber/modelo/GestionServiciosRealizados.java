package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionServiciosRealizados {

    private static final String FILE_PATH = "serviciosRealizados.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static List<ServicioRealizado> obtenerServiciosRealizados() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            guardarServiciosRealizados(new ArrayList<>());

            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<ServicioRealizado>>(){}.getType();

            List<ServicioRealizado> lista =
                    gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarServiciosRealizados(
            List<ServicioRealizado> servicios) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(servicios, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarServicioRealizado(
            ServicioRealizado servicio) {

        List<ServicioRealizado> servicios =
                obtenerServiciosRealizados();

        for (ServicioRealizado s : servicios) {

            if (s.getId()
                    .equalsIgnoreCase(servicio.getId())) {

                return false;
            }
        }

        servicios.add(servicio);

        guardarServiciosRealizados(servicios);

        return true;
    }

    public static boolean actualizarServicioRealizado(
            ServicioRealizado servicio) {

        List<ServicioRealizado> servicios =
                obtenerServiciosRealizados();

        for (int i = 0; i < servicios.size(); i++) {

            if (servicios.get(i).getId()
                    .equalsIgnoreCase(servicio.getId())) {

                servicios.set(i, servicio);

                guardarServiciosRealizados(servicios);

                return true;
            }
        }

        return false;
    }

    public static boolean eliminarServicioRealizado(
            String id) {

        List<ServicioRealizado> servicios =
                obtenerServiciosRealizados();

        boolean eliminado =
                servicios.removeIf(
                        s -> s.getId()
                                .equalsIgnoreCase(id));

        if (eliminado) {

            guardarServiciosRealizados(servicios);
        }

        return eliminado;
    }
}