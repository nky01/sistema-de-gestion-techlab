package com.techlab;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.Productos.Producto;

public class App {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ArrayList<Producto> productos = new ArrayList<>();

        int opcion;

        do{
            System.out.println("===================================");
            System.out.println("SISTEMA DE GESTION - TECHLAB");
            System.out.println("===================================");
            System.out.println("1- Agregar producto");
            System.out.println("2- Listar productos");
            System.out.println("3- Consultar un producto");
            System.out.println("4- Modificar un producto");
            System.out.println("5- Eliminar un producto");
            System.out.println("0- Salir");
            System.out.println("===================================");

            opcion= leerEntero(scanner, "Ingrese una opcion: ");

            switch (opcion) {
                case 1 : 
                    ingresarProducto(scanner, productos);
                    break;
                case 2 : 
                    listarProductos(productos);
                    break;
                case 3 : 
                    consultarProducto(scanner, productos);
                    break;
                case 4 : 
                    modificarArticulo(scanner, productos);
                    break;
                case 5 : 
                    eliminarProducto(scanner, productos);
                    break;
                case 0: 
                    System.out.println("\nSaliendo del sistema. Nos vemos!");
                    break;
                default: 
                    System.out.println("Error: La opcion no es valida, intente de nuevo");
                    break;
            }
        }
        while(opcion!=0);

        scanner.close();
    }

    // INGRESAR PRODUCTO ======================================================
    public static void ingresarProducto(Scanner scanner, ArrayList<Producto> productos) {
        System.out.println("===================================");
        System.out.println("INGRESAR PRODUCTO");
        System.out.println("===================================");

        int codigo = leerEntero(scanner, "Ingrese el codigo del producto: ");

        if (buscarProductoPorCodigo(productos, codigo) != null) {
            System.out.println("Error: ya existe un producto con ese codigo.");
            return;
        }

        String nombre = leerTextoNoVacio(scanner, "Ingrese el nombre del producto: ");
        double precio = leerDoubleNoNegativo(scanner, "Ingrese su precio: ");

        Producto producto = new Producto(codigo, nombre, precio);

        productos.add(producto);

        System.out.println("El producto fue ingresado correctamente!");
    }

    // LISTAR LOS PRODUCTOS ======================================================
    public static ArrayList<Producto> listarProductos(ArrayList<Producto> productos){
        System.out.println("===================================");
        System.out.println("LISTAR PRODUCTOS");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            System.out.println("Error: No hay productos registrados");
            return productos;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }

        return productos;
    }

    // CONSULTAR POR UN PRODUCTO ======================================================
    public static void consultarProducto(Scanner scanner, ArrayList<Producto> productos) {

        System.out.println("===================================");
        System.out.println("CONSULTAR POR PRODUCTO");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            System.out.println("Error: No hay productos cargados");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el codigo del producto a consultar: ");

        Producto producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("Error: El producto no existe");
        } else {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        }
    }

    // MODIFICAR PRODUCTO ======================================================
    public static void modificarArticulo(Scanner scanner, ArrayList<Producto> productos) {

        System.out.println("===================================");
        System.out.println("MODIFICAR PRODUCTO");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            System.out.println("Error: No hay producto cargados");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el codigo del producto a modificar: ");

        Producto articulo = buscarProductoPorCodigo(productos, codigo);

        if (articulo == null) {
            System.out.println("El producto no existe");
            return;
        }

        String nuevoNombre = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del producto: ");
        double nuevoPrecio = leerDoubleNoNegativo(scanner, "Ingrese su nuevo precio: ");

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);

        System.out.println("El producto fue modificado correctamente!");
    }

    
    // ELIMINAR PRODUCTO ======================================================
    public static void eliminarProducto(Scanner scanner, ArrayList<Producto> productos) {
        
        System.out.println("===================================");
        System.out.println("ELIMINAR PRODUCTO");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            System.out.println("Error: No hay productos cargados");
            return;
        }

        int codigo = leerEntero(scanner, "Ingrese el codigo del producto a eliminar: ");

        Producto producto = buscarProductoPorCodigo(productos, codigo);

        if (producto == null) {
            System.out.println("Error: El producto no existe");
            return;
        }

        productos.remove(producto);

        System.out.println("Producto eliminado correctamente!");
    }

    
    // METODOS AUXILIARE ==================================================================
    public static Producto buscarProductoPorCodigo(ArrayList<Producto> productos, int codigo) {

        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }
    
    public static int leerEntero(Scanner scanner, String mensaje){
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: tiene que ingresar un numero entero valido");
            }
        }
    }

    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Tiene que ingresar un numero decimal valido");
            }
        }
    }

    public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {

        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Tiene que ingresar un numero decimal valido");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();

            if (!texto.trim().isEmpty()) {
                return texto.trim();
            }

            System.out.println("Error: el texto no puede estar vacio");
        }
    }
}