package dicionarios;


import java.util.LinkedList;

import bean.Fator;

public class Dominio {
	
	//atributos
	private LinkedList<Fator> dominio;
	private static Dominio uniqueInstance;
	private int strenght; 
 	
	//costrutor
	private Dominio(){
		dominio = new LinkedList<Fator>();
		strenght = 1;
	}

	//metodos
	public static Dominio getInstance(){
		if(uniqueInstance==null){
			uniqueInstance = new Dominio();
		} 
		return uniqueInstance;
	}
	
	public LinkedList<Fator> getDominio() {
		return dominio;
	}

	public void addFator(Fator p) {
		this.dominio.add(p);
	}

	public void setStrenght(int s){
		this.strenght = s;
	}

	public int getStrenght() {
		return strenght;
	}
}
