/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenaireattaque;

import Facorizer.Factorizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.util.Duration.seconds;
import outils.wordGenerator;

/**
 *
 * @author blunt
 */
public class VigenaireAttaque {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        String text ="POURTRESBREFSMESSAGESCOMMEUNSIMPLEMOTCETTEMETHODEES"
                + "TPEUSURCARILNYAGUEREDEVARIANTSPOURREDISTRIBUERUNEPOIGNEE"
                + "DELETTRESPAREXEMPLEUNMOTDETROISLETTRESNEPEUTETRETOURNEQUA"
                + "NDDANSSIXPOSITIONSDIFFERENTESAINSIPERSONNENEPEUTSETRANSFORMERQUENSEUL";
        
        String chiffréParVigenaireABCD="PPWU TSGV BSGIS NGVSBIHS DQPMF WQ SGOSLF ORT DGWTF OHTIQGE "
                + "FUW PFW VUS EDRGNQYBIXESGGEWCUIBPWSQQXRSGGITVUICWHRVPHPPKGNFGGEMGWTSGVPBTH"
                + "XFOSLFWQMPVGEUTRITNHTUTHSOGSEVVHTSGWOVTQERWDNEFDNTULXQQVIUKRNTFLFGGUEOVH"
                + "SBKQSGRHRTQQNFPHPFWWSFVUAOUIOSOHRRWHNTGXLQQXRUTHSCTHFTOHSTCGETERMNGXNTKP"
                + "PMGPOUEHTUGPEUGRDFGVTQGXSVTFASKONZCGUFTHDFXDRGCQTTRRUSTHDGUWRGDXESWQEQQLG"
                + "OGHDFNHTUTHSQCUEYGPPMGXNNQWDFVUOGUOEUVUETPHPFWWEUTHTPWUNFSXAOFGAOUVIYRRSG"
                + "VLOOUGIGHHRFPWETCLNTKSESURNOGQEQGXTTGWRBPVFPTPESSXEOUHUM";
        
        String chiffréParVigenaireREGARDER="GSARKUIGSVKFGPIGGEMEGFSDDIANGLQGCISOKFIKK"
                + "ISEKKSUVIYTGHYGLVIAILPEPEMUVUIUVZGRZDRKGTUUIUIUZWZRZEYVIYTEGRMXEIKDV"
                + "OIKKVKSGDVVOISPCHYEDSZDVWVFZWREKWVVGRKPVXXVKVKTFXVEVUAAEGHREWYIOSSGZXO"
                + "OEVHZWGKRVQXVGEONGLTVIWUNEHRVGIATGHXIRRYFFUQVIUAEEVILCTUUIWVVGFXEWVQVGW"
                + "GGVVGFDQKUEVMDGPKMFWGVKXKMVWLFUIKSKSILGYXCRUMCECGGLHVVUIBAILEEKWVOLUVVU"
                + "MYTILFLVVANVSSZXRKEUHPVKXXEGSEIVBKMGOILEQUTUHXIFMYLVWXIVWTEGHYKVXXEKRYIE"
                + "IWURQHURRYSZATFGMZIFQWUZGLEIHRKVWGIEVMGVVYOEQIEVTKUKVIKIETSWRVDVVWUVQWVLP";
        
        String chiffréParVigenaireECOLE="TQIC XVGG MVIHG XIWUORIW ECXQI WB DMQRZP QSV QPXXG APXLQRP IWV "
                + "DPY WWF NEVKZYCEIIPVIFSGEVKOYXWRCFVVGRTWXTWMYITIYITQWRRIGRPPIVHCIWROCIBGAAPIW"
                + "BXSXFSEVSKGWIXVFPWRGDPYXGHCIXQICRISILRHFOYWWKLASWKHTSRURTGGGFPRXGGLMRUWAIVUC"
                + "YRIPSAIYVGPXVCBDGSTAPVUWSYWIWZASYTHCIWDFPGWOSDWEISDGSOAPYRUWXTPGAZXGGHEIQGHSSH"
                + "GSDXTGIDYVEOCMPPMLKYGFPHIXOCMEPHDTSWFCIHKGEVMDIPVYPSASMIBPIHGZPXXTSDTETSIIQRZ"
                + "PYROCEHIVFZMWNSEXVGGYITGIEIXTSESYTBPUYCBOHEPGDMBRCDMXKCYWHKTQIVGBEIWCWYWMRSCW"
                + "SPBPRIRSFXWGHCERUTZVQGFBYIPGPYP";
        String chiffréParVigenaireVIGENAIRECIPHER="KWAVGRMGFTMUZQVNAGKRSKFQOMGUWZHXRIZOBTIVBT"
                + "TIKCWGIRSBGIWAGYGRMQRRLAOLITMSLZRMQGRGSXFYTZTKMGOZOFHEZLRGXDPKEZMGIYEBKVG"
                + "AEHVVSMSTYECEQQBSLXIGQYPRTBIIUVTWILOMZVRTWLVPMFBEEYLGRFSQOTQAXAMFIAGMSFMIIP"
                + "BTZEZIAOTRRAFRPMCLTVPBYIGRIEWHWGTIILCKRFECC";
        String textChiffreAvecGHADI="HVNCWAY TRCZ SE PWTKE CM SHPSMRSE OWQTAQMFLVLMTZDHKUU"
                + "SWITAIQMAUEYQRSETCOZEWZUBVHLGUSOMDARHUKLSWLKSAOOKYIHTGZALAUUCKMFUOXAKZ"
                + "TOMVYIQBKTPVUGPNWMTHNWMZPLHFOZTHLKZOLAKHUAXGYTRCZXULNUUTFPGUTHZZVUWTKAEPXY";

        //System.out.println(text);
        vigenaireBreaking VigenaireBreak=new vigenaireBreaking();
       
        try{
            //VigenaireBreak.keyLengthThanBreak("VRALHVPOMO");
            //VigenaireBreak.BruteForce(chiffréParVigenaireABCD, 4);
               // VigenaireBreak.appartientAuFrançais("TRISTE");
            //VigenaireBreak.dechiffrerUnMot("DRUWEI",6);
            wordGenerator wg=new wordGenerator();
            wg.generate();
           /*
            BigInteger n1=new BigInteger("806573");//9.51E-4  secondes.
            BigInteger n2=new BigInteger("4027949");//5.958E-4  secondes.
            BigInteger n3=new BigInteger("88932209");// 0.0015681  secondes.
            BigInteger n4=new BigInteger("6419752441");//0.0066406  secondes.
            BigInteger n5=new BigInteger("954731463599");//0.0451807  secondes
            BigInteger n6=new BigInteger("15030197913046671961");//18.530144  secondes.
            BigInteger n7=new BigInteger("41334682187482505677");
            
            Factorizer fc=new Factorizer();
            long tempsDebut = System.nanoTime();
            fc.FermatFactorization(n6);
            long tempsEnd = System.nanoTime();
            
            float seconds = (tempsEnd - tempsDebut) / 1000000000F;
            System.out.println("Opération effectuée en: "+(seconds)+ "  secondes.");
            */
            
            
        } catch (Exception ex) {
            Logger.getLogger(VigenaireAttaque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
