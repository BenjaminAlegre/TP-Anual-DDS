package controllers;


import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;

public class LoginController {



    public LoginController() throws IOException {

    }

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }


}
