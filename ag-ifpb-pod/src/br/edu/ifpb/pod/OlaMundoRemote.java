/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Aluísio
 */
public class OlaMundoRemote extends UnicastRemoteObject implements OlaMundoInterface {

    private String mensagem;
    private int i;

    public OlaMundoRemote(String msg) throws RemoteException {
        mensagem = msg;
        i = 0;
    }

    public String falar() {
        return mensagem;
    }

    public int contar() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        return ++i;
    }

}
