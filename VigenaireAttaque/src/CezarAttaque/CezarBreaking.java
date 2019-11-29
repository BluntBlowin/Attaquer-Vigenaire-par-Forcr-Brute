/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CezarAttaque;

import org.omg.CORBA.SystemException;

/**
 *
 * @author blunt
 */
public class CezarBreaking {
    
    public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public void trouverCleCesar(String texte){
	   double apparitionMax = 0;
	   char lettreLaPlusFrequente = 'A';
	   int n = texte.length();
	   for (char c : alphabet){
		   double apparition = 0;
		   for(int i=0; i<n; i++) {
			   if (texte.charAt(i)==c)
				   apparition++;
		   }
		   if (apparition > apparitionMax){
			   apparitionMax = apparition;
			   lettreLaPlusFrequente = c;
		   }
	   }
	   
	   // on part du principe que la lettre la plus fr�quente code le 'e'
	   int decalage = ChiffreCesar.getIndex(lettreLaPlusFrequente)-ChiffreCesar.getIndex('E'); //d�calage entre -4 et 21
	   decalage = (decalage + alphabet.length ) % (alphabet.length); //ram�ne le d�calage entre 0 et 25
	   char cle = ChiffreCesar.getLettre(decalage);
           
           ChiffreCesar CipherCezar=new ChiffreCesar();
	   System.out.println("Principe que la lettre la plus fréquente = 'E' :");
           System.out.println("Decalage = "+decalage+" : clef = "+cle);
           System.out.println("Text Probable :");
           System.out.println(CipherCezar.Dec(texte,decalage ));
           
           System.out.println("\n Autres résultats : \n");
           for(int i=0;i<26;i++){
               if(i!=decalage){
                   System.out.println("Decalage = "+i+" : clef = "+ChiffreCesar.getLettre(i));
                   System.out.println("Text Probable :");
                   System.out.println(CipherCezar.Dec(texte,i)+"\n");
                   
               }
           }
   }
}
