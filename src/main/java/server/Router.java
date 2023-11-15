package server;


import controllers.*;
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
       EstablecimientosController establecimientosController = new EstablecimientosController();
       ServiciosController serviciosController = new ServiciosController();


        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });


        // Apertura Incidente
        Spark.path("/aperturaIncidente", () -> {
            Spark.get("", incidentesController::pantallaAperturaIncidentes, engine);
            Spark.post("/registrarIncidente", incidentesController::registrarIncidente);
        });

        Spark.path("/mostrarIncidente", () -> {
            Spark.get("", incidentesController::mostrarIncidente, engine);
        });

        Spark.path("/mostrarIncidentes", () -> {
            //Spark.get("", incidentesController::obtenerIncidentes);
            Spark.get("", incidentesController::mostrarIncidentes);
            Spark.post("/cerrarIncidente/:id", incidentesController::cerrarIncidente);
        });





//recursos asicronicos, se devuelve un strign en el formato json que son llos valores que se mostraran en los desplegables

        Spark.path("/entidadesPorTipo",() ->{
           Spark.get("", entidadesController::obtenerEntidadesPorTipo);
        });
        Spark.path("/establecimientosPorEntidad",() ->{
            Spark.get("", establecimientosController::obtenerEstableciminetosDeEntidad);
        });

        Spark.path("/serviciosDeEstablecimiento",() ->{
            Spark.get("", serviciosController::obtenerServiciosDeEstablecimiento);
        });



    }

}
