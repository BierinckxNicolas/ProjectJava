package com.crescendo.smartschool.services.utils;

import org.json.XML;

import javax.xml.bind.DatatypeConverter;
/** xml naar json en base64 naar json converter
 * @author Groep 5
 */
public class Converting {
    /**
     * Methode om van base64 naar json te converten<br>
     * Maakt gebruikt van de xmlToJson methode
     * @param base64Input base64 input
     * @return json object
     */
    public static String base64ToJson(String base64Input){
        String xmlOut = new String(DatatypeConverter.parseBase64Binary(base64Input));
        String JsonOut = xmlToJson(xmlOut);
        return JsonOut;
    }

    /**
     * Methode om van xml naar json om te zetten
     * @param xmlInput xml input
     * @return json object
     */
    public static String xmlToJson (String xmlInput){
        String JsonOut = XML.toJSONObject(xmlInput).toString();

        return JsonOut;
    }
}
