package server;


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
//        InicioAdministradorController inicioAdministradorController = new InicioAdministradorController();
//        InicioMiembroController inicioMiembroController = new InicioMiembroController();
//        InicioAgenteController inicioAgenteController = new InicioAgenteController();
//        OrganizacionController organizacionController = new OrganizacionController();
//        RegistroController registroController = new RegistroController();
//        TransportePublicoController transportePublicoController = new TransportePublicoController();
//        HCController hcController = new HCController();
//        MunicipiosController municipiosController = new MunicipiosController();
//        LocalidadesController localidadesController = new LocalidadesController();
//        BaseController baseController = new BaseController();

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });





    }

}
