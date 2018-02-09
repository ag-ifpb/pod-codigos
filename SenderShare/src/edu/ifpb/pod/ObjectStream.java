package edu.ifpb.pod;

import java.io.Serializable;

//@SuppressWarnings("serial")
public class ObjectStream implements Serializable{
	/**
   * 
   */
  private static final long serialVersionUID = 290068431794775782L;
  
  private String name;
	private boolean exit = false;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTrueForExit(){
	  exit = true;
	}
	
	public boolean isExit() {
      return exit;
    }
}