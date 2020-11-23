package bean;

import java.util.LinkedList;

public class Tc {
		
		private LinkedList<ElementoTc> elementos;
		private static Tc uniqueInstance;
		
		
		private Tc(){
			this.elementos = new LinkedList<ElementoTc>();
		}
		
		public static Tc getInstance(){
			if(uniqueInstance==null)
				return uniqueInstance = new Tc();
			return uniqueInstance;
		}

		public LinkedList<ElementoTc> getElementos() {
			return elementos;
		}

		public void setElementos(LinkedList<ElementoTc> elementos) {
			this.elementos = elementos;
		}

}
