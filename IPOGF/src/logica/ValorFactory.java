package logica;


import java.util.LinkedList;

import dicionarios.Dominio;
import bean.Fator;
import bean.Nivel;
import bean.NivelConteudo;

public class ValorFactory {

	private Dominio dominio;
	private Integer fator;
	private Integer indice;
	
	public ValorFactory(){
		dominio = Dominio.getInstance();
		fator = 1;
		indice = 0;
	}
	
	public void atualizarDominio(Integer valor) {
		
		LinkedList<Nivel> valores = new LinkedList<Nivel>();
		
		for(int i=1; i<=valor; i++){
			
			Nivel n = new Nivel(indice, new NivelConteudo(fator, i));
			valores.add(n);
			indice = indice + 1;
		}
		
		fator+=1;
		Fator p = new Fator();
		p.getListaNiveis().setNiveis(valores);
		dominio.addFator(p);		
	}
	
	public void addNull(){
		Nivel n = new Nivel(indice, new NivelConteudo(fator, null));
		Fator p = new Fator();
		p.getListaNiveis().addNiveis(n);
		dominio.addFator(p);			
	}
}
