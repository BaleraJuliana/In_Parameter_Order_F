package logica;

import java.util.LinkedList;
import java.util.Scanner;

import bean.Nivel;
import bean.Tc;
import dicionarios.ConjuntoTestes;
import dicionarios.Cov;
import dicionarios.Dominio;
import dicionarios.Lambda;

public class CrescimentoHorizontal {
	
	private Cov cov;
	private static CrescimentoHorizontal uniqueInstance;
	private ConjuntoTestes conjunto_testes;
	private Dominio dominio;
	private Tc tc;
	
	private CrescimentoHorizontal(){
		cov = Cov.getInstance();
		conjunto_testes = ConjuntoTestes.getInstance();
		dominio = Dominio.getInstance();
		tc = Tc.getInstance();
	}
	
	public static CrescimentoHorizontal getInstance(){
		if(uniqueInstance==null) 
			return uniqueInstance = new CrescimentoHorizontal();
		return uniqueInstance;
	}
	 
	public void init(int i){
	
			Integer tamanho_cov=0;  
			Impressora im = new Impressora();
			 
			for(Lambda l: cov.getTuplas()){
				tamanho_cov = tamanho_cov + l.getConjunto_tuplas().size();
			} 
			 
		 	Integer numero_testes = conjunto_testes.getListaTeste().size();
		
			Nivel nivel_escolhido = new  Nivel(null, null);
			
			for(int j=0; j<numero_testes; j++){  
				 
				int valor = 0;
				int valorMaximo=0;
				
				for(Nivel n: dominio.getDominio().get(i).getListaNiveis().getNivel()){
					
					valorMaximo = tamanho_cov - tc.getElementos().get(j).getIndice().get(n.getIndice()).getStatus(); 	
					
					if(valorMaximo>valor){
						valor = valorMaximo;   
						nivel_escolhido = n.clonar();
					}
					
				}
				
				conjunto_testes.getListaTeste().get(j).replace(nivel_escolhido.getConteudo().getFator(),nivel_escolhido);
			
				for(int k=j+1; k<numero_testes; k++){			
					 
					LinkedList<Integer> aux = new LinkedList<Integer>();
					aux = this.identificaColunasIguais(j, k, i); 
					
					if(aux.size()>=dominio.getStrenght()){
						tc.getElementos().get(k).getIndice().get(nivel_escolhido.getIndice()).addStatus();	
					}
				}
			} 
	} 
	
	public LinkedList<Integer> identificaColunasIguais(int m, int n, int i){
		
		LinkedList<Integer> colunasIguais = new LinkedList<Integer>();
		
		int a = 0;
		
		while(a!=i+1){
			
			if((conjunto_testes.getListaTeste().get(m).getNivelCoberto().getNivel().get(a).getIndice()==
					conjunto_testes.getListaTeste().get(n).getNivelCoberto().getNivel().get(a).getIndice()) ||	
					(conjunto_testes.getListaTeste().get(n).getNivelCoberto().getNivel().get(a).getConteudo().getValor()==null)){
				colunasIguais.add(a);
			}  
			a = a + 1;  
		}
		return colunasIguais;
	}
}
