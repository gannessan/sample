package com.gan.data.transformer;

import java.util.HashMap;
import java.util.Map;

public class Summary {
		
		private Map<SearchIndex, SummaryItem> summaryMap = new HashMap<>();

		public Summary incrementConfirmationToSummary(SearchIndex searchKey, long confirmedCount) {
			SummaryItem item =  summaryMap.get(searchKey);
			summaryMap.putIfAbsent(searchKey, new SummaryItem(0, 0));
			summaryMap.compute(searchKey, (k, v) -> v.increaseConfirmationCount(confirmedCount));
			
			return this;

		}

		public Summary incrementRecoveryToSummary(SearchIndex searchKey, long recoveryCount) {
			if ("04-16-2020".equals(searchKey.getDate()) && "united kingdom".equals(searchKey.getCountry())) {
				int k = 1;
			}
			SummaryItem item =  summaryMap.get(searchKey);
			summaryMap.putIfAbsent(searchKey, new SummaryItem(0, 0));
			summaryMap.compute(searchKey,
					(key, summaryItem) -> summaryItem.increaseRecoveredCount(recoveryCount));
			return this;
		}
		
		public SummaryItem getSummary(SearchIndex key) {
			
			return summaryMap.getOrDefault(key, new SummaryItem(0, 0));
		}

		@Override
		public String toString() {
			return "Summary [summaryMap=" + summaryMap + "]";
		}
		
		

	}