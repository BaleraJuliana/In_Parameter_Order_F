package bean;
import dicionarios.ListaNiveis;

public class Fator{
	
	private ListaNiveis lista_niveis;
	
	public Fator(){
		lista_niveis = new ListaNiveis();
	}

	public ListaNiveis getListaNiveis() {
		return lista_niveis;
	}

	public void setListaNiveis(ListaNiveis ln) {
		this.lista_niveis = ln;
	}
	
	public boolean compara(Fator f){
		if(!f.getListaNiveis().compara(lista_niveis)) return false;
		return true;
	}
	
	public Fator clone(){
		try{
			return (Fator) super.clone();	
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto Fator, interface Clonable não foi implementada");
			return this;
		}
	}	
}

 

