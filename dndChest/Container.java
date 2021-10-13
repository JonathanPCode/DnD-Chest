package dndChest;

import java.util.ArrayList;

public class Container {
	private String name;
	private ArrayList<Trinket> trinketList = new ArrayList<Trinket>();
	private double maxWeight;
	private double containedValue = 0;
	private double containedWeight = 0;
	
	public Container() {
		name = "Bag of Holding";
		maxWeight = Double.POSITIVE_INFINITY;
	}
	
	public Container(String n, double w) {
		name = n;
		maxWeight = w;
	}
	
	public void addTrinket(Trinket t) {
		if (containedWeight + t.getWeight() < maxWeight) {
			containedValue += t.getValue();
			containedWeight += t.getWeight();
			trinketList.add(t);
		}
		else {
			System.out.println("Trinket cannot fit in container.");
		}
	}
	
	public void removeTrinket(int index) {
		containedValue -= trinketList.get(index).getValue();
		containedWeight -= trinketList.get(index).getWeight();
		trinketList.remove(index);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	
	public double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(double w) {
		if (containedWeight < w) {
			maxWeight = w;
		}
		else {
			System.out.println("Contained weight greater than given max weight.");
		}
	}
	
	public double getContainedValue() {
		return containedValue;
	}
	
	public double getContainedWeight() {
		return containedWeight;
	}
	
	public String toString() {
		int listLength = trinketList.size();
		String catString = "";
		for (int i = 0; i < listLength; i++) {
			catString = catString + "\n" + trinketList.get(i);
		}
		return catString;
	}
}
