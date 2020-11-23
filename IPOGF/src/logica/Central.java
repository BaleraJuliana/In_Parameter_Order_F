package logica;

import java.util.List;
import java.util.Scanner;

import bean.Teste;
import dicionarios.ConjuntoTestes;
import dicionarios.Cov;
import dicionarios.Dominio;

public class Central {
	
	
	private ManipuladorCov mcpc;
	private Cov cov;
	private ConjuntoTestes testes;
	private ManipuladorTuplasCobertas mtc;
	private ManipuladorTc manTc;
	private CrescimentoHorizontal ch;
	private Dominio dominio;
	private CrescimentoVertical cv;
	private Impressora impressora;

	private ValorFactory factory;
	private Scanner leitor;

	

	public Central(){ 
		
		mcpc = ManipuladorCov.getInstance();
		cov = Cov.getInstance();
		testes = ConjuntoTestes.getInstance();
		mtc = ManipuladorTuplasCobertas.getInstance();
		manTc = ManipuladorTc.getInstance();
		ch = CrescimentoHorizontal.getInstance();
		dominio = Dominio.getInstance();
		cv = CrescimentoVertical.getInstance();
		impressora = new Impressora();
		
		leitor = new Scanner(System.in);
		factory = new ValorFactory();
	
	} 
	
	public void entradaPrincipal(){
		
	
		System.out.println("IPOGF");
		
		System.out.println("Digite o valor do Strenght: ");
		
		int t = leitor.nextInt();
			
		System.out.println("Digite em cada linha o número de níveis de cada fator: ");
	
		Integer valor; 
		while(true){
			valor = leitor.nextInt();  
			if(valor!=0){ 
				factory.atualizarDominio(valor);
			}else{
				break;  
			} 
		}
		
		dominio.setStrenght(t);
		
		long tempo_inicial = System.currentTimeMillis();
		 
		factory.addNull();
		 
		mcpc.geracaoCov(t);
		testes.geracaoTestesInicial(); 
		  
		for(Teste te: testes.getListaTeste()){ 
			te.completarTeste(); 
		}
		
		manTc.geraçãoTc();
		
		for(int i=t; i<(dominio.getDominio().size()-1);i++){
			ch.init(i);
		} 
			
		mtc.setListaTeste(testes.getListaTeste());
		mtc.acharTuplasCobertas(t);  
		mcpc.removerTodasTuplas(mtc.getConjunto()); 
		
		cv.init(); 
		
		mtc.acharTuplasCobertas(t);  
		mcpc.removerTodasTuplas(mtc.getConjunto()); 
		
		long tempo_final = System.currentTimeMillis();
		
		impressora.imprimirTestes(testes);
		System.out.println("Tempo de execução do algorimto: "+ ((tempo_final - tempo_inicial))+" milisegundos");
		
	} 
}


