package controllers;

import com.google.gson.Gson;
import model.entities.notificacion.Incidente;
import services.IncidenteService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
        res.redirect("/mostrarIncidentes");
        return null;
    }


    public ModelAndView mostrarIncidentes(Request req, Response res) {
        List<Incidente> incidentes = incidenteService.obtenerTodos();
        System.out.println("Incidentes: " + incidentes); // Solo para depuración

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("incidentes", incidentes);
        return new ModelAndView(modelo, "mostrarIncidente.hbs");
    }

    public ModelAndView cerrarIncidente(Request req, Response res) {
        Integer id = Integer.parseInt(req.params(":id"));
        try {
            incidenteService.cerrarIncidente(id);
            res.redirect("/mostrarIncidentes");
        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            res.status(500); // Indica un error interno del servidor
        }
        return null;
    }


}
