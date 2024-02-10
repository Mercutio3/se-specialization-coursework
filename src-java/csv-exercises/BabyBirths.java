
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord currRow : fr.getCSVParser(false)){
            System.out.println("Name "+currRow.get(0)+" Gender "+currRow.get(1)+" NumBorn "+currRow.get(2));
        }
    }
    public void totalBirths(FileResource fr){
        int totalBirths = 0, nameCount = 0;
        int totalMales = 0, maleCount = 0;
        int totalFemales = 0, femaleCount = 0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(currRow.get(2));
            totalBirths += numBorn;
            nameCount += 1;
            if(currRow.get(1).equals("M")){
                totalMales += numBorn;
                maleCount += 1;
            } else {
                totalFemales += numBorn;
                femaleCount += 1;
            }
        }
        System.out.println("Total births: "+totalBirths+", of which "+totalMales+" are males and "+totalFemales+" are female.");
        System.out.println("Total names: "+nameCount+", of which "+maleCount+" are males and "+femaleCount+" are female.");
    }
    public int getRank(int year,String name,String gender){
        String yearFile = Integer.toString(year);
        FileResource fr = new FileResource("/Users/santiagodominguezham/Desktop/Java/Coursera/CSVExercises/testing/yob"+yearFile+".csv");
        int rank = 0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            if(currRow.get(1).equals(gender)){
                rank += 1;
                if(currRow.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    public String getName(int year,int rank,String gender){
        String yearFile = Integer.toString(year);
        FileResource fr = new FileResource("/Users/santiagodominguezham/Desktop/Java/Coursera/CSVExercises/testing/yob"+yearFile+".csv");
        int readRank = 0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            if(currRow.get(1).equals(gender)){
                readRank += 1;
                if(readRank == rank){
                    return currRow.get(0);
                }
            }
        }
        return "NO NAME";
    }
    public void whatNameInYear(String name,int year,int newYear,String gender){
        int nameRank = getRank(year,name,gender);
        String newName = getName(newYear,nameRank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" in "+newYear);
    }
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0, highestYear = 0;
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            String fileYear = fileName.substring(3,7);
            int intYear = Integer.parseInt(fileYear);
            int currRank = getRank(intYear,name,gender);
            if(highestRank == 0){
                highestRank = currRank;
                highestYear = intYear;
            } else if(currRank < highestRank){
                highestRank = currRank;
                highestYear = intYear;
            }
        }
        if(highestRank == -1){
            return -1;
        } else {
            return highestYear;
        }
    }
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0, count = 0;
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            String fileYear = fileName.substring(3,7);
            int intYear = Integer.parseInt(fileYear);
            int currRank = getRank(intYear,name,gender);
            if(currRank != -1){
                count++;
                totalRank += currRank;
            }
        }
        System.out.println(count+" "+totalRank);
        if(totalRank == 0 || count == 0){
            return -1.0;
        } else {
            double avg = (double)totalRank/count;
            return avg;
        }
    }
    public int totalBirthsRankedHigher(int year,String name,String gender){
        String yearFile = Integer.toString(year);
        FileResource fr = new FileResource("/Users/santiagodominguezham/Desktop/Java/Coursera/CSVExercises/testing/yob"+yearFile+".csv");
        int higherBirths = 0;
        for(CSVRecord currRow : fr.getCSVParser(false)){
            if(currRow.get(1).equals(gender)){
                if(currRow.get(0).equals(name)){
                    return higherBirths;
                } else {
                    higherBirths += Integer.parseInt(currRow.get(2));
                }
            }
        }
        return higherBirths;
    }
    public static void main(String[] args){
        BabyBirths test = new BabyBirths();
        //FileResource nameFile = new FileResource();
        //test.totalBirths(nameFile);
        //System.out.println("Rank of name Frank in year 1971: "+test.getRank(1971,"Frank","M"));
        //System.out.println("Name of male Rank 450 in year 1982: "+test.getName(1982,450,"M"));
        //test.whatNameInYear("Owen",1974,2014,"M");
        //System.out.println("Year of highest rank for name Mich: "+test.yearOfHighestRank("Mich","M"));
        //System.out.println("Average rank for Robert: "+test.getAverageRank("Robert","M"));
        System.out.println("Births higher than Drew for 1990: "+test.totalBirthsRankedHigher(1990,"Drew","M"));
    }
}
