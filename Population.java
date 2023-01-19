import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Brian Law
 *	@since	1/9/23
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	private List<City> temp;
	private List<City> stateCities;
	private List<City> sameNameCities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population()
	{
		cities = new ArrayList<City>();
		sameNameCities = new ArrayList<City>();
		stateCities = new ArrayList<City>();
	}
	
	public static void main(String[]args)
	{
		Population run = new Population();
		run.runPopulation();
	}
	
	/** Runs the program by getting the users selection, and then sorting
	 * based on the input	*/
	public void runPopulation()
	{
		printIntroduction();
		readPopulationData();
		int selection = 0;
		while (selection != 9)
		{
			printMenu();
			selection = Prompt.getInt("Enter selection");
			long startMillisec = System.currentTimeMillis();
			if (selection == 1)
			{
				sortAscendingPopulation();
				long endMillisec = System.currentTimeMillis();
				long totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty least populous cities");
				//System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type", "population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 2)
			{
				sortDescendingPopulation();
				long endMillisec = System.currentTimeMillis();
				long totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty most populous cities");
		//		System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type",
		//				"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 3)
			{
				sortAscendingName();
				long endMillisec = System.currentTimeMillis();
				long totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty cities sorted by name");
				//System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type",
				//		"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 4)
			{
				sortDescendingName();
				long endMillisec = System.currentTimeMillis();
				long totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty cities sorted by name descending");
			//	System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type",
			//			"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 5)
			{
				String state = "s";
				while (!isValidState(state))
				{
					state = Prompt.getString("Enter state name (ie. Alabama)");
				}
				startMillisec = System.currentTimeMillis();
				sortStatePopulation(state);
				long endMillisec = System.currentTimeMillis();
				long totalMillisec = endMillisec - startMillisec;
				
				System.out.println("Fifty most populous cities in " + state);
			//	System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type",
			//			"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(stateCities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			
			else if (selection == 6)
			{
				String city = "c";
				while (!isValidCity(city))
				{
					city = Prompt.getString("Enter city name");
				}
				startMillisec = System.currentTimeMillis();
				getAllCities(city);
				long endMillisec = System.currentTimeMillis();
				long  totalMillisec = endMillisec - startMillisec;
				
				System.out.println("City " + city + " by population");
		//		System.out.printf("%-22s %-22s %-12s %,12s", "State", "Name", "Type",
		//				"population");
				
				for (int i = 0; i < sameNameCities.size(); i++)
				{
					System.out.printf("%3s",i+1 + ":");
					System.out.println(sameNameCities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
		}
	}
	
	/**	Uses FileUtils to read all of the cities' data from a file
	 */
	private void readPopulationData()
	{
		Scanner read = FileUtils.openToRead(DATA_FILE);
		read.useDelimiter("[\t\n]");
		while (read.hasNext())
		{
			String state = read.next();
			String name = read.next();
			String type = read.next();
			int population = read.nextInt();
			cities.add(new City(name,state,type,population));
		}
	}
	
	/**
	 * 	Checks whether the user's inputted city is a valid city that exists
	 * 	
	 * 	@param city		The user's inputted city
	 * 	@return			true if it is a valid city, false if not
	 */
	public boolean isValidCity(String city)
	{
		for (int a = 0; a < cities.size(); a++)
		{
			if (city.equalsIgnoreCase(cities.get(a).getName()))
				return true;
		}
		return false;
	}
	
	/**
	 * 	Checks whether the user's inputted state is a valid city that exists
	 * 	
	 * 	@param state	The user's inputted state
	 * 	@return			true if it is a valid state, false if not
	 */
	public boolean isValidState(String state)
	{
		for (int a = 0; a < cities.size(); a++)
		{
			if (state.equalsIgnoreCase(cities.get(a).getState()))
				return true;
		}
		return false;
	}
	
	/**
	 * 	Gets all the cities with the same name, chosen by the user
	 * 
	 * 	@param city		The user's chosen city name
	 */
	private void getAllCities(String city)
	{
		sortDescendingPopulation();
		for (int i = 0; i < cities.size(); i++)
		{
			if (city.equals(cities.get(i).getName()))
				sameNameCities.add(cities.get(i));
		}
	}
	
	/**
	 * 	Sorts the cities within a state chosen by the user
	 * 
	 * 	@param state	The user's chosen state
	 */
	private void sortStatePopulation(String state)
	{
		sortDescendingPopulation();
		for (int i = 0; i < cities.size(); i++)
		{
			if (state.equals(cities.get(i).getState()))
				stateCities.add(cities.get(i));
		}
	}
	
	/**
	 * 	Sorts all cities ascending by population by using selection sort
	 */
	private void sortAscendingPopulation()
	{
		for (int a = cities.size(); a > 1; a--)
		{
			int maxIndex = 0; 
			for (int i = 0; i < a; i++)
			{
				if (cities.get(i).getPopulation() > cities.get(maxIndex).getPopulation())
					maxIndex = i;
			}
			
			City tempCity = cities.get(maxIndex);
			cities.set(maxIndex, cities.get(a-1));
			cities.set(a-1, tempCity);
		}
	}
	
	/**
	 * 	Calls helper methods to sort all cities descending by population by 
	 * 	using merge sort
	 */
	private void sortDescendingPopulation()
	{
		int n = cities.size();
		//System.out.println(cities.size());
		temp = new ArrayList<City>();
		recursiveSortPopulation(0, n-1);
	}
	
	/**
	 * 	A recursive method that continues to split the list into lists that are to be sorted
	 * 
	 * 	@param from		The starting index to be sorted
	 * 	@param to		The ending index to be sorted
	 */
	private void recursiveSortPopulation(int from, int to)
	{
		if (to - from < 2)
		{
			if (to > from && cities.get(to).getPopulation() > cities.get(from).getPopulation())
			{
				City cityTemp = cities.get(to);
				cities.set(to, cities.get(from));
				cities.set(from, cityTemp);
			}
		}
		else
		{
			int middle = (from + to)/2;
			if (middle != 0)
			{
				//System.out.println(from + " " + middle + " " + to);
				recursiveSortPopulation(from, middle);
				recursiveSortPopulation(middle + 1 ,to);
				mergePopulation(from, middle, to);
			}
		}
	}
	
	/**
	 * 	Merges 2 array lists that have been sorted
	 * 	
	 *  @param from		The starting index of the list that has been sorted
	 * 	@param middle	The middle index of the list that has been sorted
	 * 	@param to		The end index of the list that has been sorted
	 */
	private void mergePopulation(int from, int middle, int to)
	{
		int i = from;
		int j = middle + 1;
		int k = from;
		
		while (i <= middle && j <= to)
		{
			if (cities.get(i).getPopulation() > cities.get(j).getPopulation())
			{
				temp.add(k, cities.get(i));
				i++;
			}
			else
			{
				//System.out.println(i + " " + j + " " + k);
				temp.add(k, cities.get(j));
				j++;
			}
			k++;
		}
		
		while (i <= middle)
		{
			temp.add(k, cities.get(i));
			k++;
			i++;
		}
		
		while (j <= to)
		{
			temp.add(k, cities.get(j));
			k++;
			j++;
		}
		
		for (k = from; k <= to; k++)
		{
			cities.set(k, temp.get(k));
		}
	}
	
	/**
	 * 	Sorts all cities ascending by name alphabetically by using insertion sort
	 */
	private void sortAscendingName()
	{
		for (int n = 1; n < cities.size(); n++)
		{
			City tempCity = cities.get(n);
			
			int i = n;
			while (i > 0 && tempCity.getName().compareTo(cities.get(i-1).getName()) < 0)
			{
				cities.set(i, cities.get(i-1));
				i--;
			}
			
			cities.set(i, tempCity);
		}
	}
	
	/**
	 * 	Calls helper methods to sort all cities descending by name alphabetically by 
	 * 	using merge sort
	 */
	private void sortDescendingName()
	{
		int n = cities.size();
		temp = new ArrayList<City>();
		recursiveSortName(0, n-1);
	}
	
	/**
	 * 	A recursive method that continues to split the list into lists that are to be sorted
	 * 
	 * 	@param from		The starting index to be sorted
	 * 	@param to		The ending index to be sorted
	 */
	private void recursiveSortName(int from, int to)
	{
		if (to - from < 2)
		{
			if (to > from && cities.get(to).getName().compareTo(cities.get(from).getName())>0)
			{
				City cityTemp = cities.get(to);
				cities.set(to, cities.get(from));
				cities.set(from, cityTemp);
			}
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSortName(from, middle);
			recursiveSortName(middle + 1 ,to);
			mergeName(from, middle, to);
		}
	}
	
	/**
	 * 	Merges 2 array lists that have been sorted
	 * 	
	 *  @param from		The starting index of the list that has been sorted
	 * 	@param middle	The middle index of the list that has been sorted
	 * 	@param to		The end index of the list that has been sorted
	 */
	private void mergeName(int from, int middle, int to)
	{
		int i = from;
		int j = middle + 1;
		int k = from;
		
		while (i <= middle && j <= to)
		{
			if (cities.get(i).getName().compareTo(cities.get(j).getName())>0)
			{
				temp.add(k, cities.get(i));
				i++;
			}
			else
			{
				temp.add(k, cities.get(j));
				j++;
			}
			k++;
		}
		
		while (i <= middle)
		{
			temp.add(k, cities.get(i));
			k++;
			i++;
		}
		
		while (j <= to)
		{
			temp.add(k, cities.get(j));
			k++;
			j++;
		}
		
		for (k = from; k <= to; k++)
		{
			cities.set(k, temp.get(k));
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

}
