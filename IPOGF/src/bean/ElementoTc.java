package bean;

import java.util.LinkedList;

public class ElementoTc implements Cloneable{
	
	private LinkedList<ValorElemento> indice;
	
	public ElementoTc(){
		this.indice = new LinkedList<ValorElemento>();
	}
	
	public LinkedList<ValorElemento> getIndice() {
		return indice;
	}
	public void setIndice(LinkedList<ValorElemento> indice) {
		this.indice = indice;
	}
	
	public boolean compara(ElementoTc n){
		if(!(n.getIndice()==this.indice))return false;
		return true;
	}
	
	public ElementoTc clone(){
		try{
			return (ElementoTc) super.clone();	
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto ElementoTc, interface Clonable não foi implementada");
			return this;
		}
	}
	
}
