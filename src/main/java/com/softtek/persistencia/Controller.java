package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Controller {
    private AccesoProducto ap;

    public Controller(AccesoProducto ap) {
        this.ap = ap;
    }

    public void createProducto(Producto p){
        try {
            System.out.println(ap.create(p) ? "Producto insertado con éxito." : "No se ha podido insertar el producto.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getProductos(){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nTodos los productos:");
        System.out.println(productos);
    }

    public void getProductosOrdenados(){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nTodos los productos ordenados alfabéticamente:");
        productos.stream().sorted((p1, p2) -> p1.getNombreProducto().compareTo(p2.getNombreProducto())).forEach(System.out::println);
    }

    public void findById(int idProducto){
        try {
            System.out.println("->: " + ap.getProduct(idProducto));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void modifyProducto(Producto producto2){
        try {
            System.out.println(ap.update(producto2) ? "\nProducto actualizado con éxito." : "\nNo se ha podido actualizar el producto.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProducto(int idProducto){
        try {
            System.out.println(ap.delete(idProducto) ? "\nProducto con id = " + idProducto + " borrado con éxito." : "\nNo se ha podido borrar el producto indicado.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void findByCategoria(int categoria){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Predicate<Producto> perteneceACategoria = p -> {
            if(p.getIdCategoria() == categoria){
                return true;
            }
            return false;
        };
        System.out.println("\nProductos que pertenecen a la categoria " + categoria + ": ");
        productos.stream().filter(perteneceACategoria).forEach(System.out::println);
    }

    public void findByComienzoNombre(String letra){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Predicate<Producto> empiezaNombrePorLetra = p -> {
            if(p.getNombreProducto().substring(0,1).equals(letra)){
                return true;
            }
            return false;
        };
        System.out.println("\nProductos cuyo nombre empieza por " + letra + ": ");
        productos.stream().filter(empiezaNombrePorLetra).forEach(System.out::println);
    }

    public void findMaxPrecio(){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        productos.stream().max(Comparator.comparing(Producto::getPrecioUnitario)).ifPresentOrElse(producto -> System.out.println("\nProducto con el precio máximo: \n" + producto),
                () -> System.out.println("\nNo se encontró ningún producto."));
    }

    public void getPromedioPrecio(){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nPromedio de los precios unitarios: ");
        System.out.println(productos.stream().mapToDouble(Producto::getPrecioUnitario).average().orElse(0.00));
    }

    public void getOcurrenciasCategorias(){
        List<Producto> productos = new ArrayList<>();
        try {
            productos = ap.read();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nNumero de ocurrencias por cada categoría: ");
        Map<Integer, Long> cantidadPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getIdCategoria, Collectors.counting()));
        cantidadPorCategoria.forEach((categoria, cantidad) ->
                System.out.println("Categoría: " + categoria + ", Cantidad: " + cantidad));
    }
}
