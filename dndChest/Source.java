package dndChest;
import java.util.Scanner;
import java.util.ArrayList;

public class Source {

	public static void main(String[] args) {
		ArrayList<Trinket> trinketList = new ArrayList<Trinket>();
		String userName = "";
		double userValue = 0;
		double userWeight = 0;
		Trinket myItem = null;
		
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter a trinket name: \n");
		userName = input.nextLine();
		while (!userName.equals("-1")) {
			System.out.printf("Enter a trinket value: \n");
			userValue = input.nextDouble();
			System.out.printf("Enter a trinket weight: \n");
			userWeight = input.nextDouble();
			myItem = new Trinket(userName, userValue, userWeight);
			System.out.println(System.identityHashCode(myItem));
			trinketList.add(myItem);
			myItem = null;
			System.out.printf("Enter a trinket name: \n");
			input.nextLine();
			userName = input.nextLine();
		}
		int listLength = trinketList.size();

		for (int i = 0; i < listLength; i++) {
			System.out.println(System.identityHashCode(trinketList.get(i)));
			System.out.println(trinketList.get(i));

		}
	}
}