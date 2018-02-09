package edu.ifpb.pod;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PersonServiceImpl implements PersonService {

  @Override
  public void save(Person p) {
    try {
      //criar o stream do arquivo person.dat
      FileOutputStream out = new FileOutputStream("person.dat");
      //criar o stream do objeto Person (p)
      ObjectOutputStream outputStream = new ObjectOutputStream(out);
      //escrever o objeto no stream
      outputStream.writeObject(p);
      //fechar o stream do objeto
      outputStream.close();
      //fechar o stream do arquivo
      out.close();
    } 
    catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    catch (IOException e) {
      e.printStackTrace();
    }

  }

}
