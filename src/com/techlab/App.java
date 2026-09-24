package com.techlab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techlab.Excepciones.StockInsuficienteException;
import com.techlab.Pedidos.Pedido;
import com.techlab.Productos.Producto;

public class App {

    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();

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
                case 3 : 
                    System.out.println(actualizarProducto(scanner));
                    break;
                case 4 : 
                    eliminarProducto(scanner);
                    break;
                case 5 : 
                    System.out.println(crearPedido(scanner));
                    break;
                case 6 : 
                    listarPedidos();
                    break;
                default: 
                    System.out.println("Opcion incorrecta. Intente de nuevo...");
                    break;
            }
        }
        while(opcion!=7);

        scanner.close();
    }

    //PRODUCTO ======================================================
    public static void agregarProducto(Scanner sc){

        System.out.println("===================================");
        System.out.println("AGREGAR PRODUCTO");
        System.out.println("===================================");

        int codigo = leerEntero(sc, "Codigo del producto: ");
        
        if (buscarProductoPorCodigo(codigo) != null) {
            System.out.println(codigo);
            return;
        }

        Producto producto = new Producto();
        producto.setCodigo(codigo);

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

    public static String actualizarProducto(Scanner sc){
        System.out.println("===================================");
        System.out.println("ACTUALIZAR PRODUCTO");
        System.out.println("===================================");
        
        int codigo = leerEntero(sc, "Ingrese el codigo del producto que desee actualizar: ");

        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getCodigo() == codigo){

                System.out.println("Nuevo nombre: ");
                String nombre = sc.nextLine().trim();

                while (nombre.isEmpty()) {
                    System.out.print("El nombre no puede estar vacio. Intente de nuevo: ");
                    nombre = sc.nextLine().trim();
                }
                productos.get(i).setNombre(nombre);

                double precio = leerDouble(sc, "Nuevo precio: ");
                productos.get(i).setPrecio(precio);

                int stock = leerEntero(sc, "Stock: ");
                productos.get(i).setStock(stock);

                return "Producto actualizado!";
            }            
        }
        return "El producto con ID: " + codigo + " no existe";
    }

    public static void eliminarProducto(Scanner sc) {
        System.out.println("===================================");
        System.out.println("ELIMINAR PRODUCTO");
        System.out.println("===================================");

        int codigo = leerEntero(sc, "Ingrese el codigo del producto a eliminar: ");
        Producto producto = buscarProductoPorCodigo(codigo);

        if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("El producto con ID " + codigo + " no existe.");
        }
    }

    //PEDIDO ======================================================
    public static String crearPedido(Scanner sc) {
        System.out.println("===================================");
        System.out.println("CREAR PEDIDO");
        System.out.println("===================================");

        if (productos.isEmpty()) {
            return "No se pueden crear pedidos porque no hay productos registrados";
        }

        int idPedido = leerEntero(sc, "Ingrese ID del pedido: ");
        int cantidadTipos = leerEntero(sc, "Cuantos tipos de productos va a agregar?: ");

        List<Producto> productosSeleccionados = new ArrayList<>();
        List<Integer> cantidadesSeleccionadas = new ArrayList<>();

        for (int i = 0; i < cantidadTipos; i++) {
            System.out.println("\n--- Producto " + (i + 1) + " de " + cantidadTipos + " ---");

            int codigoProd = leerEntero(sc, "Ingrese el ID del producto: ");
            int cantidad = leerEntero(sc, "Ingrese la cantidad a pedir: ");

            if (cantidad <= 0) {
                System.out.println("Error: La cantidad debe ser mayor a 0.");
                i--;
                continue;
            }

            Producto producto = buscarProductoPorCodigo(codigoProd);

            if (producto == null) {
                System.out.println("Error: El producto con ID " + codigoProd + " no existe");
                i--;
                continue;
            }

            try {
                if (producto.getStock() < cantidad) {
                    throw new StockInsuficienteException("Stock insuficiente para '" + producto.getNombre()
                            + "'. Requerido: " + cantidad + " | Disponible: " + producto.getStock());
                }

                productosSeleccionados.add(producto);
                cantidadesSeleccionadas.add(cantidad);

            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
                i--;
            }
        }

        Pedido pedido = new Pedido();
        pedido.setCodigo(idPedido);

        for (int i = 0; i < productosSeleccionados.size(); i++) {
            Producto prod = productosSeleccionados.get(i);
            int cant = cantidadesSeleccionadas.get(i);

            prod.setStock(prod.getStock() - cant);
            pedido.agregarProducto(prod, cant);
        }

        pedidos.add(pedido);
        return "Pedido #" + idPedido + " creado!";
    }

    public static void listarPedidos() {
        System.out.println("===================================");
        System.out.println("LISTAR PEDIDOS");
        System.out.println("===================================");

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ". " + pedidos.get(i));
        }
    }

    // ==================================================================
    private static Producto buscarProductoPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
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
                System.out.println("Error: Debe ingresar un numero decimal valido");
            }
        }
    }
}