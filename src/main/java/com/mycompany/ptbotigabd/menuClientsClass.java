/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ptbotigabd;

import dao.ClientDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Client;

/**
 *
 * @author jorda
 */
public class menuClientsClass {
    //GESTIONAR CLIENTS

    public ClientDAO clDao = new ClientDAO();
    private Scanner sc = new Scanner(System.in);

    public void menuClients() {

        System.out.println("---- GESTIONA CLIENTS ----\n"
                + "1. Crea client\n"
                + "2. Llista els clients\n"
                + "3. Edita un client\n"
                + "4. Elimina un client\n");
        int opcio = sc.nextInt();
        sc.nextLine();
        int idCl;
        switch (opcio) {
            case 1:
                System.out.println("Nom: ");
                String nom = sc.nextLine();
                System.out.println("Correu: ");
                String correu = sc.nextLine();

                creaCl(nom, correu);
                break;

            case 2:
                System.out.println("Llistant clients: ");

                try {
                    List<Client> llistaCl = clDao.llistar();
                    for (Client client : llistaCl) {
                        System.out.println(client.toString());
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }

                break;

            case 3:
                System.out.println("Introdueix ID del client que vols editar: ");
                idCl = sc.nextInt();
                //----------------------------------------------------------------

                System.out.println("\nCleint en estat actual:");
                Client clAntic = cercaCl(idCl);
                System.out.println(clAntic.toString());
                //----------------------------------------------------------------

                System.out.println("Introdueix nous valors (si vols mantenir els que hi ha només prem Enter a Nom i -1 als valors numèrics)");

                //NOM
                System.out.println("Nom: ");
                String nomN = sc.nextLine();
                
                //CORREU
                System.out.println("Correu: ");
                String correuN = sc.nextLine();

                //Creem nou objecte Client i cridem mètode per actualitzar-lo a BD
                Client clNou = new Client();
                clNou.setNom(nomN);
                clNou.setCorreu(correuN);

                try {
                    actualitzarCl(clNou, clAntic);
                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }

                break;
            case 4:
                System.out.println("Introdueix ID del client que vols eliminar: ");
                idCl = sc.nextInt();
                try {
                    int elimCorr = clDao.eliminar(idCl);
                    if (elimCorr == 1) {
                        System.out.println("Client eliminat correctament");
                    }

                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }

                break;

        }
    }

    public Client creaCl(String nom, String correu) {
        Client cl = new Client();
        cl.setNom(nom);
        cl.setCorreu(correu);
        try {
            clDao.inserir(cl);
        } catch (SQLException e) {
            System.out.println("Error en consulta SQL (INSERT)");
            e.printStackTrace();
        }

        return cl;
    }

    public Client cercaCl(int id) {
        Client cl = new Client();
        try {
            cl = clDao.obtenirPerId(id);
        } catch (SQLException e) {
            System.out.println("Error en consulta SQL (SELECT)");
            e.printStackTrace();
        }
        return cl;
    }

    public void actualitzarCl(Client clNou, Client clAntic) throws SQLException {

        Client clFinal = new Client();

        String nomFinal = "";
        if (!clNou.getNom().equals(nomFinal)) {
            nomFinal = clNou.getNom();// Si està buit, agafem el vell
        }

        String correuFinal = "";
        if (!clNou.getCorreu().equals(correuFinal)) {
            nomFinal = clNou.getNom();// Si està buit, agafem el vell
        }

        clFinal.setNom(nomFinal);
        clFinal.setCorreu(correuFinal);

        clDao.actualitzar(clFinal);
    }
}
