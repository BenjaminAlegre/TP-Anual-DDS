package server;


import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import controllers.utils.BooleanHelper;
import controllers.utils.HandlebarsTemplateEngineBuilder;

import java.util.Base64;


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
       AuthController authController = new AuthController();
       AdministradorController administradorController = new AdministradorController();
       RankingsController rankingsController = new RankingsController();

        // Login
        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });

        Spark.path("/callback", () -> {
            Spark.get("", authController::pantallaDeLogin, engine);
        });

        // Apertura Incidente

        Spark.path("/aperturaIncidente", () -> {
            Spark.before("/*", (req, res) -> {//TODO: obviamente esto se pondra en una clase aparte
                System.out.println("Filtro de autenticación");
                if (req.cookie("jwt") == null) {
                    res.redirect("/login");

                } else {
                    String jwtToken = req.cookie("jwt");
                    String jwtPayload = Router.decodeJWT(jwtToken);
                    String namespace = "http://localhost:3000/";
                    String roles = Router.obtenerValor(jwtPayload, namespace + "roles");
//                    System.out.println("usser: " + roles);
                    if (roles == null || !roles.contains("falopa de la buena")) {
                        res.redirect("/mostrarTodosIncidentes");
                    }
                }
            });

            Spark.get("/", incidentesController::pantallaAperturaIncidentes, engine);
            Spark.post("/registrarIncidente", incidentesController::registrarIncidente);
        });
        //No Muestra incidentes
        Spark.path("/mostrarIncidente", () -> {
            Spark.get("", incidentesController::mostrarIncidente, engine);
        });
        //Muestra incidentes activos
        Spark.path("/mostrarTodosIncidentes", () -> {
            Spark.get("", incidentesController::mostrarTodosIncidentes, engine);
        });

        Spark.post("/cerrarIncidente/:id", incidentesController::cerrarIncidente);

        //TODO: esto no funcina, estaba probando
        Spark.get("/prueba/buscarIncidentesPorEstado", incidentesController::pantallaBuscarIncidentesPorEstado, engine);
       // Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstado, engine);
        //Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstado);

        //recursos asicronicos, se devuelve un strign en el formato json que son los valores que se mostraran en los desplegables
        Spark.path("/entidadesPorTipo",() ->{
           Spark.get("", entidadesController::obtenerEntidadesPorTipo);
        });
        Spark.path("/establecimientosPorEntidad",() ->{
            Spark.get("", establecimientosController::obtenerEstableciminetosDeEntidad);
        });

        Spark.path("/serviciosDeEstablecimiento",() ->{
            Spark.get("", serviciosController::obtenerServiciosDeEstablecimiento);
        });


//Carga masiva

        Spark.path("/cargaMasiva", () -> {
            Spark.get("", administradorController::pantallaCargaMasiva , engine);
            Spark.post("", administradorController :: cargarDatos);
//            Spark.after("", (request, response)->{
//                System.out.println("--------------------------------------CIERRA ENTITY MANAGER Carga Masiva");
//                EntityManagerHelper.closeEntityManager();});
        });

// Rankings
        Spark.path("/rankingsSemanales",() ->{
            Spark.get("", rankingsController::pantallaRankings, engine);
            Spark.get("/buscar", rankingsController::mostrarRanking, engine);
            Spark.get("/buscarAsync", rankingsController::enviarRanking);
            Spark.get("/pesado", rankingsController::pantallaRankingPesado, engine);
        });


    //Vistas Agregadas

        Spark.path("/buscarIncidentesPorEstado",() ->{
            Spark.get("", incidentesController::pantallaBuscarIncidentesPorEstado, engine);
            Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstadoPrueba);

        });

        //Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstadoPrueba);


        Spark.path("/buscarIncidenteComunidad", () -> {
            Spark.get("", incidentesController::pantallaBuscarIncidenteComunidad, engine);
        });
        Spark.path("/cargaMasiva", () -> {
            Spark.get("", incidentesController::pantallaCargaMasiva, engine);
        });
        Spark.path("/mostrarIncidenteAbierto", () -> {
            Spark.get("", incidentesController::pantallaMostrarIncidenteAbierto, engine);
        });
        Spark.path("/mostrarIncidenteCerrado", () -> {
            Spark.get("", incidentesController::pantallaMostrarIncidenteCerrado, engine);
        });
        Spark.path("/paginaPrincipal", () -> {
            Spark.get("", incidentesController::pantallaPaginaPrincipal, engine);
        });
        Spark.path("/rankings", () -> {
            Spark.get("", incidentesController::pantallaRankings, engine);
        });
        Spark.path("/resultadoBusqueda", () -> {
            Spark.get("", incidentesController::pantallaResultadoBusqueda, engine);
        });
        Spark.path("/sugerenciaRevisionIncidente", () -> {
            Spark.get("", incidentesController::pantallaSugerenciaRevisionIncidente, engine);
        });


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
