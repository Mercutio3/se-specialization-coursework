
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<myWords.length;i++){
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
            return false;
        }
        for(int i=0;i<myWords.length;i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        String[] shifted = new String[myWords.length];
        System.arraycopy(myWords,0,shifted,0,myWords.length);
        for(int i=0;i<out.length()-1;i++){
            String shift = out.wordAt(i+1);
            shifted[i] = shift;
        }
        // you lose the first word
        shifted[out.length()-1] = word;
        // TODO: Complete this method
        out = new WordGram(shifted,0,shifted.length);
        return out;
    }
    
    public int hashCode(){
        WordGram hashC = new WordGram(myWords,0,myWords.length);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<myWords.length;i++){
            sb.append(hashC.wordAt(i));
            sb.append(" ");
        }
        String sbString = sb.toString().trim();
        int hash = sbString.hashCode();
        return hash;
    }
}