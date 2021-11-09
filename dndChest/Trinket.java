package application;

public class Trinket {
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
