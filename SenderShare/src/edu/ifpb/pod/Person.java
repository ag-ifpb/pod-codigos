package edu.ifpb.pod;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Person implements Serializable {
  private int id;
  private String name;
  
  public Person() {
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
}
