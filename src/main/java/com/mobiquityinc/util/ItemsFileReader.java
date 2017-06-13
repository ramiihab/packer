package com.mobiquityinc.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.exception.APIException;

/*
 * Responsible for Reading the file from the Disk.
 */
public class ItemsFileReader {

	public static List<String> fileReader(String filename) {

		BufferedReader br = null;
		FileReader fr = null;

		ArrayList<String> listOfRawItems = new ArrayList<>();

		try {

			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				listOfRawItems.add(sCurrentLine);
			}

		} catch (IOException e) {
			 throw new APIException(e.toString());
		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				 throw new APIException(ex.toString());
			}

		}	
		return listOfRawItems;
	}
}
