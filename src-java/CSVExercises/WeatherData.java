
/**
 * Write a description of WeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherData {
    public String fileWithColdestTemperature(String whichColumn){
        DirectoryResource dr = new DirectoryResource();
        String pass2 = "N/A";
        CSVRecord lowestTemp = null;
        String smallTempFile = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestXInFile(fr.getCSVParser(),whichColumn);
            if(lowestTemp == null){
                lowestTemp = current;
                smallTempFile = f.getName();
            } else {
                String pass = current.get(whichColumn);
                if(pass != pass2){
                    double lowTemp = Double.parseDouble(lowestTemp.get(whichColumn));
                    double currentTemp = Double.parseDouble(current.get(whichColumn));
                    if(currentTemp < lowTemp && currentTemp != -9999){
                         lowestTemp = current;
                         smallTempFile = f.getName();
                    }
                }
            }
        }
        return smallTempFile;
    }
    public CSVRecord coldestXInFile(CSVParser parser,String column){
        CSVRecord smallestTemp = null;
        String check2 = "N/A";
        for(CSVRecord currRow : parser){
            if(smallestTemp == null){
                smallestTemp = currRow;
            } else {
                String check = currRow.get(column);
                if(check.equals(check2)){
                    System.out.println("N/A detected. Skipping.");
                } else {
                    double smallTemp = Double.parseDouble(smallestTemp.get(column));
                    double currTemp = Double.parseDouble(currRow.get(column));
                    if(currTemp < smallTemp){
                         smallestTemp = currRow;
                    }
                }
            }
        }
        return smallestTemp;
    }
    public void printAll(CSVParser parser,String printWhat){
        for(CSVRecord currRow : parser){
            System.out.println(currRow.get("DateUTC")+": "+currRow.get(printWhat));
        }
    }
    public double averageTemps(CSVParser parser){
        double count = 0.0;
        double total = 0.0;
        double avg = 0.0;
        for(CSVRecord currRow : parser){
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            count++;
            total += currTemp;
        }
        avg = total / count;
        return avg;
    }
    public double averageTempsHumidity(CSVParser parser, int value){
        double count = 0.0;
        double total = 0.0;
        double avg = 0.0;
        for(CSVRecord currRow : parser){
            int intHumidity = Integer.parseInt(currRow.get("Humidity"));
            if(intHumidity > value){
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                count++;
                total += currTemp;
            }
        }
        if(count == 0.0){
            System.out.println("No temperatures with that humidity");
        } else {
            avg = total / count;
        }
        return avg;
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord output = coldestXInFile(fr.getCSVParser(),"TemperatureF");
        System.out.println("Coldest temp: "+output.get("TemperatureF"));
        System.out.println("Average temperature: "+averageTemps(fr.getCSVParser()));
        System.out.println("Average temp with high humidity is "+averageTempsHumidity(fr.getCSVParser(),80));
    }
    public void testFileWithColdestTemperature(String whichThing){
        String smallFile = fileWithColdestTemperature(whichThing);
        System.out.println("Coldest/Lowest day was in file "+smallFile);
        FileResource fr = new FileResource("/Users/santiagodominguezham/Desktop/Java/Coursera/CSVExercises/nc_weather/2013/"+smallFile);
        CSVRecord cold = coldestXInFile(fr.getCSVParser(),whichThing);
        System.out.println("Coldest/Lowest on that day was "+cold.get(whichThing));
        printAll(fr.getCSVParser(),whichThing);
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord output = coldestXInFile(fr.getCSVParser(),"Humidity");
        System.out.println("Lowest humidity: "+output.get("Humidity")+" at "+output.get("DateUTC"));
    }
    public static void main(String[] args){
        WeatherData test = new WeatherData();
        //test.testColdestHourInFile();
        //test.testLowestHumidityInFile();
        test.testFileWithColdestTemperature("TemperatureF");
        //test.testFileWithColdestTemperature("Humidity");
    }
}
