/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CezarAttaque;

/**
 *
 * @author blunt
 */
public class CezarAttaque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text ="POURTRESBREFSMESSAGESCOMMEUNSIMPLEMOTCETTEMETHODEES"
                + "TPEUSURCARILNYAGUEREDEVARIANTSPOURREDISTRIBUERUNEPOIGNEE"
                + "DELETTRESPAREXEMPLEUNMOTDETROISLETTRESNEPEUTETRETOURNEQUA"
                + "NDDANSSIXPOSITIONSDIFFERENTESAINSIPERSONNENEPEUTSETRANSFORMERQUENSEUL";
        
        String chiffréParCezar3="SRXUWUHVEUHIVPHVVDGHVFRPPHXQVLPSOHPRWFHWWHPHWKRGHHVWSH"
                + "XVXUFDULOQBDGXHUHGHYDULDQWVSRXUUHGLVWULEXHUXQHSRLGQHHGHOHWWUHVSDUHAHP"
                + "SOHXQPRWGHWURLVOHWWUHVQHSHXWHWUHWRXUQHTXDQGGDQVVLASRVLWLRQVGLIIHUHQWHV"
                + "DLQVLSHUVRQQHQHSHXWVHWUDQVIRUPHUTXHQVHXO";
        
        
        
        System.out.println("Cezar attaque par analyse frequentiel \n");
        
        ChiffreCesar CipherCezar=new ChiffreCesar();
        CezarBreaking CipherCezarBreaker=new CezarBreaking();
        
        CipherCezarBreaker.trouverCleCesar(chiffréParCezar3);

        
    }
    
}
