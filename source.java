import java.util.Scanner;
import java.util.ArrayList;

public class source {

	public static void main(String[] args) {
		ArrayList<Trinket> trinketList = new ArrayList<Trinket>();
		String userName = "";
		double userValue = 0;
		double userWeight = 0;
		
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter a trinket name: \n");
		userName = input.nextLine();
		while (!userName.equals("-1")) {
			System.out.printf("Enter a trinket value: \n");
			userValue = input.nextDouble();
			System.out.printf("Enter a trinket weight: \n");
			userWeight = input.nextDouble();
			Trinket myItem; 
			myItem = new Trinket(userName, userValue, userWeight);
			trinketList.add(myItem);
			System.out.printf("Enter a trinket name: \n");
			input.nextLine();
			userName = input.nextLine();
		}
		int listLength = trinketList.size();
		for (int i = 0; i < listLength; i++) {
			System.out.println(trinketList.get(i));
		}
	}
	
	public static class Trinket {
		private String name;
		private double value;
		private double weight;
		
		public Trinket() {
			name = "Trinket";
			value = 0;
			weight = 0;
		}
		public Trinket(String n, double v, double w) {
			name = n;
			value = v;
			weight = w;
		}
		
		public double getValue() {
			return value;
		}
		public double getWeight() {
			return weight;
		}
		public String getName() {
			return name;
		}
		public void setValue(double value) {
			this.value = value;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {
			return "Name: " + name + "\nValue: " + value + "\nWeight: " + weight;
		}
		
	}
}
