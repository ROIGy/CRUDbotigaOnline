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
import java.util.List;

/**
 *
 * @author alumne
 */
public class PtBotigaBD {
    private Scanner sc = new Scanner(System.in);
    private menuProductesClass mpc = new menuProductesClass();
    private menuClientsClass mcc = new menuClientsClass();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PtBotigaBD inst = new PtBotigaBD();
        inst.Menu();
    }

    public void Menu() {

        int opcio = 5;

        do {
            try {
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
                        mpc.menuProductes();
                        break;
                    case 2:
                        //mcc.menuClients();
                        break;
                    case 3:
                        //menuCrearComanda();
                        break;
                    case 4:
                        //menuLlistarComandes();
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Introdueix un ");
            }
        } while (opcio != 0);

    }

    
}
