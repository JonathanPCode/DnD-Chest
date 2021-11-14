package application;

public class Trinket {
	private String name;
	private String description;
	private double value;
	private double weight;
	
	public Trinket() {
		name = "Trinket";
		value = 0;
		weight = 0;
		description = "";
	}
	public Trinket(String n, String d, double v, double w) {
		name = n;
		value = v;
		weight = w;
		description = d;
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
	public String getDesc() {
		return description;
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
	public void setDesc(String desc) {
		this.description = desc;
	}
	
	public String toString() {
		return "Name: " + name + "\nValue: " + value + "\nWeight: " + weight;
	}
}
