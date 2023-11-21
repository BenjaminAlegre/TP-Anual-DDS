package controllers;



import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

public class AuthController {

    private String domain = "https://dev-7yecyva5welnz3zc.us.auth0.com";;

    public AuthController() {
    }

    public ModelAndView pantallaDeLogin(Request req, Response res) throws Exception {
        String authCode = req.queryParams("code");
        String urlToken = this.domain + "/oauth/token";
        String clientSecret = "Dxiw4FnGlefSrA-yipHyR9dgy7fDhEBK0N5L9-n6V87UUSmspsNDDlgMs-X2MTDH";


        String json = this.makePostRequest(urlToken, authCode, clientSecret);
        System.out.println("Json Token: " + json);
        String id_token = this.obtenerValor(json, "id_token");
        System.out.println("idToken: " + id_token);
        String jwtToken = this.decodeJWT(id_token);
        System.out.println("JWT Token: " + jwtToken);

        res.cookie("jwt", id_token);

        res.redirect("/aperturaIncidente/");
        return null;
    }

    //TODO: Esto debería estar en una clase aparte
    private static String makePostRequest(String url, String authCode, String clientSecret) throws Exception {
        try {
            // Crear un cliente HTTP
            HttpClient httpClient = HttpClients.createDefault();

            // Crear una solicitud POST
            HttpPost httpPost = new HttpPost(url);

            // Configurar los encabezados de la solicitud
            httpPost.setHeader("Content-Type", "application/json");

            // Agregar datos al cuerpo de la solicitud
            String jsonBody = "{ \"code\": \"" + authCode + "\"" +
                    ",\"client_secret\": \"" + clientSecret + "\"" +
                    ",\"grant_type\": \"authorization_code\"" +
                    ",\"redirect_uri\": \"http://localhost:3000\"" +
                    ",\"client_id\": \"88BuFrmFnU78LT4FnfGV1Ml4MlMfdKxC\"" +
                    "}";
            System.out.println("Json del servidor: " +jsonBody);

            StringEntity entity = new StringEntity(jsonBody);
            httpPost.setEntity(entity);

            // Enviar la solicitud y recibir la respuesta
            HttpResponse response = httpClient.execute(httpPost);

            // Leer la respuesta
            HttpEntity responseEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            String line;
            StringBuilder responseString = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseString.append(line);
            }

            // Imprimir la respuesta
            System.out.println("Respuesta del servidor: " + responseString.toString());

            // Cerrar recursos
            reader.close();

            return responseString.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //TODO: Esto debería estar en una clase aparte
    private static String decodeJWT(String jwtToken) {
        try {
            // Dividir el JWT en partes (encabezado, payload, firma)
            String[] parts = jwtToken.split("\\.");


            // Decodificar Base64 URL de la carga útil (payload)
            String base64Payload = parts[1].replace('-', '+').replace('_', '/');
            String payload = new String(Base64.getDecoder().decode(base64Payload), "UTF-8");

            return payload;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //TODO: Esto debería estar en una clase aparte
    private static String obtenerValor(String jsonString, String campo) {
        int indiceInicio = jsonString.indexOf("\"" + campo + "\":");

        if (indiceInicio != -1) {
            int indiceFin = jsonString.indexOf(",", indiceInicio);
            if (indiceFin == -1) {
                indiceFin = jsonString.indexOf("}", indiceInicio);
            }

            if (indiceFin != -1) {
                // Extraer el valor del campo
                String valorCampo = jsonString.substring(indiceInicio + campo.length() + 4, indiceFin);
                // Eliminar comillas si están presentes
                valorCampo = valorCampo.replace("\"", "").trim();
                return valorCampo;
            }
        }

        return null;
    }





}