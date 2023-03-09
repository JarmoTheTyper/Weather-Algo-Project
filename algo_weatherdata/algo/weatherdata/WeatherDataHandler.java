package algo.weatherdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;


/**
 * Retrieves temperature data from a weather station file.
 */
public class WeatherDataHandler {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	private final TreeMap<LocalDate, Data> weatherData = new TreeMap<>();

	/**
	 * Load weather data from file.
	 * 
	 * @param filePath path to file with weather data
	 * @throws IOException if there is a problem while reading the file
	 */

	public void loadData(String filePath) throws IOException {
		List<String> fileData = Files.readAllLines(Paths.get(filePath));

		for(String s : fileData){
			String[] attributes = s.split(";");
			LocalDate date = LocalDate.parse(attributes[0]);

			if (weatherData.containsKey(date)) {
				addData(attributes, weatherData.get(date));
			}
			else{
				Data data = new Data();
				addData(attributes, data);
				weatherData.put(date, data);
			}
		}
	}

	/**
	 * Adds values to a Data Object
	 * @param attributes Array of String values to be added to the Data object
	 */
	private static void addData(String[] attributes, Data data){
		data.addTime(attributes[1]);
		data.addTemperature(attributes[2]);
		data.addQuality(attributes[3]);
	}

	/**
	 * Search for average temperature for all dates between the two dates (inclusive).
	 * Result is sorted by date (ascending). When searching from 2000-01-01 to 2000-01-03
	 * the result should be:
	 * 2000-01-01 average temperature: 0.42 degrees Celsius
	 * 2000-01-02 average temperature: 2.26 degrees Celsius
	 * 2000-01-03 average temperature: 2.78 degrees Celsius
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return average temperature for each date, sorted by date  
	 */
	public List<String> averageTemperatures(LocalDate dateFrom, LocalDate dateTo) {

		List<String> average = new ArrayList<>();
		for (Map.Entry<LocalDate, Data> entry : weatherData.subMap(dateFrom, true, dateTo,true).entrySet()){
			average.add(entry.getKey() + " average temperature: "
					+ df.format(entry.getValue().averageDataTemperature()) + " degrees Celsius");
		}

		return average;
	}
	/**
	 * Search for missing values between the two dates (inclusive) assuming there 
	 * should be 24 measurement values for each day (once every hour). Result is
	 * sorted by number of missing values (descending). When searching from
	 * 2000-01-01 to 2000-01-03 the result should be:
	 * 2000-01-02 missing 1 values
	 * 2000-01-03 missing 1 values
	 * 2000-01-01 missing 0 values
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return dates with missing values together with number of missing values for each date, sorted by number of missing values (descending)
	 */
	public List<String> missingValues(LocalDate dateFrom, LocalDate dateTo) {

		int hours = 24;
		Map<LocalDate, Integer> tmp = new TreeMap<>();
		NavigableMap<LocalDate, Data> subMap= weatherData.subMap(dateFrom, true, dateTo,true);
		for (Map.Entry<LocalDate, Data> entry :subMap.entrySet()){
			int mis = hours - entry.getValue().getTime().size();
			tmp.put(entry.getKey(), mis);
		}

		List<Map.Entry<LocalDate, Integer>> missingList = new ArrayList<>(tmp.entrySet());
		missingList.sort(Comparator.comparing(Map.Entry<LocalDate, Integer> :: getValue)
				.reversed().thenComparing(Map.Entry :: getKey));

		List<String> missing = new ArrayList<>();
		for(Map.Entry<LocalDate, Integer> entry : missingList){
			missing.add(entry.getKey() + " missing " + entry.getValue() + " values");
		}

		return missing;
	}
	/**
	 * Search for percentage of approved values between the two dates (inclusive).
	 * When searching from 2000-01-01 to 2000-01-03 the result should be:
	 * Approved values between 2000-01-01 and 2000-01-03: 32.86 %
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return period and percentage of approved values for the period  
	 */
	public List<String> approvedValues(LocalDate dateFrom, LocalDate dateTo) {
		double sumSize = 0;
		double countApproved = 0;

		NavigableMap<LocalDate, Data> subMap= weatherData.subMap(dateFrom, true, dateTo,true);
		for (Map.Entry<LocalDate, Data> entry :subMap.entrySet()){
			sumSize += entry.getValue().getQuality().size();

			for(String c: entry.getValue().getQuality()){
				if(c.equals("G")) countApproved += 1;
			}
		}
		double approved = countApproved/sumSize * 100;
		List<String> answer = new ArrayList<>();
		String result = "Approved values between " + dateFrom + " and " + dateTo + ": " + df.format(approved) + " %";
		answer.add(result);
		return answer;
	}
}