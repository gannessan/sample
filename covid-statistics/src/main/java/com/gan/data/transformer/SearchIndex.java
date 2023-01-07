package com.gan.data.transformer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public  class SearchIndex {

		private String date;
		private String state;
		private String country;
		private String continent;
		private Map<String, String> keyValueMap = new LinkedHashMap<>();
		
		

		public SearchIndex() {
		}

		public SearchIndex(String date, String state, String country, String continent) {
			this.date = normalize(date);
			this.state = normalize(state);
			this.country = normalize(country);
			this.continent = normalize(continent);
			keyValueMap.put("date", this.date);
			if (Objects.nonNull(this.state)) {
				keyValueMap.put("state", state);
			}
			keyValueMap.put("country", this.country);
			keyValueMap.put("continent", this.continent);

		}

		public void setValue(String key, String value) {
			value = normalize(value);
			switch (key) {
			case "date":
				this.date = value;
				break;
			case "state":
				this.state = value;
				break;
			case "country":
				this.country = value;
				break;
			case "continent":
				this.continent = value;
				break;

			default:
				break;
			}
		}
		
		private String normalize(String data) {
			return data == null || data.trim().length() == 0 ? null : data.trim();
		}

		public Set<SearchIndex> generatePossibleKeys() {
			Set<SearchIndex> searchKeys = new LinkedHashSet<>();
			List<String> keys = new ArrayList<>(keyValueMap.keySet());

			for (int i = 0; i < keys.size(); i++) {
				SearchIndex searchKey = new SearchIndex();
				for (int j = i; j < keys.size(); j++) {
					searchKey = searchKey.clone();
					searchKey.setValue(keys.get(j), keyValueMap.get(keys.get(j)));
					searchKeys.add(searchKey);
				}
			}
			searchKeys.add(new SearchIndex());
			return searchKeys;
		}

		@Override
		public int hashCode() {
			return Objects.hash(continent, country, date, state);
		}

		public SearchIndex clone() {
			return new SearchIndex(date, state, country, continent);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SearchIndex other = (SearchIndex) obj;
			return Objects.equals(continent, other.continent) && Objects.equals(country, other.country)
					&& Objects.equals(date, other.date) && Objects.equals(state, other.state);
		}

		@Override
		public String toString() {
			return "SearchKey [date=" + date + ", state=" + state + ", country=" + country + ", continent=" + continent
					+ "]";
		}

	}