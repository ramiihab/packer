package com.mobiquityinc.util;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mobiquityinc.exception.APIException;

public class ItemsFileReaderTest {

	@Test
	public void testCorrectParameters() {

		// Arrange
		// Act
		List<String> itemsList = ItemsFileReader.fileReader("C:\\testFile.txt");
		// Assert
		Assert.assertNotNull(itemsList);

	}

	@Test(expectedExceptions = APIException.class)
	public void testInCorrectParameters() {

		// Arrange
		// Act
		ItemsFileReader.fileReader("wrong Parameter");
		// Assert
	}
}
