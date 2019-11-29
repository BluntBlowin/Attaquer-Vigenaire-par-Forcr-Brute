/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenaireattaque;

import CezarAttaque.ChiffreCesar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author blunt
 */
public class vigenaireBreaking {

    public static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    int N=alphabet.toString().length();

        public static int LONGUEUR_CLE_MAX = 100; 
	public static double IC_FRANCAIS = 2.02;
	public static double IC_UNIFORME = 1.0;
        String textChiffré;
        
    Vigenaire vigenaireObjet=new Vigenaire();
  
    public void BruteForce(String tChiffre, int tailleDuclé){
        
        ArrayList indicesDesEspaces=eliminerLesEspaces(tChiffre);
        this.textChiffré=tChiffre.replaceAll(" ", "");
        
        try{
            InputStream flux=new FileInputStream(Integer.toString(tailleDuclé)+".txt"); 
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){

                String result=this.vigenaireObjet.Dechiffrer(ligne,this.textChiffré);               
                double indiceDeCoincidence=getIndiceDeCoincidence(result);
                if((indiceDeCoincidence-IC_FRANCAIS)>0.2){
                    System.out.println(ligne+" :");
                    System.out.println(remettreLesEspaces(indicesDesEspaces, result)+"\n_ _ _ _ _"); 
                }
            }
            buff.close(); 
            }   		
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void Dictionnaire(String textChiffré, String listDesMots){
        try{
            InputStream flux=new FileInputStream(listDesMots); 
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println("la clé : "+ligne);
                System.out.println();
                System.out.println("text en claire : _____________");
                System.out.println(this.vigenaireObjet.Dechiffrer(ligne, textChiffré));
                System.out.println("______________________________");
            }
            buff.close(); 
            }   		
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void keyLengthThanBreak(String texteChiffre) throws Exception{
        ArrayList indicesDesEspaces=eliminerLesEspaces(texteChiffre);
        texteChiffre=texteChiffre.replaceAll(" ", "");
         
        int LongeurDuClef=trouverLongueurCle(texteChiffre);
        System.out.println("Longueur du clef : "+LongeurDuClef);
        List<String> sousTextes=parser(texteChiffre,LongeurDuClef);
        String clefProbable="";
           for(int i=0;i<sousTextes.size();i++){
               String sousTexte=sousTextes.get(i);
               char c=trouverCleCesar(sousTexte);  
               clefProbable+=c;
           }
        System.out.println("Clef probable : "+clefProbable);
        
        String Claire=new Vigenaire().Dechiffrer(clefProbable, texteChiffre);
        Claire=remettreLesEspaces(indicesDesEspaces, Claire);
         System.out.println(Claire);
    }
    private int trouverLongueurCle(String texteChiffre) throws Exception{
	   int lgCle = 1;
	   Boolean found = false;
	   while(!found && lgCle<LONGUEUR_CLE_MAX) {
		   
		   // création des sous-textes en prenant une lettre tout les lgCle caract�res
		   List<String> sousTexte = parser(texteChiffre, lgCle);
   
		   // calcul de la moyenne de l'indice de coincidence pour cette lg de cl�
		   double moyIC = 0; 
		   for (String s : sousTexte){
			   moyIC += getIndiceDeCoincidence(s);
		   }
		   moyIC = moyIC / lgCle;
		   
		  // System.out.println("Pour la lg de clé "+lgCle+ ", on calcule un IC moyen de : " +moyIC);
		   
                   if (Math.abs(moyIC-IC_UNIFORME) > 0.99)
			   return lgCle;   
		   lgCle++;
	   }
	   
	   throw new Exception ("Impossible de trouver la longueur de la clé (recherche des clés < "+LONGUEUR_CLE_MAX+").");
   }
    private List<String> parser(String texte, int n){   //decomposer le text en sous texts selon la longueur du clé
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
    private double getIndiceDeCoincidence(String texte) { //calculer l'indice du coincidence
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
    private char trouverCleCesar(String texte){
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

          // System.out.println(cle);
	   return cle;
   }
    
    private ArrayList eliminerLesEspaces(String text){  
        String ch1 = "";
        int l = text.length();
        ArrayList indicesDesEspaces;
        indicesDesEspaces = new ArrayList<>();
        char c;
        for(int i = 0 ; i < l ; i++){
            c = text.charAt(i);
            if(c == ' ') indicesDesEspaces.add(i);
        } 
       return indicesDesEspaces;
    }
    private String remettreLesEspaces(ArrayList indices,String text){
        String res = text;
        for(int i=0;i<indices.size();i++){
            res=addChar(res,' ', (int) indices.get(i));
        }
        return res;
    }  
    private String addChar(String str, char ch, int position) {
    int len = str.length();
    char[] updatedArr = new char[len + 1];
    str.getChars(0, position, updatedArr, 0);
    updatedArr[position] = ch;
    str.getChars(position, len, updatedArr, position + 1);
    return new String(updatedArr);
   }
    
    public boolean appartientAuFrançais(String mot) throws FileNotFoundException, IOException{
        File f = new File("liste_de_mots_français.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f)); 
        String line = null;
        
        while ((line = reader.readLine()) != null) {
        if (line.contains(mot)) {
            System.out.println("\t \t dans le mot :"+line);
            return true;
            }      
        }
        return false;
    }
   
    public boolean  dechiffrerUnMot(String chiifré,int tailleDuclé) throws FileNotFoundException, IOException{
        File f;//Le dictionnaire des mots
        String line;
        BufferedReader reader = null;
        
        InputStream flux; //les clés probables
        InputStreamReader lecture;
        BufferedReader buff;
        String ligne;
        String result;
        try{
             f= new File("liste_de_mots_français.txt");   
             reader= new BufferedReader(new FileReader(f)); 
             line= null;
        }
        catch(Exception e){
            e.printStackTrace();    
                }
        try{    
            flux=new FileInputStream(Integer.toString(tailleDuclé)+".txt"); 
            lecture=new InputStreamReader(flux);
            buff=new BufferedReader(lecture);
            
            
            while ((ligne=buff.readLine())!=null){
                result=this.vigenaireObjet.Dechiffrer(ligne,chiifré);
                /* Verifier esk appartient au dictionnaire :*/
                while ((line = reader.readLine()) != null) {
                    if (line.contains(result)) {
                    System.out.println("\t \t dans le mot :"+line);
                    return true;
                    }
                    else{
                        System.out.println(".");
                    }
        }
               
            }
            buff.close(); 
            }  
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
