package model.data.structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Clase que representa un iterador 
 * @author nicot
 * @param <T>
 */
public class IteratorCola<T> implements Iterator<T>
{
	/**
	 * Nodo que representa al proximo del actual
	 */
	private Node<T> proximo;
	
	/**
	 * Construye el iterador y asigna el nodo proximo a un nodo inicial para comenzar la iteracion.
	 * @param primero
	 */
	public IteratorCola(Node<T> primero) 
	{
		// TODO Auto-generated constructor stub
		proximo=primero;
	}

	@Override
	public boolean hasNext() 
	{
		// TODO Auto-generated method stub
		return proximo!=null;
	}

	@Override
	public T next() 
	{
		// TODO Auto-generated method stub
		if ( proximo == null )
		{ 
			throw new NoSuchElementException(); 
		}
		T elemento = proximo.getElemento(); // ultimo elemento visitado
		proximo = proximo.getSiguiente(); // proximo nodo a visitar
		return elemento;
	}

}
