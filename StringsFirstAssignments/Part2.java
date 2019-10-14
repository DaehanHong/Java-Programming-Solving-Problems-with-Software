
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        if(Character.isUpperCase(dna.charAt(0))) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();            
        }
        
        int start = dna.indexOf(startCodon);
        if(start == -1) {
            return "";
        }
        
        int stop = dna.indexOf(stopCodon, start);
        if(stop == -1) {
            return "";
        }
        
        // If the Gene is valid - divisible by 3
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        } 
    }
    
    public void testSimpleGene() {
        String a = "CCATCAATAACATGA";
        String a1 = "CCAATGCAGCGATAC";
        String a2 = "CTAATCCGGATCCGA";
        String a3 = "ccagcatgccagtcagctaacag";
        String a4 = "CCAGCATGCCAGTAGCTAACAG";
        
        System.out.println("The string is: " + a + ". The Gene is: " + findSimpleGene(a, "ATG", "TAA"));
        System.out.println("The string is: " + a1 + ". The Gene is: " + findSimpleGene(a1, "ATG", "TAA"));
        System.out.println("The string is: " + a2 + ". The Gene is: " + findSimpleGene(a2, "ATG", "TAA"));
        System.out.println("The string is: " + a3 + ". The Gene is: " + findSimpleGene(a3, "ATG", "TAA"));
        System.out.println("The string is: " + a4 + ". The Gene is: " + findSimpleGene(a4, "ATG", "TAA"));
    }
    
    public static void main (String[] args) {
        Part2 gene = new Part2();
        gene.testSimpleGene();
    }
}
