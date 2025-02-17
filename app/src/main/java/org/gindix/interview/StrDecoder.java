
package org.gindix.interview;
import java.util.Iterator;

public class StrDecoder implements Iterator{

    private final String strToDecode ;
    //current iterator char index 
    private int currentCharIndex;
    //stores the repeat count of the characters to be repeated
    // we repeat the character until it gets 0
    private int repeatCount=0;


    public StrDecoder(String str){
        //Here below checks input string includes only numeric chars
        // we can extend here with fine-grained exceptions.
        //For the sake of the time I handled it only using assert
        assert(str.matches("^[0-9]+$"));
        this.strToDecode = str;
    }

    @Override
    public boolean hasNext() {
        return currentCharIndex < strToDecode.length();
    }

    @Override
    public Object next() {
        
        if (currentCharIndex%2 == 0){
            repeatCount = Character.getNumericValue(strToDecode.charAt(currentCharIndex));
            //below while block is to handle 0 character count inside the encoded string
            //skips until find a non-zero character count
             while (repeatCount == 0 ) {
                currentCharIndex = currentCharIndex+2; 
                if(currentCharIndex >= strToDecode.length())
                    break;
                repeatCount = Character.getNumericValue(strToDecode.charAt(currentCharIndex));
            }

            currentCharIndex++; 
        }
        if(currentCharIndex > strToDecode.length()){
            return "";
        }
        Character currentChar = strToDecode.charAt(currentCharIndex);
        repeatCount--;
        if (repeatCount <= 0)
            currentCharIndex++;
        // This implementation returns a string to simplify handling of empty strings and to return an empty string 
        // when all repeat counts are 0 (as seen in the 5th test). 
        // With additional effort, this could be adapted to return characters instead, potentially improving performance.
        return currentChar.toString();
    }

}