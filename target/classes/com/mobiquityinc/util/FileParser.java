package com.mobiquityinc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobiquityinc.models.Item;

/*
 * Responsible for file parsing and constructing Map of items and its corresponding package limit
 */
public class FileParser {

	public static Map<Integer, List<Item>> parse(List<String> listOfRawItems) {
		String line;
		int packageWeightLimit = 0;
		List<Item> itemsList = null;
		Map<Integer, List<Item>> itemsMap = new HashMap<Integer, List<Item>>();

		for (int i = 0; i < listOfRawItems.size(); i++) {
			line = listOfRawItems.get(i);

			// Check that the line is not null or empty
			if (line != null && !(line.trim().equals("") || line.trim().equals("\n"))) {

				// parse the package weight limit before the ":"
				packageWeightLimit = Integer.parseInt(line.split(":")[0].trim());
				/*
				 * Constrain #1 max weight that a package can take is <= 100 if
				 * package weight limit is greater than 100 make it equals 100
				 * -- Tested
				 */
				if (packageWeightLimit > 100) {
					packageWeightLimit = 100;
				}

				// parse the items line by ")" to get each item
				String[] itemsToBeParsed = line.split(":")[1].split("\\)");

				itemsList = new ArrayList<Item>();

				// loop over the items to parse it and construct Item object and
				// List of items in the current line
				for (int j = 0; j < itemsToBeParsed.length; j++) {
					Item item = new Item();

					/*
					 * split the whole item (1,53.38,�45 by the colon, trim it
					 * and delete all nondigit characters then parse it to
					 * integer then place the first item in array into index
					 * second to weight third to cost
					 */
					item.setIndex(Integer.parseInt(itemsToBeParsed[j].split(",")[0].trim().replaceAll("[^\\d.]", "")));
					item.setWeight(Double.parseDouble(itemsToBeParsed[j].split(",")[1].trim().replaceAll("[^\\d.]", "")));
					item.setCost(Integer.parseInt(itemsToBeParsed[j].split(",")[2].trim().replaceAll("[^\\d.]", "")));

					/*
					 * Constrain #3 max weight and cost of an item is 100 so any
					 * items with more weight or cost than 100 are forced to be
					 * 100 -- Tested
					 */
					if (item.getWeight() > 100) {
						item.setWeight(100);
					}
					if (item.getCost() > 100) {
						item.setCost(100);
					}
					itemsList.add(item);

					/*
					 * Constrain #2 there might be up to 15 items to choose from
					 * so any items above 15 will be discarded -- Tested
					 */
					if (itemsList.size() >= 15) {
						break;
					}
				}

				itemsMap.put(packageWeightLimit, itemsList);
			}

		}
		return itemsMap;
	}
}
