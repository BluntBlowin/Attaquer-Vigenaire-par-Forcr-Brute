/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenaireattaque;

/**
 *
 * @author Blunt_Blowin
 */
public class Vigenaire {
    private static String textClair;
    private static String textChiffre;
    private static  String clé;
    
    private static int[] RepeteClé; 
    private static int[] textClairInt;
    private static int[] textChiffreInt;
    private static int[] cléInt;
    static String  alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static int  N=alphabet.length();
    
    
    
    public Vigenaire(){

    }
    
    public String  chiffrer(String clé,String textClair){
        this.clé=clé;
        this.textClair=textClair;
        textChiffre="";
        textChiffreInt=new int[textClair.length()]; //creer la table entiere du texte chiffrer
        textClairInt=new int[textClair.length()];//creer la table entiere du texte claire
        for(int i=0;i<textClair.length();i++) textClairInt[i]=lettreVersChiffre(textClair.charAt(i)); //remplire  table text claire par entiers corespendants aux lettres
        cléInt =new int[clé.length()];
        for(int i=0;i<clé.length();i++)cléInt[i]=lettreVersChiffre(clé.charAt(i));//Conversion de la clé en entier
        RepeteClé=new int[textClair.length()];
        for(int i=0;i<textClair.length();i++){
           int j=i%clé.length();
           RepeteClé[i]=cléInt[j];    
        }

       for(int i=0;i<textClair.length();i++){
            textChiffreInt[i]=(lettreVersChiffre(textClair.charAt(i))+RepeteClé[i])%N;
            textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }   
       return textChiffre;
    }
     
    public  String Dechiffrer(String clé,String textClair){
        Vigenaire.clé=clé;
        Vigenaire.textClair=textClair;
        textChiffre="";
        textChiffreInt=new int[textClair.length()]; //creer la table entiere du texte chiffrer
        textClairInt=new int[textClair.length()];//creer la table entiere du texte claire
        for(int i=0;i<textClair.length();i++) textClairInt[i]=lettreVersChiffre(textClair.charAt(i)); //remplire  table text claire par entiers corespendants aux lettres
        cléInt =new int[clé.length()];
        for(int i=0;i<clé.length();i++)cléInt[i]=lettreVersChiffre(clé.charAt(i));//Conversion de la clé en entier
        RepeteClé=new int[textClair.length()];
        for(int i=0;i<textClair.length();i++){
           int j=i%clé.length();
           RepeteClé[i]=cléInt[j];    
        }     
       for(int i=0;i<textClair.length();i++){
            textChiffreInt[i]=(lettreVersChiffre(textClair.charAt(i))-RepeteClé[i])%N;
            if(textChiffreInt[i]<0)textChiffreInt[i]=textChiffreInt[i]+N;
            textChiffre=textChiffre+ChiffreVerslettre(textChiffreInt[i]);
        }   
       return textChiffre;       
    }
    
     private static int lettreVersChiffre(char c){     
       return Vigenaire.alphabet.indexOf(c);                
    }
        private  static char ChiffreVerslettre(int c){
        return Vigenaire.alphabet.charAt(c);
    }
        
    public void setAlphabet(int alphabetIndex){
        if(alphabetIndex==1)
            this.alphabet="ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        else if(alphabetIndex==2)
            this.alphabet="ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        else if(alphabetIndex==3)
            this.alphabet="ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        this.N=this.alphabet.length();
            
    }
}
