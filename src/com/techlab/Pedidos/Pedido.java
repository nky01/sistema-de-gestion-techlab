package com.techlab.Pedidos;

import java.util.ArrayList;

import com.techlab.Productos.Producto;

public class Pedido {
    private Long id;
    private ArrayList<Producto> productos;
    
    public static double costoTotal(double precio, int cantidad){
        double total = precio * cantidad;
        return total;
    }
}