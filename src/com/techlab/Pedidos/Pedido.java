package com.techlab.Pedidos;

import java.util.ArrayList;
import java.util.List;

import com.techlab.Productos.Producto;

public class Pedido {
    private int id;
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        this.lineas.add(new LineaPedido(producto, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Items: " + lineas + " | Total: $" + calcularTotal();
    }
}