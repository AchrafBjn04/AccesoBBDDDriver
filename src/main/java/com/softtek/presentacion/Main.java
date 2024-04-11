package com.softtek.presentacion;

import com.softtek.modelo.Producto;
import com.softtek.persistencia.AccesoProducto;
import com.softtek.persistencia.Conexion;
import com.softtek.persistencia.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccesoProducto ap = new AccesoProducto();
        Controller controller = new Controller(ap);
        Producto p = new Producto(100, "Producto prueba", 2, 2, null, 25, 200, 3, 5, 0);
        Producto p2 = new Producto(p.getIdProducto(), "Producto actulizado", 2, 2, null, 0, 0, 0, 0, 0);

        controller.createProducto(p);
        controller.findById(p.getIdProducto());
        controller.modifyProducto(p2);
        controller.findById(p.getIdProducto());
        controller.getProductos();
        controller.deleteProducto(p.getIdProducto());
        controller.findByCategoria(2);
        controller.findByComienzoNombre("N");
        controller.getProductosOrdenados();
        controller.findMaxPrecio();
        controller.getPromedioPrecio();
        controller.getOcurrenciasCategorias();
    }
}
