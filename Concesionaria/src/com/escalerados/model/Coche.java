package com.escalerados.model;

public class Coche {
    //PK
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private double precio;

    //FK
    private Cliente cliente;

    public Coche() {

    }

    public Coche(String placa, String marca, String modelo, String color, double precio, Cliente cliente) {
        this.setPlaca(placa);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setColor(color);
        this.setPrecio(precio);
        this.setCliente(cliente);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                '}';
    }
}