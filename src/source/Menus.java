package source;

public class Menus {
	
	public Menus(){}
	
	public static void startMenu(){
		System.out.println("====pick or dilivery=====");
		System.out.println("0. Exit.");
		System.out.println("1. Pickup.");
		System.out.println("2. dilivery.");
		System.out.print("Choose one of the previous options [0-2]: ");
	}
	
	public static void SizeMenu(){
		System.out.println("=========Size=========");
		System.out.println("0. Exit.");
		System.out.println("1. small.");
		System.out.println("2. median.");
		System.out.println("3. Large.");

		System.out.print("Choose one of the previous options [0-3]: ");
	}
	
	public static void Menu(){
		System.out.println("=========menu=========");
		System.out.println("0. Exit.");
		System.out.println("1. Pizza.");
		System.out.println("2. Drink.");

		System.out.print("Choose one of the previous options [0-2]: ");
	}
	
	public static void PizzaMenu(){
		System.out.println("=========Pizza=========");
		System.out.println("0. Exit.");
		System.out.println("1. Italian Pizza.");
		System.out.println("2. Canadian Pizza.");

		System.out.print("Choose one of the previous options [0-2]: ");
	}
	
	public static void ToppingMenu(){
		System.out.println("=========Topping=========");
		System.out.println("0. Exit.");
		System.out.println("1. sausage.");
		System.out.println("2. chiken.");
		System.out.println("3. pieapple.");
		System.out.println("4. mushroom.");

		System.out.print("Choose one of the previous options [0-4]: ");
		
		
	}
	
	public static void DrinkMenu(){
		System.out.println("=========Drink=========");
		System.out.println("0. Exit.");
		System.out.println("1. coke 350ml.");
		System.out.println("2. coke 750ml.");
		System.out.println("3. diet coke 350ml.");
		System.out.println("4. diet coke 750ml.");
		System.out.println("5. orange juice 350ml.");
		System.out.println("6. orange juice 750ml.");
		
	}
	
	//Print menu options
	public static void OpMenu() {
		System.out.println("=========Operation=========");
		System.out.println("0. Exit.");
		System.out.println("1. Insert a record.");
		System.out.println("2. Select a record.");
		System.out.println("3. Print schema.");
		System.out.println("4. Print table schema.");
		System.out.println("5. New Customer ");
		System.out.println("6. Customer Update");
		System.out.println("7. Place Order");
		System.out.println("8. update Order");
		System.out.println("9. print bill");
		System.out.print("Choose one of the previous options [0-9]: ");
	}

}
