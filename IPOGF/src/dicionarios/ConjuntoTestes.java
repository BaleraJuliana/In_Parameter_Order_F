package dicionarios;

import java.util.LinkedList;

import bean.Fator;
import bean.Nivel;
import bean.Teste;
import bean.Tupla;

public class ConjuntoTestes {
	
	//atributos
	private LinkedList<Teste> listaTeste;
 	private Cov cov;
 	private static ConjuntoTestes uniqueInstance;
 	
	//costrutor
	private ConjuntoTestes() {
		listaTeste = new LinkedList<Teste>();
		cov = Cov.getInstance();
	}

	//metodos
	public static ConjuntoTestes getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ConjuntoTestes();
		}
		return uniqueInstance;
	}
	
	public LinkedList<Teste> getListaTeste(){
		return listaTeste;
	}
	
	/*geração do pairwise teste inicial para apenas dois parâmetros*/
	

	public void geracaoTestesInicial(){ 
		
		int i = 1;
		for(Tupla t: cov.getTuplas().get(0).getConjunto_tuplas()){
			
			Teste teste = new Teste();
			teste.setIndice(i);
			teste.setNivelCoberto(t.getLn().clonar());
			teste.completarTeste();
			
			listaTeste.add(teste);
			
			i = i + 1;
		} 
		
	}
	
	public void geracaoTestesInicia(){
		int comandante = 0;
		ListaNiveis guia = new ListaNiveis();

		for(Fator f: Dominio.getInstance().getDominio()){
			for(Nivel n: f.getListaNiveis().getNivel()){
				comandante = n.getConteudo().getFator();
				if(comandante>=3){
					break;
				} else{
					guia.getNivel().add(n);
				}
			} 
		}
		
		for(Nivel ng: guia.getNivel()){
			for(Fator f: Dominio.getInstance().getDominio()){
				if(ng.getConteudo().getFator()==f.getListaNiveis().getNivel().get(0).getConteudo().getFator()){
					continue;
				}
				for(Nivel n: f.getListaNiveis().getNivel()){
					if(n.getConteudo().getFator()>=3){
						break;
					} 
					if(n.getConteudo().getFator()>ng.getConteudo().getFator()){
						Teste t = new Teste();
						t.getNivelCoberto().getNivel().add(ng);
						t.getNivelCoberto().getNivel().add(n);
						
						listaTeste.add(t);
					} 
				}
			}
		}
	}
	
	
public Teste adicionarValoresSemImportancia(Tupla tupla){
		
		Teste teste = new Teste(); 
			
		int i=1;
		for(Nivel n: tupla.getLn().getNivel()){
			if(i==n.getConteudo().getFator()){
				teste.nivelCoberto.addNiveis(n);
				i = i + 1;
				continue;
			} 
			if(i<n.getConteudo().getFator()){
				int k = i;
				for(int j=k; j<n.getConteudo().getFator(); j++){
					Nivel nivel = new Nivel(i, null);
					teste.nivelCoberto.addNiveis(nivel);
					i = i + 1;
				}
			}
			teste.nivelCoberto.addNiveis(n);
			i = i + 1;
		}
		while(i<=Dominio.getInstance().getDominio().size()){
			Nivel nivel = new Nivel(i, null);
			teste.nivelCoberto.addNiveis(nivel);
			i = i + 1;
		}
		return teste;				
	}
		
}
