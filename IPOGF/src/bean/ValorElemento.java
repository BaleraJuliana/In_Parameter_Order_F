package bean;

public class ValorElemento {
	
	private Integer status;
	
	public ValorElemento(){
		status = 0;
	}
	
	public Integer getStatus(){
		return status;
	}
	
	public void addStatus(){
		this.status = this.status + 1;
	}
}
