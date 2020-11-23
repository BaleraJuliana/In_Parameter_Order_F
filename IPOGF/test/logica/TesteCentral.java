/*
package logica;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TesteCentral {
	
	private Central central;
	
	@Before
	public void init(){
		central = new Central();
		System.out.println("//------------------------------------------------------//");
	}
	 
	//@Test
	public void teste1(){	
		System.out.println("Caso de teste: [2, 2, 3]");
		System.out.println("Strenght: 2");
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(2);
		entrada.add(2);
		entrada.add(3);
		central.entradaDebug(2, entrada);
	}

	//@Test
	public void teste2(){	
		System.out.println("Caso de teste: [2, 2, 2, 3]");
		System.out.println("Strenght: 3");
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(2);
		entrada.add(2);
		entrada.add(2);
		entrada.add(3);
		central.entradaDebug(3, entrada);
	}
	
	//@Test
	public void teste3(){	
		System.out.println("Caso de teste: [3^13]");
		System.out.println("Strenght: 2");
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		central.entradaDebug(2, entrada);
	}	
	
	//@Test não passou
	public void teste4(){	
		System.out.println("Caso de teste: [3^13]");
		System.out.println("Strenght: 2");
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		central.entradaDebug(2, entrada);
	}
		
	//@Test
	public void testePerformance(){
		System.out.println("Caso de teste: [3^20]");
		System.out.println("Strenght: 2");
		List<Integer> entrada = new ArrayList<Integer>();
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		entrada.add(3);
		central.entradaDebug(2, entrada);
	}
	
	@Test
		public void teste5(){
			System.out.println("Caso de teste: [2, 2, 2, 2]");
			System.out.println("Strenght: 3");
			List<Integer> entrada = new ArrayList<Integer>();
			entrada.add(2);
			entrada.add(3);
			entrada.add(4);
			entrada.add(2);
			entrada.add(2);
			central.entradaDebug(3, entrada);
		}
}
*/
package logica;

import org.junit.Before;
import org.junit.Test;

public class TesteCentral {
	
	private Central central;
	
	@Before
	public void init(){
		central = new Central();
	}
	
	@Test
	public void testeEntrada(){	
		central.entradaPrincipal();
	}
}
