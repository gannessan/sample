package com.gan.data.transformer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CovidCsvDataTransformer {
	
	Map<String, String> continentMap ;
	
	public CovidCsvDataTransformer(){
		List<String> lines = readFromFile("data/countries_to_continent.csv");
		continentMap = lines.stream().skip(1).map(s -> s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).collect(Collectors.toMap(con-> con[1], con-> con[0]));
	}

	
	public Summary generateSummary() {
		Summary summary = new Summary();
		prepareSummarizedData("data/covid_confirmed.csv", (k, c) -> summary.incrementConfirmationToSummary(k, c));
		prepareSummarizedData("data/covid_recovered.csv", (k, c) -> summary.incrementRecoveryToSummary(k, c));
		System.out.println("completed loading data");
		
		return summary;
	}

	public void prepareSummarizedData(String fileLocation, BiFunction<SearchIndex, Long, Summary> summary) {
		System.out.println(fileLocation);
		List<String> lines = readFromFile(fileLocation);
		List<String> dates = Arrays.stream(lines.get(0).split(",")).collect(Collectors.toList());

		lines.stream().skip(1).map(s -> s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")).forEach(array -> {
			int dateIndex = 4;
			String state = array[0];
			String country = array[1];
			String continent = continentMap.get(country);
			if(continent == null) {
				System.out.println("there is no continent mapping present for "+ country);
			}

			while (dateIndex < dates.size()) {
				String date = dates.get(dateIndex);
				Set<SearchIndex> combinationKeys = new SearchIndex(date, state, country, continent)
						.generatePossibleKeys();
				for (SearchIndex key : combinationKeys) {
					summary.apply(key, Long.valueOf(array[dateIndex]));
				}
				dateIndex++;
			}

		});

	}


	private List<String> readFromFile(String fileLocation) {
		List<String> lines;
		try {
			lines = Files
					.readAllLines(Path.of(Thread.currentThread().getContextClassLoader().getResource(fileLocation).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException("Error while paring file "+ fileLocation , e);
		}
		return lines;
	}

}
