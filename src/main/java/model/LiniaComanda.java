/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alumne
 */
public class LiniaComanda {
    // Atributs
    private int id;
    private int comandaId;
    private int producteId;
    private int quantitat;
    private double preuUnitari;

    // Constructor complet
    public LiniaComanda(int id, int comandaId, int producteId, int quantitat, double preuUnitari) {
        this.id = id;
        this.comandaId = comandaId;
        this.producteId = producteId;
        this.quantitat = quantitat;
        this.preuUnitari = preuUnitari;
    }

    // Constructor sense ID
    public LiniaComanda(int comandaId, int producteId, int quantitat, double preuUnitari) {
        this.comandaId = comandaId;
        this.producteId = producteId;
        this.quantitat = quantitat;
        this.preuUnitari = preuUnitari;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getComandaId() {
        return comandaId;
    }

    public int getProducteId() {
        return producteId;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public double getPreuUnitari() {
        return preuUnitari;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setComandaId(int comandaId) {
        this.comandaId = comandaId;
    }

    public void setProducteId(int producteId) {
        this.producteId = producteId;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public void setPreuUnitari(double preuUnitari) {
        this.preuUnitari = preuUnitari;
    }

    // toString()
    @Override
    public String toString() {
        return "LiniaComanda{" +
                "id=" + id +
                ", comandaId=" + comandaId +
                ", producteId=" + producteId +
                ", quantitat=" + quantitat +
                ", preuUnitari=" + preuUnitari +
                '}';
    }
}
