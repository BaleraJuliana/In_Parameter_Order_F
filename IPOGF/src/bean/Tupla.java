package bean;
import java.util.LinkedList;

import dicionarios.ListaNiveis;

public class Tupla implements Cloneable{
	
	private ListaNiveis lista_niveis;
	private boolean status;
	
	public Tupla(){
		lista_niveis = new ListaNiveis();
		status = false;
	}
	
	public ListaNiveis getLn() {
		return lista_niveis;
	}
	public void setLn(ListaNiveis ln) {
		this.lista_niveis = ln;
	}
	public boolean getStatus() {
		return status; 
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean compara(Tupla t){
		if(!(t.getLn().compara(lista_niveis))) return false;
		return true;
	}
	
	public LinkedList<Integer> getLambda(){
		LinkedList<Integer> aux = new LinkedList<Integer>();
		for(Nivel n: lista_niveis.getNivel()){
			aux.add(n.getConteudo().getFator());
		}
		return aux;
	}

	  
	public Tupla clone(){ 
		try{
			return (Tupla) super.clone();	
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto Tupla, interface Clonable não foi implementada");
			return this;
		}
	}
	public Tupla clonar(){
		Tupla clone = new Tupla();
		for(Nivel n: this.getLn().getNivel()){
			Nivel clone_nivel = new Nivel(n.getIndice(), n.getConteudo().clone());
			clone.getLn().getNivel().add(clone_nivel);
		}
		return clone;
	}
}
