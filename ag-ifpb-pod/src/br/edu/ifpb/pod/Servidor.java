/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Aluísio
 */
public class Servidor {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        System.setProperty("java.rmi.server.hostname","192.168.1.117");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("OlaMundo", new OlaMundoRemote("Aluísio: \n Contando: "));

    }

}
