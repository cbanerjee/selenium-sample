package com.sample;

public class SeleniumSample{
	public SeleniumSample() {}
	public static void main (String[] args) {
		System.out.println("Hello World");
		System.out.println(new SeleniumSample().getMessage());
	}
	private final String getMessage() {
        return "The app is working";
    }
}