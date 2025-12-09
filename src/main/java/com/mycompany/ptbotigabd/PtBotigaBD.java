/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ptbotigabd;

import java.util.Scanner;
import model.Producte;
import dao.ProducteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

/**
 *
 * @author alumne
 */
public class PtBotigaBD {
    
    private Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        PtBotigaBD inst = new PtBotigaBD();
        inst.Menu();
    }
    
    public void Menu(){
        
        int opcio = 5;
        
            do {
            try{   
            System.out.println("\n---- BOTIGA ONLINE - Benvingut! ----"
                    + "\n ------------------------------");
            System.out.println("Escull què vols fer:\n "
                    + "1. Gestionar Productes\n"
                    + "2. Gestionar Clients\n"
                    + "3. Crear Comanda\n"
                    + "4. Llistar Comandes\n"
                    + "0. Sortir ");
            opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    menuProductes();
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                
            }
           } catch (InputMismatchException e){
            System.out.println("Introdueix un ");
        } 
        } while (opcio != 0);
        
        
    }
    
    //GESTIONAR PRODUCTES
    public void menuProductes(){
        ProducteDAO pDao = new ProducteDAO();
        System.out.println("---- GESTIONA PRODUCTES ----\n"
                + "1. Crea producte\n"
                + "2. Cerca un producte\n"
                + "3. Modifica un producte\n"
                + "4. Elimina un producte");
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
                creaProducte(nom, preu, estoc, pDao);
                break;
                
            case 2:
                System.out.println("Introdueix ID del producte a cercar: ");
                idProd = sc.nextInt();
                Producte pProva = new Producte();
                pProva.getNom();
                System.out.println(pProva.toString());
                
                Producte p = cercaProducte(idProd, pDao);
                if (!p.getNom().equals(null)) {
                    System.out.println(p.toString());
                    
                } else System.out.println("No hi ha cap producte amb aquest ID");;
                
                break;
            case 3:
                System.out.println("Introdueix ID del producte que vols editar: ");
                idProd = sc.nextInt();
                
                break;
            case 4:
                
                break;
            
        }
    }
    
    public Producte creaProducte(String nom, double preu, int estoc, ProducteDAO pDao){
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
    
    public Producte cercaProducte(int id, ProducteDAO pDao){
        Producte p = new Producte();
        try {
            p = pDao.obtenirPerId(id);
        } catch (SQLException e) {
            System.out.println("Error en consulta SQL (SELECT)");
            e.printStackTrace();
        }
        return p;
    }
}
