package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionProveedores {

    private static final String FILE_PATH = "proveedores.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static List<Proveedor> obtenerProveedores() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            guardarProveedores(new ArrayList<>());

            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<Proveedor>>(){}.getType();

            List<Proveedor> lista =
                    gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarProveedores(
            List<Proveedor> proveedores) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(proveedores, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarProveedor(
            Proveedor proveedor) {

        List<Proveedor> proveedores =
                obtenerProveedores();

        for (Proveedor p : proveedores) {

            if (p.getId()
                    .equalsIgnoreCase(proveedor.getId())) {

                return false;
            }
        }

        proveedores.add(proveedor);

        guardarProveedores(proveedores);

        return true;
    }

    public static boolean actualizarProveedor(
            Proveedor proveedor) {

        List<Proveedor> proveedores =
                obtenerProveedores();

        for (int i = 0; i < proveedores.size(); i++) {

            if (proveedores.get(i).getId()
                    .equalsIgnoreCase(proveedor.getId())) {

                proveedores.set(i, proveedor);

                guardarProveedores(proveedores);

                return true;
            }
        }

        return false;
    }

    public static boolean eliminarProveedor(String id) {

        List<Proveedor> proveedores =
                obtenerProveedores();

        boolean eliminado =
                proveedores.removeIf(
                        p -> p.getId()
                                .equalsIgnoreCase(id));

        if (eliminado) {

            guardarProveedores(proveedores);
        }

        return eliminado;
    }
}