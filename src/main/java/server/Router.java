package server;


import controllers.EntidadesController;
import controllers.IncidentesController;
import controllers.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import cargaDeDatosMasiva.utils.BooleanHelper;
import cargaDeDatosMasiva.utils.HandlebarsTemplateEngineBuilder;


public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() throws Exception {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() throws Exception {

       LoginController loginController = new LoginController();
       EntidadesController entidadesController = new EntidadesController();
       IncidentesController incidentesController = new IncidentesController();


        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });


        // Apertura Incidente
        Spark.path("/aperturaIncidente", () -> {
            Spark.get("", incidentesController::pantallaAperturaIncidentes, engine);
        });


//recursos asicronicos, se devuelve un strign en el formato json que son llos valores que se mostraran en los desplegables

        Spark.path("/entidades",() ->{
           Spark.get("", entidadesController::obtenerEntidadesPorTipo);
        });


    }

}
