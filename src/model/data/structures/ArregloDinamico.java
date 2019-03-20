package model.data.structures;



/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de clases gen�ricas.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T>> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos =  (T []) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darTamano() {
		// TODO implementar
		return tamanoAct;
	}

	public T darElemento(int i) {
		
		return elementos[i];
	}

	public T buscar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T rta = null;

		for(int i=0; i<tamanoAct; i++)
		{
			if(dato.compareTo(elementos[i]) == 0)
			{
				rta= elementos[i];
			}
		} 
		return rta;
	}

	public T eliminar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T rta = null;

		if(buscar(dato)!= null)
		{
			for(int i=0; i<tamanoAct; i++)
			{
				if(dato.compareTo(elementos[i]) == 0)
				{
					rta = elementos[i];

					while(i+1 != tamanoAct)
						elementos[i] = elementos[i+1];

					if(i+1 == tamanoAct)
						elementos[i] = null;

					tamanoAct --;


				}
			}
		}
		return rta;
	}


}