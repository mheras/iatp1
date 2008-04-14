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
	/* Aptitude. */
	private Double aptitude;
	
	/**
	 * Gets individual's aptitude.
	 * 
	 * @return Individual's aptitude.
	 */
	public Double getAptitude() {
		return aptitude;
	}

	/**
	 * Sets individual's aptitude.
	 * @param aptitude Individual's aptitude.
	 */
	public void setAptitude(Double aptitude) {
		this.aptitude = aptitude;
	}

	/**
	 * A_Individual doesn't know what to do when input comes and what to return.
	 * 
	 * @param input
	 * @return The output
	 */
	public abstract Object operate(Object input);

	/* Getters and Setters. */

	/**
	 * Gets the ID.
	 * @return The ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID.
	 * @param id The ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Disposes the individual.
	 */
	public abstract void dispose();
}
