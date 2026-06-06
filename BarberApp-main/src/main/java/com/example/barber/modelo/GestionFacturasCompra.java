package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionFacturasCompra {

    private static final String FILE_PATH = "facturasCompra.json";

    private static final Gson gson
            = new GsonBuilder().setPrettyPrinting().create();

    public static List<FacturaCompra> obtenerFacturasCompra() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            guardarFacturasCompra(new ArrayList<>());

            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType
                    = new TypeToken<ArrayList<FacturaCompra>>() {
                    }.getType();

            List<FacturaCompra> lista
                    = gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarFacturasCompra(
            List<FacturaCompra> facturas) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(facturas, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarFacturaCompra(
            FacturaCompra factura) {

        List<FacturaCompra> facturas
                = obtenerFacturasCompra();

        for (FacturaCompra f : facturas) {

            if (f.getId()
                    .equalsIgnoreCase(factura.getId())) {

                return false;
            }
        }

        facturas.add(factura);

        guardarFacturasCompra(facturas);

        return true;
    }
}
