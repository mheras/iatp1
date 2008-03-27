/**
 * 
 */
package edu.itba.ia.tp1.engine.population;

/**
 * Represents a single individual of a Population.
 * 
 * @author Pablo F. Siviero
 */
public abstract class A_Individual {

	/* Individual identification. */
	private Long id;

	/**
	 * A_Individual doesn't know what to do when input comes and what to return.
	 * 
	 * @param input
	 * @return The output
	 */
	public abstract Object operate(Object input);

	/* Getters and Setters. */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
