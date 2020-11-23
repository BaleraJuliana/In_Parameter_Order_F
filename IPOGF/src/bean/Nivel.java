package bean;


public class Nivel{
	
	//atributos
	private Integer indice;
	private NivelConteudo conteudo; 
	 
	//construtor
	public Nivel(Integer indice, NivelConteudo conteudo){
		this.indice = indice;
		this.conteudo = conteudo;
	}

	//metodos	
	 
	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public NivelConteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(NivelConteudo conteudo) {
		this.conteudo = conteudo;
	}
	
	public boolean compara(Nivel n){ 
		if(!(n.getIndice()==this.indice)) return false;
		return true;
	}
	
	public Nivel clonar(){
		Nivel clone = new Nivel(indice, conteudo.clone());
		return clone;
	}
}
 