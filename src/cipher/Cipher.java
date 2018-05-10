/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

import com.sun.xml.internal.ws.message.stream.StreamAttachment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.security.krb5.EncryptionKey;

/**
 *
 * @author Kalana
 */
public class Cipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text","txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
        }

        System.out.println(chooser.getSelectedFile().getAbsolutePath());
//        FileType fileType = new FileType(chooser.getSelectedFile().getAbsolutePath());
BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
try {
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
String everything = "";
    while (line != null) {
//        sb.append(line);
//        sb.append(System.lineSeparator());
everything+=line.toLowerCase();
        line = br.readLine();
        
    }
    br.close();
//    String everything = sb.toString();
    System.out.println(everything);
    
//    String s = everything;
//  byte[] bytes = s.getBytes();
//  StringBuilder binary = new StringBuilder();
//  for (byte b : bytes)
//  {
//     int val = b;
//     for (int i = 0; i < 8; i++)
//     {
//        binary.append((val & 128) == 0 ? 0 : 1);
//        val <<= 1;
//     }
////     binary.append(' ');
//  }
//  System.out.println("'" + s + "' to binary: " + binary);
//    BigInteger val = new BigInteger(binary.toString(), 2);
//    String hi = new String(val.toByteArray());
//    System.out.println(hi);

String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String encrypionKey = "";
 
        Random ran = new Random();
        int alphabetIterator1 = ran.nextInt(7)+2;
 
        String f1= "";
        for(int i=0; i<alphabetIterator1;i++){
            f1+=alphabet.charAt(i);
        }
        String f2="";
        for(int i=alphabetIterator1;i<alphabet.length();i++){
            f2+=alphabet.charAt(i);
        }
        
        String cipherAlphabet1 = f2+f1;
        
        int alphabetIterator2 = ran.nextInt(7)+2;
        while(alphabetIterator1 == alphabetIterator2){
            alphabetIterator2 = ran.nextInt(7)+2;
        }
        
        f1= "";
        for(int i=0; i<alphabetIterator2;i++){
            f1+=alphabet.charAt(i);
        }
        f2="";
        for(int i=alphabetIterator2;i<alphabet.length();i++){
            f2+=alphabet.charAt(i);
        }
        
        String cipherAlphabet2 = f2+f1;
 
 ArrayList<Character> originalAlphabet = new ArrayList<>();
 ArrayList<Character> newAlphabet = new ArrayList<>();
 ArrayList<Character> newAlphabet1 = new ArrayList<>();
 ArrayList<Character> newAlphabet2 = new ArrayList<>();
 
 
 for(int i=0; i<alphabet.length();i++){
     originalAlphabet.add(alphabet.charAt(i));
//     newAlphabet.add(alphabet.charAt(i));
     newAlphabet1.add(cipherAlphabet1.charAt(i));
     newAlphabet2.add(cipherAlphabet2.charAt(i));
 }
 
// Collections.shuffle(newAlphabet);
 encrypionKey+=alphabetIterator1;
 encrypionKey+=alphabetIterator2;
// for(int i=0; i<newAlphabet.size(); i++){
//    encrypionKey += newAlphabet.get(i); 
// }
 String newText = "";
 for(int i=0; i<everything.length();i++){
     if((i%2)==0){
        for(int j=0; j<newAlphabet1.size();j++){
         if(everything.charAt(i) == originalAlphabet.get(j)){
            newText += newAlphabet1.get(j);
            break;
          }
         else if(j == 25){
             newText += everything.charAt(i);
         }
     } 
     }else{
         for(int j=0; j<newAlphabet2.size();j++){
         if(everything.charAt(i) == originalAlphabet.get(j)){
            newText += newAlphabet2.get(j);
            break;
          }
         else if(j == 25){
             newText += everything.charAt(i);
         }
     }
     }
     
 }
 
    Random random = new Random();
    int x= random.nextInt(2);
    int iterateBlockSize = x+8;
    
    int lessCharacterNumber = iterateBlockSize - newText.length()%iterateBlockSize;
    
    for(int i=0; i<lessCharacterNumber;i++){
        newText += " ";
    }
    
    ArrayList<String> iterateBollck = new ArrayList<>();
    
    String tempString="";
    int count = 0;
    for(int i=0;i<newText.length();i++){
        count+=1;
        tempString+=newText.charAt(i);
        if(count == iterateBlockSize){
            iterateBollck.add(tempString);
            tempString = "";
            count = 0;
        }
    }

//    System.out.println(iterateBollck);
    ArrayList<Integer> tempIterate = new ArrayList<>();
    for(int i=0; i<iterateBlockSize;i++){
        tempIterate.add(i);
    }
//    System.out.println(tempIterate);  
    Collections.shuffle(tempIterate);
    
    for(int i=0; i<tempIterate.size();i++){
        encrypionKey+=tempIterate.get(i);
    }
    String encryptText = "";
    
    for(int i=0; i<iterateBollck.size();i++){
        for(int j=0; j<tempIterate.size();j++){
            encryptText+= (iterateBollck.get(i).charAt(tempIterate.get(j)));
//            System.out.println(iterateBollck.get(i).charAt(tempIterate.get(j)));
        }
//        System.out.println(encryptText);
    }
    
    System.out.println("##########################");
    System.out.println(encryptText);
    System.out.println(encrypionKey);
    
//    ###########Decrytion###################
String newFile= encryptText;
int deccryptIteratorLength = encrypionKey.length() - 2;
ArrayList<Integer> decryptIterationArray = new ArrayList<>();

for(int i=2; i<encrypionKey.length();i++){
    decryptIterationArray.add(Character.getNumericValue(encrypionKey.charAt(i)));
}

ArrayList<Integer> substitutionArray = new ArrayList<>();

for(int i=0; i<2;i++){
    substitutionArray.add(Character.getNumericValue(encrypionKey.charAt(i)));
}

//String decryptionCipherAlphabet1 = "";
//String decryptionCipherAlphabet2 = "";
System.out.println(substitutionArray.get(0));
String f3= "";
        for(int i=0; i<substitutionArray.get(0);i++){
            f3+=alphabet.charAt(i);
        }
        String f4="";
        for(int i=substitutionArray.get(0);i<alphabet.length();i++){
            f4+=alphabet.charAt(i);
        }
        
        String decryptionCipherAlphabet1 = f4+f3;
        
f3 ="";
for(int i=0; i<substitutionArray.get(1);i++){
            f3+=alphabet.charAt(i);
        }
        f4="";
        for(int i=substitutionArray.get(1);i<alphabet.length();i++){
            f4+=alphabet.charAt(i);
        }
        
        String decryptionCipherAlphabet2 = f4+f3;

    System.out.println(decryptIterationArray);
ArrayList<String> decryptIterator = new ArrayList<>();

String tempString2 = "";
int count2 = 0;

for(int i =0; i<newFile.length();i++){
    count2+=1;
    tempString2+=newFile.charAt(i);
    if(count2 == deccryptIteratorLength){
        decryptIterator.add(tempString2);
        count2= 0;
        tempString2="";
    }
}
    System.out.println(decryptIterator);
String decryptedText = "";
for(int i=0; i<decryptIterator.size();i++){
    String tempString3 = decryptIterator.get(i);
    char[] tempCharArray = tempString3.toCharArray();
//    System.out.println(tempCharArray);
    for(int j=0; j<deccryptIteratorLength;j++){
        tempCharArray[decryptIterationArray.get(j)] = tempString3.charAt(j);
    }
    for(int k=0;k<tempCharArray.length;k++){
        decryptedText+=tempCharArray[k];
    }
}
    System.out.println(decryptedText);
    System.out.println(decryptionCipherAlphabet1);
    System.out.println(decryptionCipherAlphabet2);
String finalDecryptText= "";

for(int i=0; i<decryptedText.length();i++){
    if((i%2)==0){
        for(int j=0;j<26;j++){
            if(decryptedText.charAt(i)==decryptionCipherAlphabet1.charAt(j)){
                finalDecryptText+=alphabet.charAt(j);
                break;
            }
            else if(j==25){
                finalDecryptText+=decryptedText.charAt(i);
            }
        }
    }else{
        for(int k=0;k<26;k++){
            if(decryptedText.charAt(i)==decryptionCipherAlphabet2.charAt(k)){
                finalDecryptText+=alphabet.charAt(k);
                break;
            }
            else if(k==25){
                finalDecryptText+=decryptedText.charAt(i);
            }
        }
    }
}
////    System.out.println(decryptedText);
////String finalDecryptText = "";
////for(int i=0; i<decryptedText.length();i++){
////    for(int j=0;j<26;j++){
////        if(decryptedText.charAt(i)==encrypionKey.charAt(j)){
////            finalDecryptText+= alphabet.charAt(j);
////            break;
////        }
////        else if(j==25){
////            finalDecryptText+= decryptedText.charAt(i);
////        }
////    }
////}
    System.out.println(finalDecryptText);    
} finally {
    
}

        
    }
    
}
