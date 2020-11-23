package dicionarios;

import java.util.LinkedList;

import bean.Tupla;

public class Lambda implements Cloneable{
	
	public LinkedList<Integer> guia;
	public LinkedList<Tupla> conjunto_tuplas;
	
	
	public Lambda(){
		guia = new LinkedList<Integer>();
		conjunto_tuplas = new LinkedList<Tupla>();
	}
	
	public LinkedList<Integer> getGuia() {
		return guia;
	}
	public void setGuia(LinkedList<Integer> guia) {
		this.guia = guia;
	}
	public LinkedList<Tupla> getConjunto_tuplas() {
		return conjunto_tuplas;
	}
	public void setConjunto_tuplas(LinkedList<Tupla> conjunto_tuplas) {
		this.conjunto_tuplas = conjunto_tuplas;
	}
	
	public boolean compara(Lambda l){
		if(!(l.getGuia()==guia)) return false;
		if(!(l.getConjunto_tuplas()==conjunto_tuplas)) return false;
		return true;
	}
	
	public Lambda clone(){
		try{
			return (Lambda) super.clone();
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto Lambda, interface Clonable não foi implementada");
			return this;
		}
	}
}
