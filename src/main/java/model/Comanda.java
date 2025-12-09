/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author alumne
 */
public class Comanda {
    // Atributs
    private int id;
    private int clientId;
    private LocalDateTime data;
    private double total;

    // Constructor complet (amb data i total)
    public Comanda(int id, int clientId, LocalDateTime data, double total) {
        this.id = id;
        this.clientId = clientId;
        this.data = data;
        this.total = total;
    }

    // Constructor sense ID, data i total (útil per a creació, on SQL assigna els valors per defecte)
    public Comanda(int clientId) {
        this.clientId = clientId;
        // La data i el total s'actualitzaran després de la inserció o càlcul
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getTotal() {
        return total;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // toString()
    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
