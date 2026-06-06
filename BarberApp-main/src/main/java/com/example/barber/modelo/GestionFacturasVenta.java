package com.example.barber.modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestionFacturasVenta {

    private static final String FILE_PATH = "facturasVenta.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    public static List<FacturaVenta> obtenerFacturas() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {

            guardarFacturas(new ArrayList<>());

            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<FacturaVenta>>(){}.getType();

            List<FacturaVenta> lista =
                    gson.fromJson(reader, listType);

            return lista != null ? lista : new ArrayList<>();

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    public static void guardarFacturas(
            List<FacturaVenta> facturas) {

        try (Writer writer = new FileWriter(FILE_PATH)) {

            gson.toJson(facturas, writer);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static boolean registrarFactura(
            FacturaVenta factura) {

        List<FacturaVenta> facturas =
                obtenerFacturas();

        for (FacturaVenta f : facturas) {

            if (f.getId()
                    .equalsIgnoreCase(factura.getId())) {

                return false;
            }
        }

        facturas.add(factura);

        guardarFacturas(facturas);

        return true;
    }
}