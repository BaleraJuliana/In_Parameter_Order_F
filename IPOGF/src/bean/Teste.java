package bean;

import java.util.LinkedList;

import dicionarios.Dominio;
import dicionarios.ListaNiveis;


public class Teste{
	
	public ListaNiveis nivelCoberto;
	public Integer indice;
	 
	public Teste(){
		nivelCoberto = new ListaNiveis();
		indice = null;
	}
	
	public ListaNiveis getNivelCoberto() {
		return nivelCoberto;
	}
	
	public void setNivelCoberto(ListaNiveis nivelCoberto){
		this.nivelCoberto = nivelCoberto;
	}
	
	public Integer getIndice() {
		return indice;
	}
	
	public void setIndice(Integer indice){
		this.indice = indice;
	} 
	
	
	
	@SuppressWarnings("unchecked") 
	public void replace(int posicao, Nivel nivel){
		ListaNiveis aux = new ListaNiveis();
		int i=1;
		for(Nivel n: nivelCoberto.getNivel()){
			if(i==posicao){
				aux.addNiveis(nivel);
				i = i + 1;
				continue; 
			}
			aux.addNiveis(n); 
			i = i + 1;
		}
		nivelCoberto.setNiveis((LinkedList<Nivel>)aux.getNivel().clone());
	}	

	public Teste clonar(){
		Teste clone = new Teste();
		for(Nivel n: this.getNivelCoberto().getNivel()){
			Nivel clone_nivel = new Nivel(n.getIndice(), n.getConteudo().clone());
			clone.getNivelCoberto().getNivel().add(clone_nivel);
		}
		clone.setIndice(this.indice);
		return clone;
	}

	public void completarTeste() {
		
		while(nivelCoberto.getNivel().size()!=Dominio.getInstance().getDominio().size()-1){ 
			nivelCoberto.addNiveis(Dominio.getInstance().getDominio().getLast().getListaNiveis().getNivel().get(0).clonar());
		}
	}
}
 