import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by John on 3/1/2017.
 */
public class RNAtranslator {

  private static HashMap<String, String> aminoMap = new HashMap<>();
  private static HashMap<String, String> codonMap = new HashMap<>();

  public RNAtranslator()
  {
    buildAminoMap(aminoMap);
    buildCodonMap(codonMap);
  }

  private void buildAminoMap(HashMap map) {

    map.put("A","alanine");
    map.put("R","arginine");
    map.put("N","asparagine");
    map.put("D","asparticAcid");
    map.put("C","cysteine");
    map.put("Q","glutamine");
    map.put("E","glutamicAcid");
    map.put("G","glycine");
    map.put("H","histidine");
    map.put("I","isoleucine");
    map.put("L","leucine");
    map.put("K","lysine");
    map.put("M","methionine");
    map.put("F","phenylalanine");
    map.put("P","proline");
    map.put("S","serine");
    map.put("T","threonine");
    map.put("W","tryptophan");
    map.put("Y","tyrosine");
    map.put("V","valine");
    map.put("X","stop");
  }

  private void buildCodonMap(HashMap map){

    map.put("UUU","F"); map.put("UUC","F"); map.put("UUA","L"); map.put("UUG","L");
    map.put("UCU","S"); map.put("UCC","S"); map.put("UCA","S"); map.put("UCG","S");
    map.put("UAU","Y"); map.put("UAC","Y"); map.put("UAA","X"); map.put("UAG","X");
    map.put("UGU","C"); map.put("UGC","C"); map.put("UGA","X"); map.put("UGG","W");

    map.put("CUU","L"); map.put("CUC","L"); map.put("CUA","L"); map.put("CUG","L");
    map.put("CCU","P"); map.put("CCC","P"); map.put("CCA","P"); map.put("CCG","P");
    map.put("CAU","H"); map.put("CAC","H"); map.put("CAA","Q"); map.put("CAG","Q");
    map.put("CGU","R"); map.put("CGC","R"); map.put("CGA","R"); map.put("CGG","R");

    map.put("AUU","I"); map.put("AUC","I"); map.put("AUA","I"); map.put("AUG","M");
    map.put("ACU","T"); map.put("ACC","T"); map.put("ACA","T"); map.put("ACG","T");
    map.put("AAU","N"); map.put("AAC","N"); map.put("AAA","K"); map.put("AAG","K");
    map.put("AGU","S"); map.put("AGC","S"); map.put("AGA","R"); map.put("AGG","R");

    map.put("GUU","V"); map.put("GUC","V"); map.put("GUA","V"); map.put("GUG","V");
    map.put("GCU","A"); map.put("GCC","A"); map.put("GCA","A"); map.put("GCG","A");
    map.put("GAU","D"); map.put("GAC","D"); map.put("GAA","E"); map.put("GAG","E");
    map.put("GGU","G"); map.put("GGC","G"); map.put("GGA","G"); map.put("GGG","G");
  }

  private ArrayList<String> createCodonList(String input)
  {
    ArrayList<String> codonList = new ArrayList<>();
    int inputLength = input.length();
    char[] bases = new char[inputLength];
    input.getChars(0,inputLength,bases,0);
    for(int i=0;i<inputLength;i++)  //convert thymine to uracil
    {
      if(bases[i]=='t'){
        bases[i] = 'u';
      }
    }
    for(int i=0;i<inputLength-2;i+=3) //separate codons
    {
      String nxtCodon = "" + bases[i] + bases[i+1] + bases[i+2];
      codonList.add(nxtCodon);
    }
    return codonList;
  }

  private void translateCodons(ArrayList<String> codons)
  {
    for(int i=0;i<codons.size();i++)
    {
      String nxtCodon = codons.get(i).toUpperCase();
      String nxtAmino = codonMap.get(nxtCodon);
      System.out.print(nxtAmino);
    }
  }

  public static void main(String[] args)
  {
    RNAtranslator rnaTranslator = new RNAtranslator();
    String sequence ="";
    String input;
    Scanner scanner = new Scanner(new BufferedInputStream(System.in));
    System.out.println("Enter RNA sequence...");
    while(scanner.hasNext()){
      input = scanner.nextLine();
      if(input.compareTo("done")==0)
      {
        break;
      }else {
        sequence = sequence.concat(input);
      }
    }
    scanner.close();
    sequence = sequence.replaceAll("\\s","");
    ArrayList codonList = rnaTranslator.createCodonList(sequence);
    rnaTranslator.translateCodons(codonList);
  }
}




