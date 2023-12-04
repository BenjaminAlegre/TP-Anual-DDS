package controllers;

import DTO.MiembroComunidadDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.entities.comunidad.Comunidad;
import model.entities.comunidad.MiembroComunidad;
import model.entities.comunidad.TipoMiembro;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioMiembroComunidad;
import model.repositorios.RepositorioMiembros;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MiembroController {

    RepositorioMiembros repoMiembros = new RepositorioMiembros();
    RepositorioComunidades repoComunidades = new RepositorioComunidades();

    RepositorioMiembroComunidad repoMiembroComunidad = new RepositorioMiembroComunidad();

    public ModelAndView pantallaComunidadesDeMiembro(Request req, Response res) {
        String idMiembro = req.params(":id");
        List<MiembroComunidad> comunidades = obtenerMiembroComunidades(idMiembro);

        System.out.println("Comunidades de miembro: " + comunidades.size());

        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        // Renderizar vista con Handlebars
          return new ModelAndView(model, "comunidadDeMiembroLiviano.hbs");
    }

    public ModelAndView cambiarTipoMiembroLiviano(Request req, Response res) {
        String idMiembro = req.params(":id");
        String idComunidad = req.params(":comunidadId");
        String nuevoTipoMiembro = req.queryParams("nuevoTipoMiembro");

        MiembroComunidad miembroComunidad = repoMiembroComunidad.buscarPorMiembroYComunidad(Integer.parseInt(idComunidad), Integer.parseInt(idMiembro));
        miembroComunidad.setTipoMiembro(TipoMiembro.valueOf(nuevoTipoMiembro));
        repoMiembroComunidad.actualizar(miembroComunidad);

        res.redirect("/miembro/" + idMiembro + "/comunidadesLiviano");
        return null;
    }


    public String cambiarTipoMiembroPesado(Request req, Response res) {
        res.type("application/json");
        try {
            String idMiembro = req.params(":id");
            String idComunidad = req.params(":comunidadId");

            // Recupera el cuerpo de la solicitud y analiza el JSON
            JsonObject json = new Gson().fromJson(req.body(), JsonObject.class);
            String nuevoTipoMiembro = json.get("nuevoTipoMiembro").getAsString();

            MiembroComunidad miembroComunidad = repoMiembroComunidad.buscarPorMiembroYComunidad(Integer.parseInt(idComunidad), Integer.parseInt(idMiembro));
            miembroComunidad.setTipoMiembro(TipoMiembro.valueOf(nuevoTipoMiembro));
            repoMiembroComunidad.actualizar(miembroComunidad);
            return "{\"status\":\"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            res.status(500);
            return "{\"status\":\"error\", \"message\":\"" + e.getClass().getSimpleName() + ": " + e.getMessage() + "\"}";
        }
    }

    public List<MiembroComunidad> obtenerMiembroComunidades(String idMiembro) {
        Integer aux = Integer.parseInt(idMiembro);
        return repoMiembroComunidad.obtenerMiembroComunidades(aux);
    }


    public ModelAndView pantallaMiembroComunidadPesado(Request req, Response res) {
        String idMiembro = req.params(":id");
        List<MiembroComunidad> comunidades = obtenerMiembroComunidades(idMiembro);

        Map<String, Object> model = new HashMap<>();
        model.put("comunidades", comunidades);
        model.put("miembroId", idMiembro);

        return new ModelAndView(model, "comunidadDeMiembroPesado.hbs");
    }

    public List<MiembroComunidadDTO> miembroComunidadDTO(List<MiembroComunidad> comunidades){
        return comunidades.stream()
                .map(mc -> new MiembroComunidadDTO(mc.getComunidad().getNombre(), mc.getMiembro().getNombre(), mc.getTipoMiembro().toString()))
                .collect(Collectors.toList());
    }

}
