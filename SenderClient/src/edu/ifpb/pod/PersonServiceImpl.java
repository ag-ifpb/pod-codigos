package edu.ifpb.pod;

//Cliente
public class PersonServiceImpl implements PersonService {
  private final PersonServiceClientStub stub = new PersonServiceClientStub();

  @Override
  public void save(Person p) {
    stub.sendBySocket(p);
  }

}
