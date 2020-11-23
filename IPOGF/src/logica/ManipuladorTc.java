package logica;

import java.util.LinkedList;

import bean.ElementoTc;
import bean.Fator;
import bean.Nivel;
import bean.Tc;
import bean.Teste;
import bean.ValorElemento;
import dicionarios.ConjuntoTestes;
import dicionarios.Dominio;

public class ManipuladorTc {

	private static ManipuladorTc uniqueInstance;
	private ConjuntoTestes conjunto_testes;
	private Dominio dominio;
	private Tc tc;
	
	
	private ManipuladorTc(){
		
		dominio = Dominio.getInstance();
		tc = Tc.getInstance();
		conjunto_testes = ConjuntoTestes.getInstance();
	}
	
	public static ManipuladorTc getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new ManipuladorTc();
		}
		return uniqueInstance; 
	} 
	
	public void geraçãoTc(){
		
		
		for(Teste t: conjunto_testes.getListaTeste()){
			ElementoTc novo = new ElementoTc();
			LinkedList<ValorElemento> indices = new LinkedList<ValorElemento>();
			for(Fator f: dominio.getDominio()){
				for(Nivel n: f.getListaNiveis().getNivel()){
					ValorElemento novo_elemento = new ValorElemento();
					indices.add(novo_elemento);
				}
			}
			novo.setIndice(indices);
			tc.getElementos().add(novo);
		}
	}
		
}

