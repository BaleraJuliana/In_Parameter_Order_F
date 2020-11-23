package logica;

import java.util.LinkedList;

import dicionarios.ConjuntoTestes;
import dicionarios.Cov;
import dicionarios.Dominio;
import dicionarios.Lambda;
import bean.Nivel;
import bean.NivelConteudo;
import bean.Teste;
import bean.Tupla;

public class CrescimentoVertical {

	private Cov cov;
	private ConjuntoTestes conjunto_testes;
	private ManipuladorCov mc;
	private static CrescimentoVertical uniqueInstance;
	
	private CrescimentoVertical(){
		
		cov = Cov.getInstance();
		conjunto_testes = ConjuntoTestes.getInstance();
		mc = ManipuladorCov.getInstance();
		
	} 
	
	public static CrescimentoVertical getInstance(){ 
		if(uniqueInstance==null) 
			uniqueInstance = new CrescimentoVertical();
		return uniqueInstance;
	}
	
	  
	public void init(){	 
	
		LinkedList<Tupla> tuplas_cobertas = new LinkedList<Tupla>();
		LinkedList<Teste> listaTeste = new LinkedList<Teste>(); 
 
		for(Lambda lambda: cov.getTuplas()){  
			
			for(Tupla tupla: lambda.getConjunto_tuplas()){
				
				if(tupla.getStatus()==true){ 
					continue; 
				} 
				
				if(listaTeste.isEmpty()){
					listaTeste.add(this.adicionarValoresSemImportancia(tupla));
					tuplas_cobertas.add(tupla.clone());
					continue; 
				}
				
				if(!verificarTesteComValorSemImportanciaCobrePar1(listaTeste, tupla, tuplas_cobertas)){
					listaTeste.add(this.adicionarValoresSemImportancia(tupla));
					tuplas_cobertas.add(tupla.clone());
				 	continue;	 
				
				}
			} 
		}
		
		for(Teste t: listaTeste){
			conjunto_testes.getListaTeste().add(t);
		}
			
	} 

	public boolean verificarTesteComValorSemImportanciaCobrePar1(LinkedList<Teste> listaTeste, Tupla tupla, LinkedList<Tupla> tuplasCobertas){
					
		for(Teste t: listaTeste){
			
			int controle = 0;
			
			for(Nivel n: tupla.getLn().getNivel()){
			 	
				if((t.getNivelCoberto().getNivel().get(n.getConteudo().getFator()-1).getConteudo().getValor()==null) || 
					(t.getNivelCoberto().getNivel().get(n.getConteudo().getFator()-1).getConteudo().getValor()==n.getConteudo().getValor())){
					continue;
				}   
					controle = 1;
					break;
			} 
			
			if(controle==1){ 
				continue; 
			} else{
				
				for(Nivel n: tupla.getLn().getNivel()){
					t.replace(n.getConteudo().getFator(), n);
				} 
			}  
			
			tuplasCobertas.add(tupla.clone());
			return true; 
	
		}   
		return false;   
	} 
	
	
	 
	// esse teste cria um novo teste para cobrir um par específico
	
	public Teste adicionarValoresSemImportancia(Tupla tupla){
		
		Teste teste = new Teste();  
			 
		int i=1;
		for(Nivel n: tupla.getLn().getNivel()){ 
			if(i==n.getConteudo().getFator()){
				teste.nivelCoberto.addNiveis(n.clonar());
				i = i + 1;
				continue;
			}  
			if(i<n.getConteudo().getFator()){
				int k = i;
				for(int j=k; j<n.getConteudo().getFator(); j++){
					Nivel nivel = new Nivel(Dominio.getInstance().getDominio().getLast().getListaNiveis().getNivel().getLast().getIndice()+1, new NivelConteudo(i, null));
					teste.nivelCoberto.addNiveis(nivel);
					i = i + 1; 
				}
			}
			
			teste.nivelCoberto.addNiveis(n);
			i = i + 1;
		}
		while(i<=Dominio.getInstance().getDominio().size()-1){
			Nivel nivel = new Nivel(Dominio.getInstance().getDominio().getLast().getListaNiveis().getNivel().getLast().getIndice()+1, new NivelConteudo(i, null));
			teste.nivelCoberto.addNiveis(nivel); 
			i = i + 1;
		}
		
		return teste;				
	}
	
	public boolean verificarExisteValorSemImportancia(Teste t){
		for(Nivel n: t.getNivelCoberto().getNivel()){
			if(n.getConteudo().getFator()==null) return true;
		}
		return false;
	}
}