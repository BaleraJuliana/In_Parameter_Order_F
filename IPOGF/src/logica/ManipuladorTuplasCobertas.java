package logica;

import java.util.LinkedList;

import dicionarios.Cov;
import dicionarios.Lambda;
import bean.Nivel;
import bean.Teste;
import bean.Tupla;

public class ManipuladorTuplasCobertas{	
	
	private LinkedList<Teste> listaTestes;
	private LinkedList<Tupla> conjunto;
	private static ManipuladorTuplasCobertas uniqueInstance;
	
	private ManipuladorTuplasCobertas(){
		listaTestes = new LinkedList<Teste>();
		conjunto = new LinkedList<Tupla>();
	}
	
	public static ManipuladorTuplasCobertas getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ManipuladorTuplasCobertas();
		}  
		return uniqueInstance;  
	} 
	
	public LinkedList<Tupla> getConjunto() {
		return conjunto;
	}
	
	public void setConjunto(LinkedList<Tupla> t) {
		this.conjunto = t;
	}
	 
	public void setListaTeste(LinkedList<Teste> listaTeste){ 
		this.listaTestes = listaTeste;
	}
 
	public void acharTuplasCobertas(Integer strength){
		
		LinkedList<LinkedList<Integer>> guia = new LinkedList<LinkedList<Integer>>();
		
		for(Lambda l: Cov.getInstance().getTuplas()){
			
			guia.add((LinkedList<Integer>) l.getGuia().clone());
		}
		
		conjunto.clear();
		
		Impressora i = new Impressora(); 
		 
		for(Teste t: listaTestes){
			
			this.armazenarTuplas(t, guia, strength);
		} 
	} 
	  
	public void armazenarTuplas(Teste t, LinkedList<LinkedList<Integer>> guia, Integer strenght){

		Impressora i = new Impressora(); 
				
		for(LinkedList<Integer> combinação: guia){
			
			Tupla tu = new Tupla();
			 
			for(Integer coluna: combinação){
				
				if(t.getNivelCoberto().getNivel().get(coluna-1).getConteudo().getValor()!=null){ 
					tu.getLn().addNiveis(t.getNivelCoberto().getNivel().get(coluna-1));
				} 
			} 
			
			
			if(tu.getLn().getNivel().size()==strenght){
				tu.setStatus(true);
				this.conjunto.add(tu);
			}
		}
	} 
}

