
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines()){
            LogEntry fromLine = WebLogParser.parseEntry(line);
            records.add(fromLine);
        }
    }
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            String ipAddress = le.getIpAddress();
            if(!uniqueIPs.contains(ipAddress)){
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }
    public void printAllHigherThanNum(int num){
        for(LogEntry le : records){
            int statCode = le.getStatusCode();
            if(statCode > num){
                System.out.println(le);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> onDay = new ArrayList<String>();
        for(LogEntry le : records){
            Date d = le.getAccessTime();
            String strDate = d.toString();
            int monthDay = strDate.indexOf(someday);
            if(monthDay != -1){
                String ipAddress = le.getIpAddress();
                if(!onDay.contains(ipAddress)){
                    onDay.add(ipAddress);
                }
            }
        }
        return onDay;
    }
    public int countUniqueIPsInRange(int low,int high){
        int count = 0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            int statCode = le.getStatusCode();
            if(statCode>=low && statCode<=high){
                String ipAddress = le.getIpAddress();
                if(!uniqueIPs.contains(ipAddress)){
                    uniqueIPs.add(ipAddress);
                    count++;
                }
            }
        }
        return count;
    }
    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            } else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsPerIP(HashMap<String,Integer> map){
        int largest = 0;
        for(String key : map.keySet()){
            int value = map.get(key);
            if(value > largest){
                largest = value;
            }
        }
        return largest;
    }
    public ArrayList<String> ipMostVisits(HashMap<String,Integer> map){
        ArrayList<String> withMax = new ArrayList<String>();
        int max = mostNumberVisitsPerIP(map);
        for(String key : map.keySet()){
            int temp = map.get(key);
            if(temp==max && !withMax.contains(key)){
                withMax.add(key);
            }
        }
        return withMax;
    }
    public HashMap<String,ArrayList<String>> ipsForDays(){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for(LogEntry le : records){
            ArrayList<String> tempList = new ArrayList<String>();
            String date = le.getAccessTime().toString().substring(4,10);
            String ip = le.getIpAddress();
            if(!map.containsKey(date)){
                tempList.add(ip);
                map.put(date,tempList);
            } else {
                tempList = map.get(date);
                tempList.add(ip);
                map.put(date,tempList);
            }
        }
        return map;
    }
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        String dayMost = "";
        int bigMoist = 0;
        for(String key : map.keySet()){
            ArrayList<String> tempArray = map.get(key);
            int tempSize = tempArray.size();
            if(tempSize>bigMoist){
                bigMoist = tempSize;
                dayMost = key;
            }
        }
        return dayMost;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> list,String day){
        ArrayList<String> dayVisits = list.get(day);
        HashMap<String,Integer> ipCounts = new HashMap<String,Integer>();
        for(String ip : dayVisits){
            if(!ipCounts.containsKey(ip)){
                ipCounts.put(ip,1);
            } else {
                ipCounts.put(ip,ipCounts.get(ip)+1);
            }
        }
        return ipMostVisits(ipCounts);
    }
}
