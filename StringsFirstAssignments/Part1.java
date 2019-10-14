
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        if(start == 1) {
            return "";
        }
        int stop = dna.indexOf("TTA", start);
        if(stop == -1) {
            return "";
        }
        
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
		String a1 = "CCAATGCAGCGATAC";
		String a2 = "CTAATCCGGATCCGA";
		String a3 = "CCAGCATGCCAGTCAGCTAACAG";
		String a4 = "CCAGCATGCCAGTAGCTAACAG";
		
		System.out.println("The string is: " + a + ". The Gene is: " + findSimpleGene(a));
		System.out.println("The string is: " + a1 + ". The Gene is: " + findSimpleGene(a1));
		System.out.println("The string is: " + a2 + ". The Gene is: " + findSimpleGene(a2));
		System.out.println("The string is: " + a3 + ". The Gene is: " + findSimpleGene(a3));
		System.out.println("The string is: " + a4 + ". The Gene is: " + findSimpleGene(a4));
    }
    
    public static void main (String[] args) {
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }

}
