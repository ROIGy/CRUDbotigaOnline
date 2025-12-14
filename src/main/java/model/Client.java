/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alumne
 */
public class Client {
    // Atributs
    private int id;
    private String nom;
    private String correu;

    // Constructor complet
    public Client(int id, String nom, String correu) {
        this.id = id;
        this.nom = nom;
        this.correu = correu;
    }

    // Constructor sense buit (útil per a noves insercions on l'ID és AUTO_INCREMENT i afegeixes els attrs a posteriori)
    public Client() {
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreu() {
        return correu;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    // toString()
    @Override
    public String toString() {
        return "\n- Client -" +
                "\nid: " + id +
                "\nnom: '" + nom + '\'' +
                "\ncorreu: '" + correu + '\'' ;
    }
}
