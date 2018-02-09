package edu.ifpb.pod;

//Servidor
public class Main {

  public static void main(String[] args) {
    PersonServiceServerStub stub = new PersonServiceServerStub();
    stub.receive();
  }
}
