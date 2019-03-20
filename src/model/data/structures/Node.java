package model.data.structures;

/**
 * Clase que representa un nodo dentro de la lista
 * @author nicot
 * @param <T> tipo generico del nodo
 */
public class Node<T> 
{
	/**
	 * Elemento que el nodo contiene
	 */
	private T elemento;
	/**
	 * Elemento siguiente al nodo. 
	 */
	private Node<T> siguiente;
	/**
	 * Constructor de la clase nodo
	 * @param dato elemento o dato a guardar en el nodo
	 * @param Nodo siguiente al que se esta creando.
	 */
	public Node(T dato)
	{
		elemento=dato;
	}
	/**
	 * Constructor de la clase nodo
	 * @param dato elemento o dato a guardar en el nodo
	 * @param Nodo siguiente al que se esta creando.
	 */
	public Node(T dato,Node<T> pSiguiente)
	{
		elemento=dato;
		siguiente=pSiguiente;
	}
	
	/**
	 * Retorna el elemento del nodo
	 * @return elemento del nodo.
	 */
	public T getElemento()
	{
		return elemento;
	}
	/**
	 * Cambia el elemento del nodo.
	 * @param pElemento elemento a asignar al nodo
	 */
	public void setElemento(T pElemento)
    {
    	elemento=pElemento;
    }
	
	/**
	 * Retorna el siguiente del nodo
	 * @return siguiente del nodo
	 */
	public Node<T> getSiguiente()
	{
		return siguiente;
	}
	/**
	 * Cambia el siguiente nodo de la lista.
	 * @param pNodo nodo a asignar como siguiente
	 */
	public void setSiguiente(Node<T> pNodo)
    {
    	siguiente=pNodo;
    }
	
}
