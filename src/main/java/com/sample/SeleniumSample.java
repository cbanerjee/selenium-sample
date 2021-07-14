package com.sample;

public class SeleniumSample{
	public SeleniumSample() {}
	private final String output_to_be= "Hello, The app is working";
	public static void main (String[] args) {
		System.out.println(new SeleniumSample().getMessage());
	}
	private final String getMessage() {
        return output_to_be;
    }
}