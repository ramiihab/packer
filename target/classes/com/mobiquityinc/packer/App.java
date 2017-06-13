package com.mobiquityinc.packer;

import java.util.Scanner;

import com.mobiquityinc.exception.APIException;

/**
 * A Class with a main method to run the app
 *
 */
public class App {
	public static void main(String[] args) {

		try {
			// Take file path as an input from the user
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter the file path: ");
			String filePath = reader.nextLine();
			reader.close();

			/*
			 * Call Packer.pack method to start read and parse the file then
			 * calculate the result
			 */
			String result = Packer.pack(filePath);
			String[] resultArray = result.split("-");
			for (int i = 0; i < resultArray.length; i++) {
				System.out.println("The result for package with weight limit " + resultArray[i].replaceAll("\\[", "").replaceAll("\\]", ""));				
				System.out.println("-----");
			}
		} catch (APIException e) {
			e.printStackTrace();
		}

	}
}
