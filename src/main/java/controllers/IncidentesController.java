package controllers;

import DTO.IncidenteDTO;
import com.google.gson.Gson;
import model.entities.notificacion.Incidente;
import services.IncidenteService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import static model.entities.notificacion.EstadoIncidente.ACTIVO;

public class IncidentesController {

    private IncidenteService incidenteService = new IncidenteService();


    public ModelAndView pantallaAperturaIncidentes(Request req, Response res){
        return new ModelAndView(null, "aperturaIncidentes.hbs");
    }

    public  ModelAndView buscarIncidente(Request req, Response res){
        return  new ModelAndView(null, "buscarIncidente.hbs");
    }

    public  ModelAndView mostrarIncidente(Request req, Response res){
        return  new ModelAndView(null, "mostrarIncidente.hbs");
    }
    public ModelAndView registrarIncidente(Request req, Response res) {
        incidenteService.guardarIncidente(req);
        res.redirect("/mostrarTodosIncidentes");
        return null;
    }


    public ModelAndView mostrarTodosIncidentes(Request req, Response res) {
//        List<Incidente> incidentes = incidenteService.obtenerTodos();
        List<Incidente> incidentes = incidenteService.obtenerPorEstado("ACTIVO");
        System.out.println("Incidentes: " + incidentes); // Solo para depuración

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("incidentes", incidentes);
        return new ModelAndView(modelo, "mostrarIncidente.hbs");
    }

    public ModelAndView cerrarIncidente(Request req, Response res) {
        Integer id = Integer.parseInt(req.params(":id"));
        try {
            incidenteService.cerrarIncidente(id);
            res.redirect("/mostrarTodosIncidentes");
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            res.status(500); // Indica un error interno del servidor
        }
        return null;
    }

    public String mostrarIncidentesPorEstado(Request req, Response res) {
        String estado = req.queryParams("estado");
        List<Incidente> incidentes = incidenteService.obtenerPorEstado(estado);

//        Map<String, Object> modelo = new HashMap<>();
//        modelo.put("incidentes", incidentes);
//        return new ModelAndView(modelo, "buscarIncidente.hbs");

        res.type("application/json");
        return new Gson().toJson(incidentes);
    }

    public ModelAndView pantallaBuscarIncidentesPorEstado(Request req, Response res) {
        return new ModelAndView(null, "buscarIncidente.hbs");
    }

    public ModelAndView pantallaBuscarIncidenteComunidad(Request req, Response res) {
        return new ModelAndView(null, "buscarIncidenteComunidad.hbs");
    }

    public ModelAndView pantallaMostrarIncidenteAbierto(Request req, Response res) {
        return new ModelAndView(null, "mostrarIncidenteAbierto.hbs");
    }
    public ModelAndView pantallaMostrarIncidenteCerrado(Request req, Response res) {
        return new ModelAndView(null, "mostrarIncidenteCerrado.hbs");
    }
    public ModelAndView pantallaCargaMasiva(Request req, Response res) {
        return new ModelAndView(null, "cargaMasiva.hbs");
    }
    public ModelAndView pantallaRankings(Request req, Response res) {
        return new ModelAndView(null, "rankings.hbs");
    }
    public ModelAndView pantallaPaginaPrincipal(Request req, Response res) {
        return new ModelAndView(null, "paginaPrincipal.hbs");
    }
    public ModelAndView pantallaResultadoBusqueda(Request req, Response res) {
        return new ModelAndView(null, "resultadoBusqueda.hbs");
    }
    public ModelAndView pantallaSugerenciaRevisionIncidente(Request req, Response res) {
        return new ModelAndView(null, "sugerenciaRevisionIncidente.hbs");
    }

    public String mostrarIncidentesPorEstadoPrueba(Request req, Response res) {
        try {
            String estado = req.queryParams("estado");
            List<IncidenteDTO> incidentesDTO = incidenteService.obtenerPorEstadoToDTO(estado);
            res.type("application/json");
            return new Gson().toJson(incidentesDTO);
        } catch (Exception e) {
            res.status(500);
            return "Error al procesar la solicitud: " + e.getMessage();
        }
    }


}
