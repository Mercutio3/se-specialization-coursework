
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer test = new LogAnalyzer();
        test.readFile("/Users/santiagodominguezham/Desktop/Java/Coursera/WebLogProgram/short-test_log");
        test.printAll();
    }
    
    public void testUniqueIPs(){
        LogAnalyzer testIP = new LogAnalyzer();
        testIP.readFile("/Users/santiagodominguezham/Desktop/Java/Coursera/WebLogProgram/weblog2_log");
        System.out.println("Number of unique IPs: "+testIP.countUniqueIPs());
        testIP.printAllHigherThanNum(400);
        System.out.println("Number of IPs in range: "+testIP.countUniqueIPsInRange(400,499));
    }
    public void testOnDay(){
        LogAnalyzer testDay = new LogAnalyzer();
        testDay.readFile("/Users/santiagodominguezham/Desktop/Java/Coursera/WebLogProgram/weblog2_log");
        ArrayList<String> onDay = testDay.uniqueIPVisitsOnDay("Sep 24");
        for(int i=0;i<onDay.size();i++){
            System.out.println("Visit on day: "+onDay.get(i));
        }
    }
    public void testVisits(){
        LogAnalyzer testVisit = new LogAnalyzer();
        testVisit.readFile("/Users/santiagodominguezham/Desktop/Java/Coursera/WebLogProgram/weblog2_log");
        HashMap<String,Integer> counts = testVisit.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("Largest number: "+testVisit.mostNumberVisitsPerIP(counts));
        ArrayList<String> ipsMax = testVisit.ipMostVisits(counts);
        System.out.println(ipsMax);
        HashMap<String,ArrayList<String>> days = testVisit.ipsForDays();
        System.out.println(days);
        System.out.println("Day with most: "+testVisit.dayWithMostIPVisits(days));
        ArrayList<String> ipsMostVisitsDay = testVisit.iPsWithMostVisitsOnDay(days,"Sep 30");
        System.out.println(ipsMostVisitsDay);
    }
}
