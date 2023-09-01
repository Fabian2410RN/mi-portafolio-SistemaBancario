/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviciosExternos;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 *
 * @author calde
 */
public class TraductorDeGoogle {
    public TraductorDeGoogle() {
        System.setProperty("GOOGLE_API_KEY", "AIzaSyAa5fnltG5HikNxs6unxMr_zfB1dTUWeZc");
    }
    
    public String traducirDeEspanolAIngles(String pMensaje) {
        Translate traductor = TranslateOptions.getDefaultInstance().getService();
        Translation traduccion = traductor.translate(pMensaje, Translate.TranslateOption.sourceLanguage("es"),Translate.TranslateOption.targetLanguage("en"));
        return traduccion.getTranslatedText();
    }
}
