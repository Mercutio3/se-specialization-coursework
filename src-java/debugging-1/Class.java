
/**
 * Part of a Coursera practice quiz. This code had to be debugged.
 * 
 * @author Coursera staff, Santiago Domínguez
 * @version August 30, 2020.
 */
public class Class {
    public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
       }
   }

   public void test(){
       //findAbc("abcd");
       findAbc("abcabcabcabca");
   }
}
