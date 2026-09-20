package com.techlab;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.Productos.Producto;

public class App {

    private static ArrayList<Producto> productos = new ArrayList<>();

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("===================================");
            System.out.println("SISTEMA DE GESTION - TECHLAB");
            System.out.println("===================================");
            System.out.println("1- Agregar producto");
            System.out.println("2- Listar productos");
            System.out.println("3- Buscar / Actualizar producto");
            System.out.println("4- Eliminar producto");
            System.out.println("5- Crear un pedido");
            System.out.println("6- Listar pedidos");
            System.out.println("7- Salir");
            System.out.println("===================================");

            opcion= leerEntero(scanner, "Ingrese una opcion: ");

            switch (opcion) {
                case 1 : 
                    agregarProducto(scanner);
                    break;
                case 2 : 
                    listarProductos();
                    break;
                default: 
                    System.out.println("Opcion incorrecta. Intente de nuevo...");
                    break;
            }
        }
        while(opcion!=7);

        scanner.close();
    }

    public static void agregarProducto(Scanner sc){

        Producto producto = new Producto();

        System.out.println("===================================");
        System.out.println("AGREGAR PRODUCTO");
        System.out.println("===================================");

        long id = leerEntero(sc, "ID del producto: ");
        producto.setId(id);

        System.out.println("Nombre del producto: ");
        String nombre = sc.nextLine();

        while (nombre.trim().isEmpty()) {
        nombre = sc.nextLine();
    }
    producto.setNombre(nombre);

        double precio = leerDouble(sc, "Precio: ");
        producto.setPrecio(precio);
        int stock = leerEntero(sc, "Stock: ");
        producto.setStock(stock);

        productos.add(producto);
        System.out.println("Producto agregado con exito!");
    }

    public static ArrayList<Producto> listarProductos(){

        System.out.println("===================================");
        System.out.println("LISTAR PRODUCTOS");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return productos;
        }

        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i+1) + ". " + productos.get(i));
        }

        return productos;
    }

    public static int leerEntero(Scanner scanner, String mensaje){
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido.");
            }
        }
    }

    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero decimal valido.");
            }
        }
    }
}