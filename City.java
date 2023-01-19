/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Brian Law
 *	@since	1/9/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String name;
	private String state;
	private String designation;
	private int population;
	
	// constructor
	public City(String nameIn, String stateIn, String typeIn, int populationIn)
	{
		name = nameIn;
		state = stateIn;
		designation = typeIn;
		population = populationIn;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other)
	 {
		 if (this.population != other.population)
			return this.population - other.population;
		 else if (!this.state.equals(other.state))
			return this.state.compareTo(other.state);
	     return this.name.compareTo(other.name);	
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other)
	 {
		if (!this.name.equals(other.name))
			return false;
		else if (!this.state.equals(other.state))
			return false;
		return true;
	 }
	
	/**	Accessor methods */
	public int getPopulation()
	{
		return population;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getState()
	{
		return state;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
