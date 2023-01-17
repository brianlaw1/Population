import java.util.List;
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
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public Population()
	{
		cities = new ArrayList<City>();
	}
	
	public static void main(String[]args)
	{
		Population run = new Population();
		run.runPopulation();
	}
	
	public void runPopulation()
	{
		printIntroduction();
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
				totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty least populous cities");
				System.out.printf("%-22s %-22s %-12s %,12d", "State", "Name", "Type",
						"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 2)
			{
				sortDescendingPopulation();
				long endMillisec = System.currentTimeMillis();
				totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty most populous cities");
				System.out.printf("%-22s %-22s %-12s %,12d", "State", "Name", "Type",
						"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 3)
			{
				sortAscendingName();
				long endMillisec = System.currentTimeMillis();
				totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty cities sorted by name");
				System.out.printf("%-22s %-22s %-12s %,12d", "State", "Name", "Type",
						"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 4)
			{
				sortDescendingName();
				long endMillisec = System.currentTimeMillis();
				totalMillisec = endMillisec - startMillisec;
				System.out.println("\nFifty cities sorted by name descending");
				System.out.printf("%-22s %-22s %-12s %,12d", "State", "Name", "Type",
						"population");
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i + ":");
					System.out.println(cities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			else if (selection == 5)
			{
				String state = "s";
				while (!isValidState(state))
				{
					state = Prompt.getString("Enter state name (ie. Alabama)")
				}
				startMillisec = System.currentTimeMillis();
				sortStatePopulation();
				long endMillisec = System.currentTimeMillis();
				totalMillisec = endMillisec - startMillisec;
				
				for (int i = 0; i < 50; i++)
				{
					System.out.printf("%3s",i + ":");
					System.out.println(stateCities.get(i).toString());
				}
				System.out.println("\nElapsed Time " + totalMillisec + " millliseconds");
			}
			
			else if (selection == 6)
			{
				String city = "c";
				while(!isValidCity(city))
				{
					city = Prompt.getString("Enter city name");
				}
			}
		}
	}
	
	private void readPopulationData()
	{
		Scanner read = FileUtils.openToRead(DATA_FILE);
		read.useDelimiter("[\t\n]");
		while (read.hasNextLine())
		{
			String state = read.next();
			String name = read.next();
			String type = read.next();
			int population = read.nextInt();
			cities.add(new City(name,state,type,population));
		}
	}
	
	public boolean isValidCity(String city)
	{
		for (int a = 0; a < cities.size(); a++)
		{
			if (city.equalsIgnoreCase(cities.get(a).getName()))
				return true;
		}
		return false;
	}
	
	public boolean isValidState(String state)
	{
		for (int a = 0; a < cities.size(); a++)
		{
			if (state.equalsIgnoreCase(cities.get(a).getState()))
				return true;
		}
		return false;
	}
	
	private void getAllCities(String city)
	{
		sortDescendingPopulation();
		while (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i)
		}
	}
	
	private void sortStatePopulation(String state)
	{
		sortDescendingPopulation();
		while (int i = 0; i < cities.size(); i++)
		{
			if (cities.get(i).getState().equals(state))
				stateCities.add(0, tempCity);
		}
	}
	
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
	
	private void sortDescendingPopulation()
	{
		int n = cities.size();
		temp = new ArrayList<City>();
		recursiveSort(0, n-1);
	}
	
	private void recursiveSortPopulation(int from, int to)
	{
		if (to - from < 2)
		{
			if (to > from && cities.get(to).getPopulation() < cities.get(from).getPopulation())
			{
				City cityTemp = cities.get(to);
				cities.set(to, cities.get(from));
				cities.set(from, cityTemp);
			}
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSort(from, middle);
			recursiveSort(middle + 1 ,to);
			merge(from, middle, to);
		}
	}
	
	private void mergePopulation(int from, int middle, int to)
	{
		int i = from;
		int j = middle + 1;
		int k = from;
		
		while (i <= middle && j <= to)
		{
			if (cities.get(i).getPopulation() > cities.get(j).getPopulation())
			{
				temp.set(k, cities.get(i));
				i++;
			}
			else
			{
				temp.set(k, cities.get(j));
				j++;
			}
			k++
		}
		
		while (i <= middle)
		{
			temp.set(k, cities.get(i));
			k++;
			i++;
		}
		
		while (j <= to)
		{
			temp.set(k, cities.get(j));
			k++;
			j++;
		}
		
		for (k = from; k <= to; k++)
		{
			cities.set(k, temp.get(k));
		}
	}
	
	private void sortAscendingName()
	{
		for (int n = 1; n < cities.size(); n++)
		{
			City tempCity = cities.get(n);
			
			int i = n;
			while (i > 0 && tempCity.compareTo(cities.get(i-1)) < 0)
			{
				cities.set(i, cities.get(i-1));
				i--;
			}
			
			cities.set(i, tempCity);
		}
	}
	
	private void sortDescendingName()
	{
		int n = cities.size();
		temp = new ArrayList<City>();
		recursiveSort(0, n-1);
	}
	
	private void recursiveSortName(int from, int to)
	{
		if (to - from < 2)
		{
			if (to > from && cities.get(to).getName() < cities.get(from).getName())
			{
				City cityTemp = cities.get(to);
				cities.set(to, cities.get(from));
				cities.set(from, cityTemp);
			}
		}
		else
		{
			int middle = (from + to)/2;
			recursiveSort(from, middle);
			recursiveSort(middle + 1 ,to);
			merge(from, middle, to);
		}
	}
	
	private void mergeName(int from, int middle, int to)
	{
		int i = from;
		int j = middle + 1;
		int k = from;
		
		while (i <= middle && j <= to)
		{
			if (cities.get(i).getName() > cities.get(j).getName())
			{
				temp.set(k, cities.get(i));
				i++;
			}
			else
			{
				temp.set(k, cities.get(j));
				j++;
			}
			k++
		}
		
		while (i <= middle)
		{
			temp.set(k, cities.get(i));
			k++;
			i++;
		}
		
		while (j <= to)
		{
			temp.set(k, cities.get(j));
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
