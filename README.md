# Sistema de Gestión - TechLab 🚀

Sistema de consola desarrollado en **Java** para la gestión integral de productos y procesamiento de pedidos, con manejo de excepciones personalizadas para el control de stock.

---

## 📌 Funcionalidades

* **Gestión de Productos (CRUD):**
  * Agregar nuevos productos (validando IDs duplicados).
  * Listar productos registrados con detalle de precio y stock.
  * Buscar y actualizar datos de un producto.
  * Eliminar productos por ID.
* **Gestión de Pedidos:**
  * Crear pedidos seleccionando múltiples productos y cantidades.
  * Control automático de stock mediante `StockInsuficienteException`.
  * Descuento automático de inventario al confirmar el pedido.
  * Listar pedidos realizados con cálculo del total.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 8+)
* **Estructuras de datos:** `ArrayList`, `List`
* **Entrada/Salida:** `java.util.Scanner`
* **Manejo de Errores:** Excepciones personalizadas (`Exception`) y bloque `try-catch`

---

## 📂 Estructura del Proyecto

```text
src/com/techlab/
│
├── App.java                        # Clase principal con el menú interactivo
├── Excepciones/
│   └── StockInsuficienteException.java  # Excepción personalizada de stock
├── Pedidos/
│   ├── LineaPedido.java            # Detalle de cada ítem en el pedido
│   └── Pedido.java                 # Entidad del pedido y cálculo total
└── Productos/
    └── Producto.java               # Entidad del producto
