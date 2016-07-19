package by.gsu.epamlab.beans;

public class SomeClass {
	String helloMessage;
	
	public SomeClass() {
		helloMessage = "Hello, Ant!";
	}
	
	@Override
	public String toString() {
		return helloMessage;
	}
}
