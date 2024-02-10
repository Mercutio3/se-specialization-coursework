
/**
 * Part of a Coursera quiz. See part1 for more info.
 * 
 * @author Coursera staff.
 * @version August 30, 2020.
 */
public class Test {
    public static void main(String[] args){
        Test test = new Test();
        System.out.println(test.mystery("ATGTTTAAAT"));
    }
    public String mystery(String dna) {
  int pos = dna.indexOf("T");
  int count = 0;
  int startPos = 0;
  String newDna = "";
  if (pos == -1) {
    return dna;
  }
  while (count < 3) {
    count ++;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
  }
  newDna = newDna + dna.substring(startPos);
  return newDna;
}
}
