package com.gan.application;

import java.io.Console;
import java.util.Scanner;

import com.gan.data.transformer.CovidCsvDataTransformer;
import com.gan.data.transformer.SearchIndex;
import com.gan.data.transformer.Summary;

public class Runner {
	
	public static void main(String[] args) {
		Summary summary = new CovidCsvDataTransformer().generateSummary();
		System.out.println(summary.getSummary(new SearchIndex())
				);
		
		while(true) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press 1 to search and 2 to exit : ");
		
		int value = scanner.nextInt();
		
		if(value == 1) {
			System.out.println("Enter ur search : ");
			String query = scanner.next();
			System.out.println(query);
			
		} else {
			System.exit(0);
		}
		 
		}
		
		
	}

}
