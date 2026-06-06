package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionClientes {

    private static final String FILE_PATH = "clientes.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static List<Cliente> obtenerClientes() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            guardarClientes(new ArrayList<>());
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<Cliente>>() {}.getType();

            List<Cliente> lista =
                    gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarClientes(List<Cliente> clientes) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(clientes, writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean registrarCliente(Cliente cliente) {

        List<Cliente> clientes = obtenerClientes();

        for (Cliente c : clientes) {

            if (c.getId().equalsIgnoreCase(cliente.getId())) {
                return false;
            }
        }

        clientes.add(cliente);
        guardarClientes(clientes);

        return true;
    }

    public static boolean eliminarCliente(String id) {

        List<Cliente> clientes = obtenerClientes();

        boolean eliminado =
                clientes.removeIf(c -> c.getId().equalsIgnoreCase(id));

        if (eliminado) {
            guardarClientes(clientes);
        }

        return eliminado;
    }

    public static boolean actualizarCliente(Cliente cliente) {

        List<Cliente> clientes = obtenerClientes();

        for (int i = 0; i < clientes.size(); i++) {

            if (clientes.get(i).getId()
                    .equalsIgnoreCase(cliente.getId())) {

                clientes.set(i, cliente);

                guardarClientes(clientes);

                return true;
            }
        }

        return false;
    }
}
