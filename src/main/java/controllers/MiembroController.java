package controllers;

import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MiembroComunidad;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembroComunidad;
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

    RepositorioMiembroComunidad repoMiembroComunidad = new RepositorioMiembroComunidad();

    public ModelAndView pantallaComunidadesDeMiembro(Request req, Response res) {
        String idMiembro = req.params(":id");
        List<MiembroComunidad> comunidades = obtenerMiembroComunidades(idMiembro);

        System.out.println("Comunidades de miembro: " + comunidades.size());
        // Preparar datos para la vista
        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        // Renderizar vista con Handlebars
          return new ModelAndView(model, "comunidadDeMiembro.hbs");
    }

    public List<MiembroComunidad> obtenerMiembroComunidades(String idMiembro) {
        Integer aux = Integer.parseInt(idMiembro);
        return repoMiembroComunidad.obtenerMiembroComunidades(aux);
    }


}
