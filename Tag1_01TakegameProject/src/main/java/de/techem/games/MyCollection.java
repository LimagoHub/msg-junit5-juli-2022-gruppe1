package de.techem.games;

import java.util.Optional;

/**
 * 
 * @author JoachimWagner
 *
 * @param <T>
 */
public interface MyCollection<T> {
	
	/**
	 * Fügt ein Element am Ende der Collection ein
	 * @param t
	 */
	void append(T t); 
	boolean movePrevious();
	boolean moveNext();
	default boolean moveFirst() {
		if(isEmpty()) return false;
		while(movePrevious()) {
			// ok
		}
		return true;
	}
	default boolean moveLast() {
		if(isEmpty()) return false;
		while(moveNext()) {}
		return true;
	}
	Optional<T> get();
	boolean remove();
	boolean isEmpty();
	default void clear() {
		while(remove()) {}
	}
}
