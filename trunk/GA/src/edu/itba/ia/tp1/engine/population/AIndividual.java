/**
 * 
 */
package edu.itba.ia.tp1.engine.population;

/**
 * Represents a single individual of a Population.
 * 
 * @author Pablo F. Siviero
 */
public abstract class AIndividual {
	
	/* Individual identification. */
	private Long id;

	/* Getters and Setters. */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
