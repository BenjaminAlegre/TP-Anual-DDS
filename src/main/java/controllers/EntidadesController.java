package controllers;

import model.repositorios.RepositorioEntidades;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

public class EntidadesController {

    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();

    public String obtenerEntidades(Request request, Response response) {

        return ExponedorDeRecursos.exponerRecurso(repositorioEntidades.buscarTodos().stream().map(e -> e.convertirADTO()).collect(Collectors.toList()), response );
    }

    public String obtenerEntidadesPorTipo(Request request, Response response) {
        String tipo = request.queryParams("tipoEntidad");
        return ExponedorDeRecursos.exponerRecurso(repositorioEntidades.buscarPorTipo(tipo).stream().map(e -> e.convertirADTO()).collect(Collectors.toList()), response);

    }
}
