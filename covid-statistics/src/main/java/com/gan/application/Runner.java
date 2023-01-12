package com.gan.application;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.gan.data.transformer.CovidCsvDataTransformer;
import com.gan.data.transformer.SearchIndex;
import com.gan.data.transformer.Summary;

public class Runner {
	
	public static void main1(String[] args) {
		String[] ssStrings = "country='Korea, South'".split(",(?=(?:[^']*'[^']*')*[^']*$)");
		System.out.println(ssStrings[0]);
	}
		
	public static void main(String[] args) {
		Summary summary = new CovidCsvDataTransformer().generateSummary();
		System.out.println(summary.getSummary(new SearchIndex())
				);
		Set<String> acceptedFields = new HashSet<>();
		acceptedFields.add("date");
		acceptedFields.add("country");
		acceptedFields.add("continent");
		acceptedFields.add("state");
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		
	
		System.out.println("Press 1 to search and 2 to exit : ");
		
		int value = scanner.nextInt();
		if(value != 1 && value != 2) {
			continue;
		}
		scanner.nextLine();
		while(value == 1) {
			
			System.out.println("Enter ur search query : ");
			String query = scanner.nextLine();
			SearchIndex searchIndex = new SearchIndex();
			for(String condition : query.split(",(?=(?:[^']*'[^']*')*[^']*$)")) {
				if(!condition.matches("(.*)='(.*)'")){
					System.out.println(query + "Is not a valid format");
					System.out.println("Valid format is <state|country|continent|date>='<value>',<state|country|continent|date>='<value>'");
					break;
				}
				String[] fieldValues =  condition.split("=");
				if(fieldValues.length!= 2) {
					System.out.println(query + "Is not a valid format");
					System.out.println("Valid format is <state|country|continent|date>='<value>',<state|country|continent|date>='<value>'");
					break;
					
				}
				if(!acceptedFields.contains(fieldValues[0].trim().toLowerCase())) {
					System.out.println(fieldValues[0] + "Is not a valid field name");
					System.out.println("Valid names are <state|country|continent|date>'");
					break;
				}
				searchIndex.setValue(fieldValues[0].trim().toLowerCase(), fieldValues[1]);
				
				
			}
			System.out.println(summary.getSummary(searchIndex));
			break;
			
		} 
		if(value != 1 ) {
			scanner.close();
			System.exit(0);
		}
		
		 
		}
		
		
	}

	
}
