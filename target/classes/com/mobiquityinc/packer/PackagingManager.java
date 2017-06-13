package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobiquityinc.models.Item;

/*
 * get the sum of weight and cost of each combination and remove the ones
 * that weights more than package limit
 */
public class PackagingManager {

	public static Map<Integer, Double> weightMap = null;
	public static Map<Integer, Integer> costMap = null;
	
	/*
	 * Calculate the sum and remove unwanted items
	 */
	public void prepareItemsList(List<List<Integer>> allCombinations, List<Item> listOfItems, double packageWeightLimit) {

		weightMap = new HashMap<Integer, Double>();
		costMap = new HashMap<Integer, Integer>();

		/*
		 * Get the sum of each combinations weight and cost and put them in a
		 * map attached with the combination index as its key
		 */
		for (int i = 0; i < allCombinations.size(); i++) {
			double weightSum = 0;
			int costSum = 0;
			for (int j = 0; j < allCombinations.get(i).size(); j++) {
				for (int k = 0; k < listOfItems.size(); k++) {
					if (listOfItems.get(k).getIndex() == allCombinations.get(i).get(j)) {
						weightSum += listOfItems.get(k).getWeight();
						costSum += listOfItems.get(k).getCost();
					}
				}
			}

			weightMap.put(i, weightSum);
			costMap.put(i, costSum);
		}

		// Remove items with exceeding weight
		ArrayList<Integer> itemsToBeRemoved = new ArrayList<Integer>();

		for (int i = 0; i < weightMap.size(); i++) {
			if (weightMap.get(i) > packageWeightLimit) {
				itemsToBeRemoved.add(i);
			}
		}

		for (int i = 0; i < itemsToBeRemoved.size(); i++) {
			weightMap.remove(itemsToBeRemoved.get(i));
			costMap.remove(itemsToBeRemoved.get(i));
		}

		/*
		 * check if all items are removed from the maps which means all
		 * combinations weights more than the package limit make the costMap =
		 * null to notify the getResult method to notify the pack method that
		 * there is no vaild combination
		 */
		if (costMap.size() == 0 && weightMap.size() == 0) {
			costMap = null;
		}
	}

	/*
	 * Calculate the Final Result
	 */
	public int getResult() {

		int result = 0;

		/*
		 * check if costMap equals null, to notify pack method that there is no
		 * valid combination
		 */
		if (costMap != null) {
			// Get max cost (Result)
			Map.Entry<Integer, Integer> maxEntry = null;
			Map<Integer, Double> duplicateCosts = new HashMap<Integer, Double>();

			// Loop over costMap to get max cost
			for (Map.Entry<Integer, Integer> entry : costMap.entrySet()) {

				if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
					maxEntry = entry;
				}
			}

			// loop over costMap to check if there is similar max costs
			for (Map.Entry<Integer, Integer> entry : costMap.entrySet()) {

				if (entry.getValue().equals(maxEntry.getValue())) {
					duplicateCosts.put(entry.getKey(), weightMap.get(entry.getKey()));
				}
			}

			/*
			 * check if there is two or more max costs get the one with less
			 * weight
			 */
			if (duplicateCosts.size() > 1) {
				Map.Entry<Integer, Double> lessWeightEntry = null;
				for (Map.Entry<Integer, Double> entry : duplicateCosts.entrySet()) {

					if (lessWeightEntry == null || entry.getValue().compareTo(lessWeightEntry.getValue()) < 0) {
						lessWeightEntry = entry;
					}
				}
				result = lessWeightEntry.getKey();
			} else {
				result = maxEntry.getKey();
			}

			return result;
		} else {
			return -1;
		}
	}

}
