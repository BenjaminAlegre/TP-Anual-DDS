package controllers;

import model.entities.comunidad.Comunidad;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembros;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiembroController {

    RepositorioMiembros repoMiembros = new RepositorioMiembros();
    RepositorioComunidades repoComunidades = new RepositorioComunidades();

    public ModelAndView pantallaComunidadesDeMiembro(Request req, Response res) {
        String idMiembro = req.params(":id");
        List<Comunidad> comunidades = obtenerComunidadesDeMiembro(idMiembro);

        // Preparar datos para la vista
        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        // Renderizar vista con Handlebars
          return new ModelAndView(model, "comunidades.hbs");
    }




    public List<Comunidad> obtenerComunidadesDeMiembro(String idMiembro) {

        List<Comunidad> comunidades = repoComunidades.obtenerComunidadesDeMiembro(idMiembro);
        return comunidades; // Devolver la lista real de comunidades
    }
}
