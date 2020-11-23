package logica;

import java.util.Collection;
import java.util.LinkedList;

import dicionarios.Cov;
import dicionarios.Dominio;
import dicionarios.Lambda;
import bean.Nivel;
import bean.Tupla;

public class ManipuladorCov{
		
		private static ManipuladorCov uniqueInstance;
		private Dominio dominio;
		private Cov cov;
		private LinkedList<LinkedList<Integer>> guia;
		private LinkedList<Tupla> fonte;
		private Impressora imp; 
		
		private ManipuladorCov(){
			
			dominio = Dominio.getInstance();
			cov = Cov.getInstance();
			guia = new LinkedList<LinkedList<Integer>>();
			fonte = new LinkedList<Tupla>();
			imp = new Impressora();
		} 
		
		public static ManipuladorCov getInstance(){
			if(uniqueInstance == null){
				uniqueInstance = new ManipuladorCov();
			}
			return uniqueInstance; 
		} 
		
		@SuppressWarnings("unchecked")
		public void geracaoCov(Integer t){ 
		 	
			this.enumerador((dominio.getDominio().size()-1), t);
			   
			for(LinkedList<Integer> combinação: guia){
				
				int i = 0;
				
				for(Integer coluna: combinação){
				
					if(i==0){
						this.geracaoInicial(coluna);
					} else {
						this.geracaoInicial2(coluna); 
					}
					i = i + 1;  
				}
				
				Lambda nova = new Lambda();
				nova.setGuia(combinação);
				nova.getConjunto_tuplas().addAll((Collection<? extends Tupla>) fonte.clone());
				fonte.clear();	
				cov.getTuplas().add(nova);
			} 
		}  
		   
		  
		public void geracaoInicial(Integer coluna){
		
			for(Nivel n1: dominio.getDominio().get(coluna-1).getListaNiveis().getNivel()){
				
				Tupla aux = new Tupla();
				aux.getLn().addNiveis(n1.clonar()); 
				fonte.add(aux.clone());
			} 
		}
		
		public void geracaoInicial2(Integer coluna){	
			
			int tamanho_nessa_vez = (fonte.size())*(dominio.getDominio().get(coluna-1).getListaNiveis().getNivel().size());
					
			LinkedList<Tupla> aux1 = new LinkedList<Tupla>();
			
			for(Tupla ln: fonte){
					
				for(int j=0; j<dominio.getDominio().get(coluna-1).getListaNiveis().getNivel().size(); j++){	
					aux1.add(ln.clonar()); 
				}
			}
			 
			fonte.clear(); 
			fonte.addAll(aux1);
			aux1.clear();
			
			int contador = 0;
			
			while(true){
				
				for(Nivel n1: dominio.getDominio().get(coluna-1).getListaNiveis().getNivel()){
					fonte.get(contador).getLn().getNivel().add(n1);
					contador = contador + 1;
					if(contador==tamanho_nessa_vez){
						break;
					}
				}
				if(contador==tamanho_nessa_vez){
					break;
				}
			}  
		} 
	
		public void enumerador(int n, Integer t){
			   
			   LinkedList<Integer> s = new LinkedList<Integer>();
			   int k = 0;
			   s.add(0);
			    
			   while(true) {
				     
				   if(s.get(k) < n) {
					   s.add(s.get(k)+1);
					   k += 1;
					} else {
					  s.set(k-1, s.get(k-1)+1); 
					  s.removeLast();
					  k -= 1; 
				   }
				   if(k == 0) break;
				   if((s.size())==t+1){
					   @SuppressWarnings("unchecked")
					LinkedList<Integer> aux = (LinkedList<Integer>) s.clone();
					   aux.removeFirst();
					   guia.add(aux);
					}
			   }  
		   } 

		public void removerTupla(Tupla t){
			for(Lambda l: cov.getTuplas()){
				for(Tupla tu: l.getConjunto_tuplas()){
					if(tu.compara(t)){
						tu.setStatus(true);
					}
				}
			}
		}  
		
		public void removerTodasTuplas(LinkedList<Tupla> lt){ 
			
			for(Tupla t: lt){
				for(Lambda l: cov.getTuplas()){
					for(Tupla tu: l.getConjunto_tuplas()){
						if(t.compara(tu)){
							tu.setStatus(true);
						} 
					}
				}
			} 
		}
		
		public boolean verificarCoberturaTotal(){
			for(Lambda l: cov.getTuplas()){
				for(Tupla tu: l.getConjunto_tuplas()){
					if(tu.getStatus()==false){
						return false;
					}
				}
			}
			return true;
		}
} 
