package vigenaireattaque.Dechiffreur;

import CezarAttaque.ChiffreCesar;
import java.util.List;
import java.util.ArrayList;

/**
 * Permet le chiffrement d'un texte par le chiffre de Vigen�re
 * � partir d'une cl� de type String.
 * 
 * @author Antoine Sachet
 * Created in Oct 2013
 */
public class ChiffreVigenere implements Chiffre {
   private String cle;
   
   /**
    * Un chiffre de Vigen�re est un cycle de chiffre de C�sar
    */
   private List<ChiffreCesar> chiffreCesar;
      
   /** 
    * Chiffre le texte selon la m�thode de Vigen�re avec la cl� retenue.
    * ON SUPPOSE LE TEXTE NE CONTENANT QUE DES LETTRES (cf Programme.assainir())
    * @param texte
    * @return texte chiffr�
    */
   public String chiffrer(String texte) {
      String texteChiffre = "";
      int lgCle = chiffreCesar.size();
	   for (int i=0; i<texte.length(); i++){
    	  char c = texte.charAt(i);
    	  char cChiffre = chiffreCesar.get(i%lgCle).chiffrer(c);
    	  texteChiffre += cChiffre;
      }
	   return texteChiffre;
   }
   
   /**
    * D�chiffre le texte avec la cl� retenue
    * 
    */
   public String dechiffrer(String texte) {
	   String texteDechiffre = "";
	      int lgCle = chiffreCesar.size();
	      for (int i=0; i<texte.length(); i++){
	    	  char c = texte.charAt(i);
	    	  char cDechiffre = chiffreCesar.get(i%lgCle).dechiffrer(c);
	    	  texteDechiffre += cDechiffre;
	      }
		  return texteDechiffre;
   }
   
   /**
    * Cr�e un nouveau chiffreVigen�re initialis� avec la cl� donn�e
    * @param cle
    *
    */
   public ChiffreVigenere (String cle) {
	   this.cle = cle;
	   this.chiffreCesar = new ArrayList<ChiffreCesar>();
	   for (char c : this.cle.toCharArray()){
    	  chiffreCesar.add(new ChiffreCesar(c));
      }
   }
   
   }
