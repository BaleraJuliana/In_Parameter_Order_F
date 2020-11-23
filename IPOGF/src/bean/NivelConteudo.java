package bean;

public class NivelConteudo implements Cloneable{
	
	//atributos
	private Integer fator;
	private Integer valor;
	
	//construtor
	public NivelConteudo(Integer fator, Integer valor){
		this.fator = fator;
		this.valor = valor;
	}

	//metodos
	public Integer getFator() {
		return fator;
	}

	public Integer getValor() {
		return valor;
	}

	public void setFator(Integer fator) {
		this.fator = fator;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	public boolean compara(NivelConteudo n){
		if(!(n.getFator()==this.fator))return false;
		if(!(n.getValor()==this.valor))return false;
		return true;
	} 
	
	public NivelConteudo clone(){
		try{
			return (NivelConteudo) super.clone();	
		} catch (CloneNotSupportedException e){
			System.out.println("Não foi possível realizar o clone do objeto NívelConteúdo, interface Clonable não foi implementada");
			return this;
		}
	}
}
