package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.models.Item;
import com.mobiquityinc.util.*;

/*
 * Responsible for connecting all project modules and result preparation
 * it loops over the items map constructed by the file parser and pass it to packing manager to decide the Result	
 */
public class Packer {

	public static String pack(String filePath) throws APIException {

		List<List<Integer>> allCombinations = null;
		List<Item> listOfItems = new ArrayList<Item>();
		double packageWeightLimit = 0;
		int[] arrayOfIndexes = null;
		String result = "";
		PackagingManager packagingManager = new PackagingManager();

		// Pass filePath to FileReader to get items as List<fileLines>
		List<String> fileLines = ItemsFileReader.fileReader(filePath);

		// Pass fileLines to FileParser to get itemsMap
		Map<Integer, List<Item>> itemsMap = FileParser.parse(fileLines);

		/*
		 * construct list of indexes For current items list
		 */
		for (Integer key : itemsMap.keySet()) {

			packageWeightLimit = key;

			listOfItems = itemsMap.get(key);
			arrayOfIndexes = new int[itemsMap.get(key).size()];
			for (int i = 0; i < itemsMap.get(key).size(); i++) {
				arrayOfIndexes[i] = itemsMap.get(key).get(i).getIndex();
			}

			// get all possible combinations
			allCombinations = new ArrayList<>();
			allCombinations = Combination.getCombination(arrayOfIndexes);

			// get sums and remove items with exceeding weight
			packagingManager.prepareItemsList(allCombinations, listOfItems, packageWeightLimit);

			// get Result
			int maxCostKey = packagingManager.getResult();

			if (maxCostKey != -1) {

				result += packageWeightLimit + " is " +String.valueOf(allCombinations.get(maxCostKey)) + "-" ;
			} else {
				result += packageWeightLimit + " is " +" None" + "-";
			}
		}
		return result;
	}

}
