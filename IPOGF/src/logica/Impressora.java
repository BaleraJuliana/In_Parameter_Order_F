package logica;
import dicionarios.ConjuntoTestes;
import dicionarios.Cov;
import dicionarios.Dominio;
import dicionarios.Lambda;
import bean.ElementoTc;
import bean.Fator;
import bean.Nivel;
import bean.Tc;
import bean.Tupla;
import bean.Teste;
import bean.ValorElemento;

public class Impressora {
	
	public Impressora(){}
	
	public void imprimirDominio(Dominio dominio){
		
		
		System.out.print("Domínio de cobertura: {");
		System.out.println("");
		for(Fator f: dominio.getDominio()){
			System.out.print("[");
			for(Nivel n: f.getListaNiveis().getNivel()){
				this.imprimirNivelComIndice(n);
			}
			System.out.print("]");
			System.out.println("");
		}
		System.out.print("}");
		System.out.println("");
	}

	public void imprimirNivelComIndice(Nivel n){
		System.out.print("["+n.getIndice()+","+"("+n.getConteudo().getFator()+","+n.getConteudo().getValor()+")"+"]");
	}
	
	public void imprimirNivelSemIndice(Nivel n){
		System.out.print("("+n.getConteudo().getFator()+","+n.getConteudo().getValor()+")");
	} 

	public void imprimirCov(Cov cov){  
		
		int cobertos = 0;
		
		System.out.println("+--------------------------+");
		System.out.println("Matriz Cov");
		int cont = 0;
		for(Lambda l: cov.getTuplas()){
			System.out.println("Combinação: "+l.getGuia());
			cont = cont + l.getConjunto_tuplas().size();
			for(Tupla t: l.getConjunto_tuplas()){ 
				
				if(t.getStatus()==true){
					cobertos += 1;
				}
				this.imprimirTupla(t);
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Tamanho: "+cont+" tuplas");
		System.out.println("Cobertos: "+cobertos+" tuplas");
		System.out.println("Não Cobertos: "+(cont - cobertos)+" tuplas");
		System.out.println("+--------------------------+");
	}	 

	public void imprimirTc(){  
		
		System.out.println("+--------------------------+");
		System.out.println("Matriz Tc");
		int i = 0;
		for(ElementoTc e: Tc.getInstance().getElementos()){
			int j = 0;
			for(ValorElemento status: e.getIndice()){
				System.out.println(i+"/"+j+"\t"+status.getStatus());
				j = j + 1;
			}
			i = i + 1;
		}
		System.out.println("");
		System.out.println("Ajuda: linha do teste/número do fator");
		System.out.println("+--------------------------+");
	}
	
	public void imprimirTestes(ConjuntoTestes testes){
		System.out.println("Conjunto de testes completo: ");
		int i=1;
		for(Teste t: testes.getListaTeste()){
			System.out.printf("%-6d", i);
			System.out.print(" "+"|"+"\t ");
			for(Nivel n: t.getNivelCoberto().getNivel()){
				if(n.getConteudo().getValor()==null){
					System.out.print("_");
					System.out.print("   ");
					continue;
				}
				System.out.printf("%-3d", n.getConteudo().getValor()); 
				System.out.print(" ");
			}
			i = i + 1;
			System.out.println("");
		}
	}
	

	public void imprimirTupla(Tupla t){
		for(Nivel n: t.getLn().getNivel()){
			this.imprimirNivelSemIndice(n);
		}
		System.out.println("\t"+t.getStatus());
	}
	
	public void imprimirTeste(Teste t){
		for(Nivel n: t.getNivelCoberto().getNivel()){
			this.imprimirNivelSemIndice(n);
		}
		System.out.println("");
	}
}
