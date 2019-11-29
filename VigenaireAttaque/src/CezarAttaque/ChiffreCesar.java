package CezarAttaque;

import vigenaireattaque.Dechiffreur.Chiffre;

/**
 * Permet le chiffrement d'un texte par le chiffre de C�sar
 * � partir d'une cl� de type char.
 * 
 * @author Antoine Sachet
 * Created in Oct 2013
 */
public class ChiffreCesar implements Chiffre {
	
	/** cle est un INDEX ENTRE O ET alphabet.length()-1 = 25+5 */
	private int cle;
	static public String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
   /** Renvoie l'INDEX dans l'alphabet (entre 0 et 25) du caract�re c.
    * @param caract�re
    * @return l'index dans l'alphabet du caract�re 
    */
   public static int getIndex(char c) {
		  return alphabet.indexOf(c);
   }
   
   /** Pour passer de l'index � la lettre
    * @param l'index dans l'alphabet du caract�re
    * @return  le caract�re
    */
   public static char getLettre(int index) {
		  return alphabet.charAt(index);
   }

    public ChiffreCesar() {
    }
   
   
   /** Chiffre 'texte' avec la cl� retenue
    * @param texte : texte � chiffrer
    * @return texte chiffr�
    */
   public String chiffrer(String texte) {
	   String texteChiffre = "" ;
	   for (int i=0 ; i<texte.length(); i++) {
		   texteChiffre += chiffrer(texte.charAt(i));
	   }
	   return texteChiffre;
   }
   public String Encrypt(String texte,int clé) {
           this.cle=clé;
	   String texteChiffre = "" ;
	   for (int i=0 ; i<texte.length(); i++) {
		   texteChiffre += chiffrer(texte.charAt(i));
	   }
	   return texteChiffre;
   }
   /**
    * Renvoie le caract�re en entr�e chiffrer avec la cl� retenue
    */
   public char chiffrer(char c) {
		   int indexChiffre = (getIndex(c) + this.cle) % alphabet.length();
		   return getLettre(indexChiffre);
   }
   
   /** Dechiffre un texte chiffr� avec la cl� retenue
    * @param texte chiffr�
    * @return texte dechiffr�
    */
   public String dechiffrer(String texte) {
	   return new ChiffreCesar(getLettre((alphabet.length() - cle)%alphabet.length())).chiffrer(texte);
   }
  
   public String Dec(String texte,int clef) {
           this.cle=clef;
	   return new ChiffreCesar(getLettre((alphabet.length() - cle)%alphabet.length())).chiffrer(texte);
   }
   public char dechiffrer(char c) {
	   int indexDechiffre = (getIndex(c)  + alphabet.length() - this.cle) % alphabet.length();
	   return getLettre(indexDechiffre);
   }
   
   public ChiffreCesar (char c) {
	   this.cle = alphabet.indexOf(c);
   }
   
   }
