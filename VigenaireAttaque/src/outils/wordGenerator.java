/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import vigenaireattaque.Vigenaire;

/**
 *
 * @author blunt
 */
public class wordGenerator {
    public void generate() throws IOException{
       String s="";
       String mot;
       Vigenaire vigenaireObjet=new  Vigenaire();
       
       
        
        
       //FileWriter fichier = new FileWriter("6.txt");
     for(char lettre1='A'; lettre1<='Z'; lettre1++)
    {
        for(char lettre2='A'; lettre2<='Z'; lettre2++)
        {
            for(char lettre3='A'; lettre3<='Z'; lettre3++)
        {
            for(char lettre4='A'; lettre4<='Z'; lettre4++)
        {
            for(char lettre5='A'; lettre5<='Z'; lettre5++)
        {
            for(char lettre6='A'; lettre6<='Z'; lettre6++)
        {
            
                        s+=lettre1;s+=lettre2;
                        s+=lettre3;
                        s+=lettre4;
                      
                        s+=lettre5;
                        s+=lettre6;
                        mot=vigenaireObjet.Dechiffrer(s,"TRMVZV");
                        
                        File f = new File("liste_de_mots_français.txt");
                        BufferedReader reader = new BufferedReader(new FileReader(f)); 
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            //System.out.println(line);
                            if (line.contains(mot)) {
                                
                                    System.out.println("dans le mot :"+line);
                                            System.out.println("clé "+s);
                                            System.out.println("mot claie :"+mot);
                                            System.out.println("__________");
                                    
                            }
                            
                        }
                        s="";
        }
        }
        }
        }
        }
    }
           // fichier.close(); 
           System.out.println("END");
    }       
}
