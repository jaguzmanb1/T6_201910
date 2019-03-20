package model.data.structures;

import java.util.Iterator;

public class SeparateChainingHash <K extends Comparable<K>, V> implements IHash<K, V>{

	private static final int INIT_CAPACITY = 4;
	
	private int N;                
	
	private int M;                          
	
	private sequentialSearch<K, V>[] st;  
	
	public SeparateChainingHash(){ 
		this(997);  
		}   
	
	public SeparateChainingHash(int M)   { 
		this.M = M;   
		st = (sequentialSearch<K, V>[]) new sequentialSearch[M];  
		for (int i = 0; i < M; i++)      
			st[i] = new sequentialSearch();   
	}   

	public int size() {
		return N;
	} 
	private int hash(K key) {  
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public V get(K key)
	{  
		return (V) st[hash(key)].get(key);  
	}   

	public void put(K key, V val) 
	{  
		st[hash(key)].put(key, val);
		N++;


	} 

	private void resize(int chains) {
		SeparateChainingHash<K, V> temp = new SeparateChainingHash<K, V>(chains);
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M  = temp.M;
		this.N  = temp.N;
		this.st = temp.st;
	}
	@Override
	public V delete(K key) {
		V valor =null;
		if (key == null) 
			throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key)) 
			N--;
		valor=st[i].get(key);
		st[i].delete(key);


		if (M > INIT_CAPACITY && N <= 2*M) 

			resize(M/2);
		N--;
		return valor;
	} 

	@Override
	public Iterator<K> keys() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys())
				queue.enqueue(key);
		}
		return queue.iterator();
	} 

}
