import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for(Point currPt : s.getPoints()){
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double perim = getPerimeter(s);
        int points = getNumPoints(s);
        double avgLength = perim / points;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currentPt : s.getPoints()){
            double currentDist = prevPt.distance(currentPt);
            if(currentDist >= largestSide){
                largestSide = currentDist;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        for(Point currentPt : s.getPoints()){
            double bigX = currentPt.getX();
            if(bigX >= largestX){
                largestX = bigX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if(perim >= largestPerim){
                largestPerim = perim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File temp = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if(perim >= largestPerim){
                largestPerim = perim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double longSide = getLargestSide(s);
        double bigX = getLargestX(s);
        System.out.println("-----");
        System.out.println("Number of points: " + points);
        System.out.println("perimeter = " + length);
        System.out.println("Average length: " + avgLength);
        System.out.println("Longest side: " + longSide);
        System.out.println("Largest X: " + bigX);
    }
    
    public void testPerimeterMultipleFiles() {
        double bigPerim = getLargestPerimeterMultipleFiles();
        System.out.println("-----");
        System.out.println("Largest Perimeter of Multiple Files: " + bigPerim);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("-----");
        System.out.println("Filename with Largest Perimeter: " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
