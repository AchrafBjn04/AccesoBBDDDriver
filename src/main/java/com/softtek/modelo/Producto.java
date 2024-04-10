package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int idProveedor;
    private int idCategoria;
    private String cantidadPorUnidad;
    private double precioUnitario;
    private int unidadesStock;
    private int unidadesPedido;
    private int nivelReorden;
    private int discontinuidad;


    @Override
    public String toString() {
        return "\nProducto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", unidadesStock=" + unidadesStock +
                '}';
    }

    public Producto(String nombreProducto, int idProveedor, int idCategoria, String cantidadPorUnidad, double precioUnitario, int unidadesStock, int unidadesPedido, int nivelReorden, int discontinuidad) {
        this.nombreProducto = nombreProducto;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.cantidadPorUnidad = cantidadPorUnidad;
        this.precioUnitario = precioUnitario;
        this.unidadesStock = unidadesStock;
        this.unidadesPedido = unidadesPedido;
        this.nivelReorden = nivelReorden;
        this.discontinuidad = discontinuidad;
    }
}
