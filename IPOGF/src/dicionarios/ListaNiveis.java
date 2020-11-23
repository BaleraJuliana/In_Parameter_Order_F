package dicionarios;


import java.util.LinkedList;

import bean.Nivel;

public class ListaNiveis implements Cloneable{
	
	//atributos
	private LinkedList<Nivel> conjunto_niveis;
	
	//construtor
	public ListaNiveis(){
		conjunto_niveis = new LinkedList<Nivel>();
	}

	//metodos
	public LinkedList<Nivel> getNivel() {
		return conjunto_niveis;
	}

	public void setNiveis(LinkedList<Nivel> n) {
		this.conjunto_niveis = n;
	}	

	public void addNiveis(Nivel n) {
		this.conjunto_niveis.add(n);
	}	
	
	public boolean compara(ListaNiveis ln){
		 
		int i = 0;
		for(Nivel n: ln.getNivel()){
			if(!(n.compara(conjunto_niveis.get(i)))) return false;
			i = i + 1;
		}
		return true;
	}
	 
	public ListaNiveis clone(){
		try{
			return (ListaNiveis) super.clone();	
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto ListaNiveis, interface Clonable não foi implementada");
			return this;
		} 
	}	 

	public ListaNiveis clonar(){
		ListaNiveis clone = new ListaNiveis();
		for(Nivel n: this.getNivel()){
			Nivel clone_nivel = new Nivel(n.getIndice(), n.getConteudo().clone());
			clone.getNivel().add(clone_nivel);
		}
		return clone;
	}
}
