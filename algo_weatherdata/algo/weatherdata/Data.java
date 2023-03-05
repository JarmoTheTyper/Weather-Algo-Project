package algo.weatherdata;


/**
 * Create an objekt of an isolated instance of whether data
 */
public class Data {
    private String date;
    private String time;
    private String temperature;
    private String quality;

    /**
     * Construct a data objekt
     * @param date The date of the whether data instance
     * @param time The time of the whether data instance
     * @param temperature The temperature of the whether data instance
     * @param quality The quality of the whether data instance
     */
    public Data(String date, String time, String temperature, String quality){
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.quality = quality;
    }

    /**
     *  Gets the Date
     * @return date of instance
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Gets the Time
     * @return time of instance
     */
    public String getTime(){
        return this.time;
    }

    /**
     * Gets the Temperature
     * @return temperature of instance
     */
    public String getTemperature(){
        return this.temperature;
    }

    /**
     * Gets the Quality
     * @return quality of instance
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Sets the Date
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the Time
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Sets the Temperature
     * @param temperature the temperature to set
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * Sets the Quality
     * @param quality the quality to set
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public String toString(){
        return "Date: " + date + ", Time: " + time + ", Temperature: " + temperature + ", Quality: " + quality;
    }
}
