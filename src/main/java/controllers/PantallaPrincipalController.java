package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PantallaPrincipalController {

    public ModelAndView pantallaPaginaPrincipal(Request req, Response res) {
        return new ModelAndView(null, "paginaPrincipal.hbs");
    }

    public ModelAndView pantallaPrincipalAdministrador(Request req, Response res) {
        return new ModelAndView(null, "principalAdministrador.hbs");
    }

    public ModelAndView pantallaPrincipalEntidad(Request req, Response res) {
        return new ModelAndView(null, "principalEntidadPrestadora.hbs");
    }

    public ModelAndView pantallaPrincipalMiembro(Request req, Response res) {
        return new ModelAndView(null, "principalMiembro.hbs");
    }
}
