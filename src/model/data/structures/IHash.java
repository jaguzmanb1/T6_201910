package model.data.structures;

import java.util.Iterator;

public interface IHash <K extends Comparable<K>, V>{

	void put(K uno, V dos);
	
	V get(K uno);
	
	V delete(K uno);
	
	Iterator<K> keys();
}
