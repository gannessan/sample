package com.gan.data.transformer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
			this.date = normalizeDate(normalize(date));
			this.state = normalize(state);
			this.country = normalize(country);
			this.continent = normalize(continent);
			keyValueMap.put("date", this.date);
			if (Objects.nonNull(this.state)) {
				keyValueMap.put("state", this.state);
			}
			keyValueMap.put("country", this.country);
			keyValueMap.put("continent", this.continent);
			
		}

		public void setValue(String key, String value) {
			value = normalize(value);
			switch (key) {
			case "date":
				this.date = normalizeDate(value);
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
		
		private String normalizeDate(String date){
			if(date != null && date.matches("^(1[0-2]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])/(?:[0-9]{2})?[0-9]{2}$")) {
				DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern("M/d/yy")
	                    .toFormatter();
				  LocalDate parsedDate = LocalDate.parse(date, f);
				DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				return f2.format(parsedDate);
			}
			if(date != null && date.matches("^(?:[0-9]{2})?[0-9]{2}-(1[0-2]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])$")) {
				DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d")
	                    .toFormatter();
				  LocalDate parsedDate = LocalDate.parse(date, f);
				DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				System.out.println( f2.format(parsedDate));
				return f2.format(parsedDate);
			}
			
			return date;
		}
		
		private String normalize(String data) {
			return data == null || data.trim().length() == 0 ? null : removeChars(data).trim().toLowerCase();
		}

		private String removeChars(String data) {
			if(data.startsWith("\"")){
				return data.substring(1,data.length()-1 );
			}
			
			if(data.startsWith("'")){
				return data.substring(1,data.length()-1 );
			}
			return data;
		}

		public Set<SearchIndex> generatePossibleKeys() {
			Set<SearchIndex> searchKeys = new LinkedHashSet<>();
			List<String> keys = new ArrayList<>(keyValueMap.keySet());

			for (int i = 0; i < keys.size(); i++) {

				for (int k = i + 1; k < keys.size(); k++) {
					SearchIndex searchKey = new SearchIndex();
					searchKey.setValue(keys.get(i), keyValueMap.get(keys.get(i)));
					searchKeys.add(searchKey);
					for (int j = k; j < keys.size(); j++) {
						searchKey = searchKey.clone();
						searchKey.setValue(keys.get(j), keyValueMap.get(keys.get(j)));
						searchKeys.add(searchKey);
					}
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

		
		
		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		@Override
		public String toString() {
			return "SearchKey [date=" + date + ", state=" + state + ", country=" + country + ", continent=" + continent
					+ "]";
		}

	}