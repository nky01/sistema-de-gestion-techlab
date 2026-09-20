package com.techlab;

import java.util.Scanner;

public class Menu {

    public static void main(){

        Scanner scanner = new Scanner(System.in);

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
                case value:
                    
                    break;
            
                default:
                    break;
            }
        }
        while(opcion!=7);

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
}