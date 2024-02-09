
/**
 * Write a description of CSVExports here.
 * 
 * @author Santiago Domínguez
 * @version September 1, 2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVExports {
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String infoLine = record.get("Country");
            if(infoLine.contains(country)){
                String exports = record.get("Exports");
                String money = record.get("Value (dollars)");
                String output = infoLine+": "+exports+": "+money;
                return output;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String infoLine = record.get("Exports");
            if(infoLine.contains(exportItem1) && infoLine.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String infoLine = record.get("Exports");
            if(infoLine.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int inputLength = amount.length();
        for(CSVRecord record : parser){
            String infoLine = record.get("Value (dollars)");
            if(infoLine.length() > inputLength){
                System.out.println(record.get("Country")+" "+infoLine);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser,"Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    
    public static void main(String[] args){
        CSVExports test = new CSVExports();
        test.tester();
    }
}
