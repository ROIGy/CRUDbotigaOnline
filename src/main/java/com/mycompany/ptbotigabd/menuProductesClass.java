/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ptbotigabd;

import dao.ProducteDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Producte;

/**
 *
 * @author jorda
 */
public class menuProductesClass {
    //GESTIONAR PRODUCTES
    
    public ProducteDAO pDao = new ProducteDAO();
    private Scanner sc = new Scanner(System.in);
            
    public void menuProductes() {

        System.out.println("---- GESTIONA PRODUCTES ----\n"
                + "1. Crea producte\n"
                + "2. Llista els productes\n"
                + "3. Edita un producte\n"
                + "4. Elimina un producte\n");
        int opcio = sc.nextInt();
        sc.nextLine();
        int idProd;
        switch (opcio) {
            case 1:
                System.out.println("Nom: ");
                String nom = sc.nextLine();
                System.out.println("Preu: ");
                Double preu = null;
                try {
                    preu = sc.nextDouble();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    break;
                }

                System.out.println("Estoc: ");
                int estoc = sc.nextInt();
                creaProd(nom, preu, estoc);
                break;

            case 2:
                System.out.println("Llistant productes: ");

                try {
                    List<Producte> llistaP = pDao.llistar();
                    for (Producte producte : llistaP) {
                        System.out.println(producte.toString());
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }
                
//                idProd = sc.nextInt();
//                Producte pProva = new Producte();
//                pProva.getNom();
//                System.out.println(pProva.toString());
//
//                Producte p = cercaProd(idProd);
//                if (!p.getNom().equals(null)) {
//                    System.out.println(p.toString());
//
//                } else {
//                    System.out.println("No hi ha cap producte amb aquest ID");
//                }
                

                break;
                
            case 3:
                System.out.println("Introdueix ID del producte que vols editar: ");
                idProd = sc.nextInt();
                //----------------------------------------------------------------

                System.out.println("\nProducte en estat actual:");
                Producte pAntic = cercaProd(idProd);
                System.out.println(pAntic.toString());
                //----------------------------------------------------------------

                System.out.println("Introdueix nous valors (si vols mantenir els que hi ha només prem Enter a Nom i -1 als valors numèrics)");

                //NOM
                System.out.println("Nom: ");
                String nomN = sc.nextLine();

                //PREU
                System.out.println("Preu: ");
                Double preuN = -1.0;
                try {
                    String preuNStr = sc.nextLine();
                    if (!preuNStr.equals("")) {
                        preuN = Double.valueOf(preuNStr);
                    }
                    sc.nextLine();

                } catch (InputMismatchException e) {
                    System.out.println("Error, introdueix un nombre: " + e);
                    break;
                }

                //ESTOC
                System.out.println("Estoc: ");
                int estocN = -1;
                String estocNStr = sc.nextLine();
                if (!estocNStr.equals("")) {
                    estocN = Integer.parseInt(estocNStr);
                }

                //Creem nou objecte Producte i cridem mètode per actualitzar-lo a BD
                Producte pNou = new Producte();
                pNou.setNom(nomN);
                pNou.setEstoc(estocN);
                pNou.setPreu(preuN);

                try {
                    actualitzarProd(pNou, pAntic);
                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }

                break;
            case 4:
                System.out.println("Introdueix ID del producte que vols eliminar: ");
                idProd = sc.nextInt();
                try {
                    int elimCorr = pDao.eliminar(idProd);
                    if (elimCorr == 1) System.out.println("Producte eliminat correctament");
                    
                } catch (SQLException e) {
                    System.out.println("ERROR ON SQL QUERY: " + e);
                }
                
                
                break;

        }
    }
    
    
    

    public Producte creaProd(String nom, double preu, int estoc) {
        Producte p = new Producte();
        p.setNom(nom);
        p.setEstoc(estoc);
        p.setPreu(preu);
        try {
            pDao.inserir(p);
        } catch (SQLException e) {
            System.out.println("Error en consulta SQL (INSERT)");
            e.printStackTrace();
        }

        return p;
    }

    public Producte cercaProd(int id) {
        Producte p = new Producte();
        try {
            p = pDao.obtenirPerId(id);
        } catch (SQLException e) {
            System.out.println("Error en consulta SQL (SELECT)");
            e.printStackTrace();
        }
        return p;
    }

    public void actualitzarProd(Producte pNou, Producte pAntic) throws SQLException {

        Producte pFinal = new Producte();

        String nomFinal = "";
        if (!pNou.getNom().equals(nomFinal)) {
            nomFinal = pNou.getNom();// Si està buit, agafem el vell
        }
        double preuFinal = -1.0;
        if (pNou.getPreu() != preuFinal) {
            preuFinal = pNou.getPreu();

            int estocFinal = -1;
            if (pNou.getEstoc() != estocFinal) {
                estocFinal = pNou.getEstoc();
            }

            pFinal.setNom(nomFinal);
            pFinal.setPreu(preuFinal);
            pFinal.setEstoc(estocFinal);

            pDao.actualitzar(pFinal);
        }
    }
}
