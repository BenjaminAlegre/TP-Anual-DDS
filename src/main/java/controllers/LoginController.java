package controllers;



import spark.ModelAndView;
import spark.Request;
import spark.Response;



public class LoginController {

    private String domain;

    public LoginController() {
        this.domain = "https://dev-7yecyva5welnz3zc.us.auth0.com";

    }

    public ModelAndView pantallaDeLogin(Request req, Response res) {
        String redirectUri = this.domain;
        res.redirect(this.domain + "/authorize" +
                "?response_type=code" +
                "&client_id=88BuFrmFnU78LT4FnfGV1Ml4MlMfdKxC" +
                "&redirect_uri=http://localhost:3000/");
        return null;
    }


}

