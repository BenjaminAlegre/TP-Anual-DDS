package controllers;



import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import services.JWTService;
import services.RequestService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Base64;


public class AuthController {

    RequestService requestService = new RequestService();
    JWTService jwtService = new JWTService();
    private String domain = "https://dev-7yecyva5welnz3zc.us.auth0.com";;

    public AuthController() {
    }

    public ModelAndView pantallaDeLogin(Request req, Response res) throws Exception {
        String authCode = req.queryParams("code");
        String urlToken = this.domain + "/oauth/token";
        String clientSecret = "Dxiw4FnGlefSrA-yipHyR9dgy7fDhEBK0N5L9-n6V87UUSmspsNDDlgMs-X2MTDH";


        String json = requestService.makePostRequest(urlToken, authCode, clientSecret);
        System.out.println("Json Token: " + json);
        String id_token = jwtService.obtenerValor(json, "id_token");
        System.out.println("idToken: " + id_token);
        String jwtToken = jwtService.decodeJWT(id_token);
        System.out.println("JWT Token: " + jwtToken);

        res.cookie("jwt", id_token);

        res.redirect("/aperturaIncidente/");
        return null;
    }


}