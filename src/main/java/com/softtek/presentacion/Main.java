package com.softtek.presentacion;

import com.softtek.modelo.Producto;
import com.softtek.persistencia.AccesoProducto;
import com.softtek.persistencia.Conexion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccesoProducto ap = new AccesoProducto();

        Producto p = new Producto(100, "Producto prueba", 2, 2, null, 25, 200, 3, 5, 0);
        try {
            System.out.println(ap.create(p) ? "Producto insertado con éxito." : "No se ha podido insertar el producto.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("->: " + ap.getProduct(100));

        Producto p2 = new Producto("Producto actulizado", 2, 2, null, 0, 0, 0, 0, 0);
        System.out.println(ap.update(100,p2) ? "\nProducto actualizado con éxito." : "\nNo se ha podido actualizar el producto.");
        System.out.println("->: " + ap.getProduct(100));

        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nTodos los productos:");
        System.out.println(productos);

        try {
            System.out.println(ap.delete(100) ? "\nProducto con id = " + 100 + " borrado con éxito." : "\nNo se ha podido borrar el producto indicado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
