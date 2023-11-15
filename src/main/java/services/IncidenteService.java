package services;

import model.entities.entidades.Entidad;
import model.entities.entidades.Establecimiento;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Servicio;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioEstablecimientos;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.List;

public class IncidenteService {
    private RepositorioIncidentes repoIncidentes = new RepositorioIncidentes();
    private RepositorioServicios repoServicios = new RepositorioServicios();

    private RepositorioEntidades repoEntidades = new RepositorioEntidades();
    private RepositorioEstablecimientos repoEstablecimientos = new RepositorioEstablecimientos();
    public void guardarIncidente(Request req) {

        try {
            Integer servicioId = Integer.parseInt(req.queryParams("servicioId"));
            String observaciones = req.queryParams("observaciones");
            String entidadIdStr = req.queryParams("entidadId");

            Integer entidadId = null;
            if (entidadIdStr != null && !entidadIdStr.isEmpty()) {
                entidadId = Integer.parseInt(entidadIdStr);
            } else{
                throw new Exception("Error ID entidad");
            }

            Entidad entidadAfectada = null;
            if (entidadId != null) {
                entidadAfectada = repoEntidades.buscarPorId(entidadId);
                if (entidadAfectada == null) {
                    throw new Exception("No se encontro una entidad");
                }
            } else{
                throw new Exception("Error id entidad null");
            }

            Servicio servicio = repoServicios.buscarPorId(servicioId);

            LocalDate horarioApertura = LocalDate.now();

            Incidente incidente = new Incidente();
            incidente.setEntidadAfectada(entidadAfectada);
            incidente.setObservaciones(observaciones);
            incidente.setHorarioApertura(horarioApertura);
            incidente.setServicioAfectado(servicio);


            // Persistir el objeto Incidente
            repoIncidentes.guardar(incidente);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarIncidente(Integer id) {
        Incidente incidente = repoIncidentes.buscarPorId(id);
        if (incidente != null) {
            incidente.setEstado(EstadoIncidente.CERRADO);
            repoIncidentes.actualizar(incidente);
        }else {
            throw new RuntimeException("No se encontro el incidente");
        }
    }

    public List<Incidente> obtenerTodos() {
        return repoIncidentes.obtenerTodos();
    }
}
