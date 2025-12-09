/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alumne
 */
public class Descompte {
    // Atributs
    private int id;
    private int producteId;
    private String tipus; // Pot ser "%" o "€"
    private double valor;

    // Constructor complet
    public Descompte(int id, int producteId, String tipus, double valor) {
        this.id = id;
        this.producteId = producteId;
        this.tipus = tipus;
        this.valor = valor;
    }

    // Constructor sense ID
    public Descompte(int producteId, String tipus, double valor) {
        this.producteId = producteId;
        this.tipus = tipus;
        this.valor = valor;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getProducteId() {
        return producteId;
    }

    public String getTipus() {
        return tipus;
    }

    public double getValor() {
        return valor;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProducteId(int producteId) {
        this.producteId = producteId;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // toString()
    @Override
    public String toString() {
        return "Descompte{" +
                "id=" + id +
                ", producteId=" + producteId +
                ", tipus='" + tipus + '\'' +
                ", valor=" + valor +
                '}';
    }
}
