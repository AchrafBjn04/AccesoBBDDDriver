package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoProducto extends Conexion {
    public boolean create(Producto p) throws SQLException, ClassNotFoundException {
        abrirConexion();
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
            pstmt.close();
            miConexion.close();
            return true;
        }
        pstmt.close();
        miConexion.close();
        return false;
    }

    public List<Producto> read() throws SQLException, ClassNotFoundException {
        abrirConexion();
        Statement stmt;
        ResultSet rs;
        String sql = "SELECT * FROM products";
        List<Producto> productos = new ArrayList<>();

        stmt = miConexion.createStatement();
        rs = stmt.executeQuery(sql);

        while (rs.next()){
            productos.add(new Producto(rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("supplier_id"),
                    rs.getInt("category_id"),
                    rs.getString("quantity_per_unit"),
                    rs.getDouble("unit_price"),
                    rs.getInt("units_in_stock"),
                    rs.getInt("units_on_order"),
                    rs.getInt("reorder_level"),
                    rs.getInt("discontinued")));
        }
        rs.close();
        stmt.close();
        miConexion.close();
        return productos;
    }

    public Producto getProduct(int idProducto) throws SQLException, ClassNotFoundException {
        abrirConexion();
        Producto producto = null;
        PreparedStatement pstmt;
        ResultSet rs;
        String sql = "SELECT * FROM products WHERE product_id = ?";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, idProducto);
        rs = pstmt.executeQuery();

        while (rs.next()){
            producto = new Producto(rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("supplier_id"),
                    rs.getInt("category_id"),
                    rs.getString("quantity_per_unit"),
                    rs.getDouble("unit_price"),
                    rs.getInt("units_in_stock"),
                    rs.getInt("units_on_order"),
                    rs.getInt("reorder_level"),
                    rs.getInt("discontinued"));
        }
        rs.close();
        pstmt.close();
        miConexion.close();
        return producto;
    }

    public boolean update(Producto p) throws SQLException, ClassNotFoundException {
        abrirConexion();
        PreparedStatement pstmt;
        String sql = "UPDATE products SET product_id = ?, product_name = ?, supplier_id = ?, category_id = ?, quantity_per_unit = ?, unit_price = ?, units_in_stock = ?, units_on_order = ?, reorder_level = ?, discontinued = ? WHERE product_id = ?";

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
        pstmt.setInt(11, p.getIdProducto());

        if (pstmt.executeUpdate()>0){
            pstmt.close();
            miConexion.close();
            return true;
        }
        pstmt.close();
        miConexion.close();
        return false;
    }

    public boolean delete(int idProducto) throws SQLException, ClassNotFoundException {
        abrirConexion();
        PreparedStatement pstmt;
        String sql = "DELETE FROM products WHERE product_id = ?";

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, idProducto);
        if (pstmt.executeUpdate()>0){
            pstmt.close();
            miConexion.close();
            return true;
        }
        pstmt.close();
        miConexion.close();
        return false;
    }
}
