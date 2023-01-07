package com.gan.data.transformer;

import java.util.HashMap;
import java.util.Map;

public class Summary {
		
		private Map<SearchIndex, SummaryItem> summaryMap = new HashMap<>();

		public Summary incrementConfirmationToSummary(SearchIndex searchKey, long confirmedCount) {
			summaryMap.putIfAbsent(searchKey, new SummaryItem(0, 0));
			summaryMap.computeIfPresent(searchKey, (k, v) -> v.increaseConfirmationCount(confirmedCount));
			
			return this;

		}

		public Summary incrementRecoveryToSummary(SearchIndex searchKey, long recoveryCount) {
			summaryMap.putIfAbsent(searchKey, new SummaryItem(0, 0));
			summaryMap.computeIfPresent(searchKey,
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