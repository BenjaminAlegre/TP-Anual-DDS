package server;


import controllers.*;
import model.entities.comunidad.Comunidad;
import services.AutenticacionService;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import controllers.utils.BooleanHelper;
import controllers.utils.HandlebarsTemplateEngineBuilder;

import java.util.*;


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

        AutenticacionService autenticacionService = new AutenticacionService();

       LoginController loginController = new LoginController();
       EntidadesController entidadesController = new EntidadesController();
       IncidentesController incidentesController = new IncidentesController();
       EstablecimientosController establecimientosController = new EstablecimientosController();
       ServiciosController serviciosController = new ServiciosController();
       AuthController authController = new AuthController();
       AdministradorController administradorController = new AdministradorController();
       RankingsController rankingsController = new RankingsController();
       MiembroController miembroController = new MiembroController();
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();

        // Login
        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
        });

        Spark.path("/callback", () -> {
            Spark.get("", authController::pantallaDeLogin, engine);
        });

        //Paginas Principales
        Spark.path("/paginaPrincipal", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.before("/administrador", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.before("/entidad", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("entidad");
                autenticacionService.authRol(req, res, roles);
            });

            Spark.get("", pantallaPrincipalController::pantallaPaginaPrincipal, engine);
            Spark.get("/administrador", pantallaPrincipalController::pantallaPrincipalAdministrador, engine);
            Spark.get("/entidad", pantallaPrincipalController::pantallaPrincipalEntidad, engine);
            Spark.get("/miembro", pantallaPrincipalController::pantallaPrincipalMiembro, engine);
        });

        // Apertura Incidente
        Spark.path("/aperturaIncidente", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                roles.add("entidad");
                autenticacionService.authRol(req, res, roles);
            });

            Spark.get("/", incidentesController::pantallaAperturaIncidentes, engine);
            Spark.post("/registrarIncidente", incidentesController::registrarIncidente);
        });

//        Spark.path("/mostrarIncidente", () -> {
//            Spark.before("/*", (req, res) -> {
//                List<String> roles = new ArrayList<>();
//                roles.add("miembro");
//                autenticacionService.authRol(req, res, roles);
//            });
//            Spark.get("", incidentesController::mostrarIncidente, engine);
//        });
        //Muestra incidentes activos
        Spark.path("/mostrarTodosIncidentes", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::mostrarTodosIncidentes, engine);
        });

        Spark.path("/mostrarIncidentesUsuario", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::mostrarIncidentesUsuario, engine);
        });

        Spark.before("/cerrarIncidente/*", (req, res) -> {
            List<String> roles = new ArrayList<>();
            roles.add("miembro");
            autenticacionService.authRol(req, res, roles);
        });
        Spark.post("/cerrarIncidente/:id", incidentesController::cerrarIncidente);

        //TODO: esto no funcina, estaba probando
        Spark.get("/prueba/buscarIncidentesPorEstado", incidentesController::pantallaBuscarIncidentesPorEstado, engine);

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
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/", administradorController::pantallaCargaMasiva , engine);
            Spark.post("", administradorController :: cargarDatos);
//            Spark.after("", (request, response)->{
//                System.out.println("--------------------------------------CIERRA ENTITY MANAGER Carga Masiva");
//                EntityManagerHelper.closeEntityManager();});
        });

        // Rankings
        Spark.path("/rankingsSemanales",() ->{
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("entidad");
                roles.add("administrador");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/", rankingsController::pantallaRankings, engine);
            Spark.get("/buscar", rankingsController::mostrarRanking, engine);
            Spark.get("/buscarAsync", rankingsController::enviarRanking);
            Spark.get("/pesado", rankingsController::pantallaRankingPesado, engine);
        });

        // Administracion de tipos de usuarios y observadores
        Spark.path("/miembroLiviano", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/comunidades", miembroController::pantallaComunidadesDeMiembro, engine);
            Spark.post("/comunidades/:comunidadId/cambiarTipo", miembroController::cambiarTipoMiembroLiviano);
        });

        Spark.path("/miembroPesado", () -> {
            Spark.before("/miembro", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("/comunidades", miembroController::pantallaMiembroComunidadPesado, engine);
            Spark.post("/comunidades/:comunidadId/cambiarTipo", miembroController::cambiarTipoMiembroPesado);
        });



        //Buscar incidentes por estado
        Spark.path("/buscarIncidentesPorEstado",() ->{
            Spark.get("", incidentesController::pantallaBuscarIncidentesPorEstado, engine);
            Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesPorEstado);
        });

        //Buscar incidentes por estado y comunidad
        Spark.path("/buscarIncidentesPorEstadoYComunidad", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::pantallaBuscarIncidenteComunidad, engine);
            Spark.get("/incidentesPorEstado", incidentesController::mostrarIncidentesEstadoComunidad);
        });

        Spark.before("/obtenerComunidades/*", (req, res) -> {
            List<String> roles = new ArrayList<>();
            roles.add("miembro");
            autenticacionService.authRol(req, res, roles);
        });
        Spark.get("/obtenerComunidades/:idUsuario", miembroController::obtenerComunidades);



        //TODO: discutir si estas vistas deberian ser borradas
        Spark.path("/resultadoBusqueda", () -> {
            Spark.get("", incidentesController::pantallaResultadoBusqueda, engine);
        });
        Spark.path("/mostrarIncidenteAbierto", () -> {
            Spark.get("", incidentesController::pantallaMostrarIncidenteAbierto, engine);
        });
        Spark.path("/mostrarIncidenteCerrado", () -> {
            Spark.get("", incidentesController::pantallaMostrarIncidenteCerrado, engine);
        });

        Spark.path("/sugerenciaRevisionIncidente", () -> {
            Spark.before("/*", (req, res) -> {
                List<String> roles = new ArrayList<>();
                roles.add("miembro");
                autenticacionService.authRol(req, res, roles);
            });
            Spark.get("", incidentesController::pantallaSugerenciaRevisionIncidente, engine);
        });


    }


//TODO: Esto deberia ser borrado(?)

//    private static String decodeJWT(String jwtToken) {
//        try {
//            // Dividir el JWT en partes (encabezado, payload, firma)
//            String[] parts = jwtToken.split("\\.");
//
//
//            // Decodificar Base64 URL de la carga útil (payload)
//            String base64Payload = parts[1].replace('-', '+').replace('_', '/');
//            String payload = new String(Base64.getDecoder().decode(base64Payload), "UTF-8");
//
//            return payload;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static String obtenerValor(String jsonString, String campo) {
//        int indiceInicio = jsonString.indexOf("\"" + campo + "\":");
//
//        if (indiceInicio != -1) {
//            int indiceFin = jsonString.indexOf(",", indiceInicio);
//            if (indiceFin == -1) {
//                indiceFin = jsonString.indexOf("}", indiceInicio);
//            }
//
//            if (indiceFin != -1) {
//                // Extraer el valor del campo
//                String valorCampo = jsonString.substring(indiceInicio + campo.length() + 4, indiceFin);
//                // Eliminar comillas si están presentes
//                valorCampo = valorCampo.replace("\"", "").trim();
//                return valorCampo;
//            }
//        }
//
//        return null;
//    }


}
