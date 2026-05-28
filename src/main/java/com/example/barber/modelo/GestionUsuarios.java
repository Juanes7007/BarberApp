package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionUsuarios {

    private static final String FILE_PATH = "usuarios.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static List<Usuario> obtenerUsuarios() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            List<Usuario> defecto = new ArrayList<>();
            defecto.add(new Usuario("admin", "1234"));
            defecto.add(new Usuario("juanes", "barber2026"));
            guardarUsuarios(defecto);
            return defecto;
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
            List<Usuario> lista = gson.fromJson(reader, listType);
            return lista != null ? lista : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarUsuarios(List<Usuario> listaUsuarios) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaUsuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método clave para validar el login
    public static boolean validarLogin(String user, String pass) {
        List<Usuario> usuarios = obtenerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(user) && u.getPassword().equals(pass)) {
                return true;
            }
        }
        return false;
    }
}