package algo.weatherdata;


import java.util.ArrayList;
import java.util.List;

/**
 * Create an objekt of an isolated instance of whether data
 */
public class Data {
    private final List<String> time = new ArrayList<>();
    private final List<String> temperature = new ArrayList<>();
    private final List<String> quality = new ArrayList<>();

    /**
     * Construct a data objekt
     * @param time The time of the whether data instance
     * @param temperature The temperature of the whether data instance
     * @param quality The quality of the whether data instance
     */
    public Data(String time, String temperature, String quality){
        this.time.add(time);
        this.temperature.add(temperature);
        this.quality.add(quality);
    }

    public Data(){

    }

    /**
     * Calculates the average temperature of the list temperature
     * @return the average temperature of the list temperature
     */
    public String averageDataTemperature(){
        double sum = 0;
        for(String temp : temperature){
            sum += Double.parseDouble(temp);
        }
        return String.format("%.2f", sum / temperature.size());
    }

    /**
     * Getts the list of time values
     * @return the list of time values
     */
    public List<String> getTime(){
        return time;
    }

    /**
     * Getts the list of temperature values
     * @return the list of temperature values
     */
    public List<String> getTemperatures(){
        return temperature;
    }

    /**
     * Getts the list of quality values
     * @return the list of quality values
     */
    public List<String> getQuality(){
        return quality;
    }

    /**
     * Adds the Time
     * @param time the time to set
     */
    public void addTime(String time) {
        this.time.add(time);
    }

    /**
     * Adds the Temperature
     * @param temperature the temperature to set
     */
    public void addTemperature(String temperature) {
        this.temperature.add(temperature);
    }

    /**
     * Sets the Quality
     * @param quality the quality to set
     */
    public void addQuality(String quality) {
        this.quality.add(quality);
    }

    @Override
    public String toString(){
        return ", Time: " + time + ", Temperature: " + temperature + ", Quality: " + quality;
    }
}
