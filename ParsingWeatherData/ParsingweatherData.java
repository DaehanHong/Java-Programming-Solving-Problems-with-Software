
/**
 * Write a description of ParsingweatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class ParsingweatherData {
    
    public static CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        
        for(CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        
        return smallestSoFar;
    }
    
    public static void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
	CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
	System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
		           " at " + smallest.get("TimeEDT"));
	}
    
    public static CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
	if (smallestSoFar == null) {
	    smallestSoFar = currentRow;
	}
	//Otherwise
	else {
	    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
	    double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
	    //Check if currentRow’s temperature > largestSoFar’s
	    if (currentTemp < smallestTemp && currentTemp != -9999) {
	        //If so update largestSoFar to currentRow
		smallestSoFar = currentRow;
            }
	}
	return smallestSoFar;
    }
    
    public static String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String filename="";
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parse = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourInFile(parse);
            coldestSoFar = getSmallestOfTwo(currentRecord, coldestSoFar);
            filename = f.getPath();
        }
        return filename;
    }
    
    public static void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
	FileResource fr = new FileResource(filename);
	CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
	System.out.println("Coldest day was in file "+ filename);
	System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
	System.out.println("All the Temperature on the coldest day were:");
	
	for(CSVRecord currentRow : fr.getCSVParser()) {
	    System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
	}
    }
    
    public static CSVRecord lowestHumidityInFile(CSVParser parser) {
        //start with lowestSoFar as nothing
	CSVRecord lowestSoFar = null;
	//For each row (currentRow) in the CSV File
	for (CSVRecord currentRow : parser) {
	    // use method to compare two records
	    lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
	}
	//The smallestSoFar is the answer
	return lowestSoFar;
    }
    
    public static CSVRecord getSmallestHumidityOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
	//If smallestSoFar is nothing
	if (smallestSoFar == null) {
	    smallestSoFar = currentRow;
	}
	//Otherwise
	else {
	    if (currentRow.get("Humidity").length() != 3){
	        double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
		double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
		//Check if currentRow’s temperature < smallestSoFar’s
		if (currentTemp < smallestTemp && currentTemp != -9999) {
		    //If so update smallestSoFar to currentRow
		    smallestSoFar = currentRow;
		}
            }
        }
	return smallestSoFar;
    }
    
    public static void testlowestHumidityInFile() {
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-06-29.csv");
	CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
	System.out.println("Lowest humidity was " + smallest.get("Humidity") +
		           " at " + smallest.get("DateUTC"));
    }
    
    public static CSVRecord lowestHumidityInManyFiles() {
	CSVRecord lowestSoFar = null;
	DirectoryResource dr = new DirectoryResource();
	// iterate over files
	for (File f : dr.selectedFiles()) {
	    FileResource fr = new FileResource(f);
	    // use method to get largest in file.
	    CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
	    // use method to compare two records
	    lowestSoFar = getSmallestHumidityOfTwo(currentRow, lowestSoFar);
	}
	//The largestSoFar is the answer
	return lowestSoFar;
    }
    
    public static void testLowestHumidityInManyFiles() {
	CSVRecord csv = lowestHumidityInManyFiles();
	System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public static double averageTemperatureInFile(CSVParser parser){
        double sum = 0;
        double average = 0;
        int count = 1;
        for(CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            average = sum/count;
            count++;
        }
        return average;
    }
    
    public static void testAverageTemperatureInFile(){
	FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	double avarage = averageTemperatureInFile(parser);
	System.out.println("Average temperature in file is " + avarage);
    }
    
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum=0;
	double average =0;
	int count = 1;
	for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentHumidity>=value){
                sum += currentTemp;
                average =sum/count;
                count++;
            }
        }
        return average;
    }
    
    public static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
	double avarage = averageTemperatureWithHighHumidityInFile(parser,80);
	if(avarage==0){
	    System.out.println("No temperatures with that humidity");
	}
	else{
	    System.out.println("Average temperature when high Humidity is " + avarage);
	}
    }
    
    public static void main(String[] args) {
        //testColdestHourInFile();
        //testFileWithColdestTempreature();
        //testlowestHumidityInFile();
        //testLowestHumidityInManyFiles();
        //testAverageTemperatureInFile();
        testAverageTemperatureWithHighHumidityInFile();
    }
}
