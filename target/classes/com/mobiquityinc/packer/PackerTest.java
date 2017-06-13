package com.mobiquityinc.packer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PackerTest {
	@Test
	public void testFullscenario() {

		// Arrange
		// Act
		String result = Packer.pack("C:\\testFile.txt");
		// Assert
		Assert.assertNotNull(result);
	}
}
