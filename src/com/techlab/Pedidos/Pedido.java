package com.techlab.Pedidos;

import java.util.ArrayList;
import java.util.List;

import com.techlab.Productos.Producto;

public class Pedido {
    private int codigo;
    private List<LineaPedido> lineas = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
        return "Pedido Codigo: " + codigo + " | Items: " + lineas + " | Total: $" + calcularTotal();
    }
}