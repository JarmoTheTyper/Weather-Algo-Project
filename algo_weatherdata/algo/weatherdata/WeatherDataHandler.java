package algo.weatherdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * Retrieves temperature data from a weather station file.
 */
public class WeatherDataHandler {

	TreeMap<LocalDate, Data> whetherData = new TreeMap<>();

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

			if (whetherData.containsKey(date)) {
				addData(attributes, whetherData.get(date));
			}
			else{
				Data data = createData(attributes);
				whetherData.put(date, data);
			}
		}
	}

	/**
	 * Adds values to a Data Object
	 * @param attributes Array of String values to be added to the Data object
	 */
	private static void addData(String[] attributes, Data data){
		String time = attributes[1];
		String temperature = attributes[2];
		String quality = attributes[3];
		data.addTime(time);
		data.addTemperature(temperature);
		data.addQuality(quality);
	}

	/**
	 * Creates an object of Data
	 * @param attributes Array of String values to be added to the Data object
	 * @return object Data with the attributes as values
	 */
	private static Data createData(String[] attributes) {
		String time = attributes[1];
		String temperature = attributes[2];
		String quality = attributes[3];
		return new Data(time, temperature, quality);
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

		NavigableMap<LocalDate, Data> whetherSubMap;
		whetherSubMap = whetherData.subMap(dateFrom, true, dateTo,true);
		List<String> average = new ArrayList<>();

		for (Map.Entry<LocalDate, Data> entry : whetherSubMap.entrySet()){
			average.add(entry.getKey() + " average temperature: "
					+ entry.getValue().averageDataTemperature() + " degrees Celsius");
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
		//TODO: Implements method
		return null;
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
		//TODO: Implements method
		return null;
	}
}