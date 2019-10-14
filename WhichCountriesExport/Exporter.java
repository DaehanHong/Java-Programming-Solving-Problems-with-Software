
/**
 * Write a description of Export here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
    
    public static void countryInfo(CSVParser parser, String country) {      
        boolean found = false;
        //for each row in the CSV File
    for (CSVRecord record : parser) {
        //Look at the "Exports" column
        String getCountry = record.get("Country");
        //Check if it contains exportOfInterest
        if (getCountry.contains(country)) {
            //If so, write down the "Country" from that row
        String export = record.get("Exports");
        System.out.println(getCountry + " : " + export);
        found = true;
            }
    }
    if (!found) System.out.println("NOT FOUND");
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            String country = record.get("Country");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numOfCountries = 0;
        for(CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem)) {
                String country = record.get("Country");
        System.out.println(country);
                numOfCountries++;
            }
    }
    System.out.println("How many countries export " + exportItem + " : " + numOfCountries);
    return numOfCountries;
    }
    
    public static void bigExporters(CSVParser parser, String amount) {
        //for each row in the CSV File
    for (CSVRecord record : parser) {
        //Look at the "Exports" column
        String value = record.get("Value (dollars)");
        //Check if it contains exportOfInterest
        if (value.length() >("$"+amount).length() ) {
            //If so, write down the "Country" from that row
        String country = record.get("Country");
        System.out.println(country + " : " + value);

            }
    }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //countryInfo(parser, "Germany");
        //parser = fr.getCSVParser();
        
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //parser = fr.getCSVParser();
    
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //parser = fr.getCSVParser();
        
        //numberOfExporters(parser, "gold");
        //parser = fr.getCSVParser();
    
        //numberOfExporters(parser, "cocoa");
        //parser = fr.getCSVParser();
    
        //countryInfo(parser, "Nauru");
        //parser = fr.getCSVParser();
        
        bigExporters(parser, "$999,999,999,999");
        parser = fr.getCSVParser();
    }
}
