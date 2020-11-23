package dicionarios;

import java.util.LinkedList;

import bean.Tupla;

public class Cov{
	
	private LinkedList<Lambda> conjunto_tuplas;
	private static Cov uniqueInstance;
	
	private Cov() {
		conjunto_tuplas = new LinkedList<Lambda>();
	}
	public static Cov getInstance(){
		if(uniqueInstance==null)
			uniqueInstance = new Cov();
		return uniqueInstance;
	}
	
	public LinkedList<Lambda> getTuplas() {
		return conjunto_tuplas;
	} 

	public void setTuplas(LinkedList<Lambda> tuplas) {
		this.conjunto_tuplas = tuplas;
	}	
	
	public boolean estaDentro(Tupla t){
		for(Lambda lambda: conjunto_tuplas)
			for(Tupla tu: lambda.getConjunto_tuplas())
				if(tu.compara(t))
					return true;
		return false;
	}
}
 