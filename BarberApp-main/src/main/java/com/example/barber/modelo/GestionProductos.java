package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionProductos {

    private static final String FILE_PATH = "productos.json";

    private static final Gson gson
            = new GsonBuilder().setPrettyPrinting().create();

    public static List<Producto> obtenerProductos() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            List<Producto> productos
                    = new ArrayList<>();

            productos.add(
                    new Producto(
                            "P001",
                            "Shampoo",
                            20,
                            10000,
                            15000));

            guardarProductos(productos);

            return productos;
        }

        try (Reader reader = new FileReader(file)) {

            Type listType
                    = new TypeToken<ArrayList<Producto>>() {
                    }.getType();

            List<Producto> lista
                    = gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarProductos(List<Producto> productos) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(productos, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarProducto(Producto producto) {

        List<Producto> productos
                = obtenerProductos();

        for (Producto p : productos) {

            if (p.getId()
                    .equalsIgnoreCase(producto.getId())) {

                return false;
            }
        }

        productos.add(producto);

        guardarProductos(productos);

        return true;
    }

    public static boolean actualizarProducto(
            Producto producto) {

        List<Producto> productos
                = obtenerProductos();

        for (int i = 0; i < productos.size(); i++) {

            if (productos.get(i).getId()
                    .equalsIgnoreCase(producto.getId())) {

                productos.set(i, producto);

                guardarProductos(productos);

                return true;
            }
        }

        return false;
    }

    public static boolean eliminarProducto(String id) {

        List<Producto> productos
                = obtenerProductos();

        boolean eliminado
                = productos.removeIf(
                        p -> p.getId()
                                .equalsIgnoreCase(id));

        if (eliminado) {
            guardarProductos(productos);
        }

        return eliminado;
    }
}
