package edu.ifpb.pod;

//Cliente
public class Main {

  public static void main(String[] args) {
    //criar um Person
    Person p = new Person();
    p.setId(10);
    p.setName("Ari Garcia");
    //
    PersonService service = new PersonServiceImpl();
    service.save(p);
  }
  
}
