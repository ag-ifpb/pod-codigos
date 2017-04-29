/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Aluísio
 */
public class ClienteOlaMundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("192.168.1.109",1099);
            OlaMundoInterface hello = (OlaMundoInterface) registry.lookup("OlaMundo");
            System.out.println(hello.falar());

	for (int j = 0; j < 1000; j++) {
                System.out.println("i = " + hello.contar());
            }
        } catch (Exception e) {
            System.out.println("ClienteOlaMundo falou: " + e);
        }

    }

}
