package com.mobiquityinc.util;

import java.util.ArrayList;
import java.util.List;

/*
 * Responsible for calculating all the possible combinations for a given list of Integers
 */
public class Combination {

	public static List<List<Integer>> allCombinations = null;

	/*
	 * arrayOfIndexes[] ---> Input Array data[] ---> Temporary array to store current
	 * combination start & end ---> Staring and Ending indexes in arrayOfIndexes[] index
	 * ---> Current index in data[] r ---> Size of a combination
	 */
	public static void combinationUtil(int arrayOfIndexes[], int data[], int start, int end, int index, int r) {
		ArrayList<Integer> tempCombination = new ArrayList<Integer>();

		if (index == r) {
			for (int j = 0; j < r; j++) {
				tempCombination.add(data[j]);
			}
			allCombinations.add(tempCombination);
			return;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data[index] = arrayOfIndexes[i];
			combinationUtil(arrayOfIndexes, data, i + 1, end, index + 1, r);
		}
	}

	// The main function that get all combinations of size r
	// in arrayOfIndexes[] of size n. This function mainly uses combinationUtil()
	public static List<List<Integer>> getCombination(int arrayOfIndexes[]) {

		allCombinations = new ArrayList<>();
		// Get all combination using temporary array 'data[]'
		for (int i = 1; i <= arrayOfIndexes.length; i++) {
			int r = i;
			// A temporary array to store all combination one by one
			int data[] = new int[r];
			int n = arrayOfIndexes.length;
			combinationUtil(arrayOfIndexes, data, 0, n - 1, 0, r);
		}
		return allCombinations;
	}
}
