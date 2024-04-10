package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoProducto extends Conexion {
    public AccesoProducto(){
        try {
            abrirConexion();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Producto p) throws SQLException {
        PreparedStatement pstmt;
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, p.getIdProducto());
        pstmt.setString(2, p.getNombreProducto());
        pstmt.setInt(3, p.getIdProveedor());
        pstmt.setInt(4, p.getIdCategoria());
        pstmt.setString(5, p.getCantidadPorUnidad());
        pstmt.setDouble(6, p.getPrecioUnitario());
        pstmt.setInt(7, p.getUnidadesStock());
        pstmt.setInt(8, p.getUnidadesPedido());
        pstmt.setInt(9, p.getNivelReorden());
        pstmt.setInt(10, p.getDiscontinuidad());

        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

    public List<Producto> read() throws SQLException {
        Statement sentencia;
        ResultSet resultado;
        String sql = "SELECT * FROM products";
        List<Producto> productos = new ArrayList<>();

        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);

        while (resultado.next()){
            productos.add(new Producto(resultado.getInt("product_id"),
                    resultado.getString("product_name"),
                    resultado.getInt("supplier_id"),
                    resultado.getInt("category_id"),
                    resultado.getString("quantity_per_unit"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("units_in_stock"),
                    resultado.getInt("units_on_order"),
                    resultado.getInt("reorder_level"),
                    resultado.getInt("discontinued")));
        }
        return productos;
    }

    public Producto getProduct(int idProducto) throws SQLException {
        Producto producto = null;
        PreparedStatement pstmt;
        ResultSet resultado;
        String sql = "SELECT * FROM products WHERE product_id = ?";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, idProducto);
        resultado = pstmt.executeQuery();

        while (resultado.next()){
            producto = new Producto(resultado.getInt("product_id"),
                    resultado.getString("product_name"),
                    resultado.getInt("supplier_id"),
                    resultado.getInt("category_id"),
                    resultado.getString("quantity_per_unit"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("units_in_stock"),
                    resultado.getInt("units_on_order"),
                    resultado.getInt("reorder_level"),
                    resultado.getInt("discontinued"));
        }
        return producto;
    }

    public boolean update(int idProducto, Producto p) throws SQLException {
        PreparedStatement pstmt;
        String sql = "UPDATE products SET product_id = ?, product_name = ?, supplier_id = ?, category_id = ?, quantity_per_unit = ?, unit_price = ?, units_in_stock = ?, units_on_order = ?, reorder_level = ?, discontinued = ? WHERE product_id = ?";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, idProducto);
        pstmt.setString(2, p.getNombreProducto());
        pstmt.setInt(3, p.getIdProveedor());
        pstmt.setInt(4, p.getIdCategoria());
        pstmt.setString(5, p.getCantidadPorUnidad());
        pstmt.setDouble(6, p.getPrecioUnitario());
        pstmt.setInt(7, p.getUnidadesStock());
        pstmt.setInt(8, p.getUnidadesPedido());
        pstmt.setInt(9, p.getNivelReorden());
        pstmt.setInt(10, p.getDiscontinuidad());
        pstmt.setInt(11, idProducto);

        if (pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

    public boolean delete(int idProducto) throws SQLException {
        PreparedStatement pstmt;
        String sql = "DELETE FROM products WHERE product_id = ?";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, idProducto);
        if (pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }

}
