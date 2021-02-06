
import java.util.Iterator;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wilmer
 */
public class ConsumoJson {

    public static void main(String[] args) throws JSONException {
        String cadena = "{\n"
                + "  \"filters\": [\n"
                + "        {\n"
                + "        \"filterType\":\"string\",\n"
                + "        \"property\":\"cif\",\n"
                + "        \"value\":\"001235\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"page\": \"<integer>\",\n"
                + "    \"pageSize\": \"<integer>\",\n"
                + "    \"queryId\": \"<string>\"\n"
                + "}";

        JSONObject mainObject = new JSONObject(cadena);
        String cif="";
        for (int i = 0; i < mainObject.length(); i++) {
            String fi=mainObject.getString("filters");
            JSONArray json=new JSONArray(fi);
            for(int x=0;x<json.length();x++){
                JSONObject jsonO=new JSONObject(json.getJSONObject(x).toString());
                cif=jsonO.getString("value");
            }
        }
        /*
           try {
            //    contenidoJson es tu string conteniendo el json.
            JSONObject mainObject = new JSONObject(cadena);
            //Obtenemos los objetos dentro del objeto principal.
               System.out.println("Json:"+mainObject);
            Iterator<String> keys = mainObject.keys();
           

           
            while (keys.hasNext())
            {
                // obtiene el nombre del objeto.
                String key = keys.next();
                if(key.substring(0).equals("filters")){
                 
                }else {
                    System.out.println("entro 2");
                  JSONObject jsonObject = mainObject.getJSONObject("{"+key+"}");
                 }
                System.out.println("key:" + key);
                
                

                //Imprimimos los valores.
            }
         */

    }

}
