package vigenaireattaque.Dechiffreur;

import CezarAttaque.ChiffreCesar;
import java.util.ArrayList;
import java.util.List;

/** 
 *  Permet de d�chiffrer un texte chiffr� (par C�sar ou Vigen�re)
 *  sans en connaitre la cl�.
 * 
 *  @author Antoine Sachet
 *  Created in Oct 2013
 */
public class Dechiffreur {
	
	public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	/*
	 * Longueur � partir de laquelle on arr�te de chercher la cl�
	 * (�vite une boucle infinie si on a d�pass� la conne longueur)
	 */
	public static int LONGUEUR_CLE_MAX = 100;
   
	/* 
	 * Valeur des indices de coicidence pour un texte anglais, fran�ais, chiffr� (r�partition uniforme)
	 */
	public static double IC_ANGLAIS = 1.73;
	public static double IC_FRANCAIS = 2.02;
	public static double IC_UNIFORME = 1.0;
	
	public String trouverCle(String texte) throws Exception {
	   String cle = "";
		
		int lgCle = trouverLongueurCle(texte);
		List<String> sousTextes = parser(texte, lgCle);
                
		for (String s : sousTextes){
			cle += trouverCleCesar(s);
		}
	
		return cle;
		
   }
   
   /** Calcule la longueur de cl� la plus probable
    *  jusqu'� une longueur maximale de 20 caract�res
    * @param texteChiffre
    * @return longueur probable de la cl� utilis�e pour chiffrer l'entr�e
 * @throws Exception si la longueur de la cl� n'a pas �t� trouv�.
    */
   public int trouverLongueurCle(String texteChiffre) throws Exception{
	   int lgCle = 1;
	   Boolean found = false;
	   while(!found && lgCle<LONGUEUR_CLE_MAX) {
		   
		   // cr�ation des sous-textes en prenant une lettre tout les lgCle caract�res
		   List<String> sousTexte = parser(texteChiffre, lgCle);
		   
		   // calcul de la moyenne de l'indice de coincidence pour cette lg de cl�
		   double moyIC = 0; 
		   for (String s : sousTexte){
			   moyIC += getIndiceDeCoincidence(s);
		   }
		   moyIC = moyIC / lgCle;
		   
		   //System.out.println("Pour la lg de cl� "+lgCle+ ", on calcule un IC moyen de : " +moyIC);
		   if (Math.abs(moyIC-IC_UNIFORME) > 0.5)
			   return lgCle;
		   
		   lgCle++;
	   }
	   
	   throw new Exception ("Impossible de trouver la longueur de la cl� (recherche des clés < "+LONGUEUR_CLE_MAX+").");
   }
   
   /**
    * D�coupe le texte d'entr�e en n sous-textes 
    * (un caract�re pris tous les n caract�res)
    * @param texte
    * @param n
    * @return liste des sous-textes
    */
   private List<String> parser(String texte, int n){
	   
	   List<StringBuilder> l = new ArrayList<StringBuilder>();
	   for (int i=0; i<n; i++){
		   l.add(new StringBuilder());
	   }
	   
	   int lgTexte=texte.length();
	   for (int i=0; i<lgTexte; i++){
		   l.get(i%n).append(texte.charAt(i));
	   }
	   
	   List<String> subText = new ArrayList<String>();
	   for (int i=0; i<n; i++){
		   subText.add(l.get(i).toString());
	   }
	   return subText;
   }
   
   private String dechiffrer(String texte, String cle) {
	 return new ChiffreVigenere(cle).dechiffrer(texte);
   }
   
   /** 
    * D�chiffre un texte chiffr� par la m�thode de Vigen�re
    * @param texte
    * @return texte d�chiffr�
    */
   public String dechiffrer(String texte) {
	   String cle = "";
	   try {
		   cle = trouverCle(texte);
	} catch (Exception e) {
		System.out.println("Impossible de d�chiffrer le texte");
		e.printStackTrace();
	}
	   return dechiffrer(texte, cle);
   }
   
   /** 
    * Calcule l'indice de coincidence du texte donn� en entr�e
    * @param texte
    * @return indice de coincidence du texte
    */
   public double getIndiceDeCoincidence(String texte) {
	   double indiceCoincidence = 0;
	   int n = texte.length();
	   for (char c : alphabet){
		   double nC = 0;
		   for(int i=0; i<n; i++) {
			   if (texte.charAt(i)==c)
				   nC++;
		   }
		   indiceCoincidence += (nC/n)*((nC-1)/(n-1));
	   }
	   indiceCoincidence *= 26;
	   return indiceCoincidence;
   }
   
   /**
    * Calcule la cl� chiffrant le texte d'entr�e
    * qu'on suppose chiffr� par un chiffre de C�sar
    * 
    * On d�termine la lettre la plus fr�quente dans le texte ;
    * En fran�ais le 'e' est de loin la lettre la plus fr�quente
    * --> donne la cl�
    * 
    * @param texte chiffr� par un chiffre de César
    * @return cl� du chiffre
    */
   public char trouverCleCesar(String texte){
	   double apparitionMax = 0;
	   char lettreLaPlusFrequente = 'a';
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
	   int decalage = ChiffreCesar.getIndex(lettreLaPlusFrequente)-ChiffreCesar.getIndex('e'); //d�calage entre -4 et 21
	   decalage = (decalage + alphabet.length ) % (alphabet.length); //ram�ne le d�calage entre 0 et 25
	   char cle = ChiffreCesar.getLettre(decalage);
	   
	   return cle;
   }
   }
