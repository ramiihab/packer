package com.mobiquityinc.util;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CombinationTest {
	@Test
	public void testGetCombination() {

		// Arrange
		int[] arrayOfIndexes = { 1, 2, 3 };
		// Act
		List<List<Integer>> allCombinations = Combination.getCombination(arrayOfIndexes);
		// Assert
		Assert.assertEquals(allCombinations.size(), 7);

	}

}
