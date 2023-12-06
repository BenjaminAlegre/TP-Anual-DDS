package services;

import DTO.IncidenteDTO;
import model.entities.comunidad.Comunidad;
import model.entities.entidades.Entidad;
import model.entities.entidades.Establecimiento;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.Servicio;
import model.repositorios.RepositorioComunidades;
import model.repositorios.RepositorioEntidades;
import model.repositorios.RepositorioEstablecimientos;
import model.repositorios.RepositorioServicios;
import model.repositorios.incidentes.RepositorioIncidentes;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class IncidenteService {
    private RepositorioIncidentes repoIncidentes = new RepositorioIncidentes();
    private RepositorioServicios repoServicios = new RepositorioServicios();
    private RepositorioEntidades repoEntidades = new RepositorioEntidades();
    private RepositorioEstablecimientos repoEstablecimientos = new RepositorioEstablecimientos();
    private RepositorioComunidades repoComunidades = new RepositorioComunidades();

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

            LocalDateTime horarioApertura = LocalDateTime.now();

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
            incidente.setHorarioCierre(LocalDateTime.now());
            repoIncidentes.actualizar(incidente);
        }else {
            throw new RuntimeException("No se encontro el incidente");
        }
    }

    public List<Incidente> obtenerTodos() {
        return repoIncidentes.obtenerTodos();
    }

    public List<Incidente> obtenerPorEstado(String estado) {
        return repoIncidentes.buscarPorEstado(estado);
    }

    public List<IncidenteDTO> obtenerPorEstadoToDTO(String estado) {
        List<Incidente> incidentes = repoIncidentes.buscarPorEstado(estado);
        return incidentes.stream()
                .map(incidente -> new IncidenteDTO(incidente.getId(), incidente.getEstado().toString(), incidente.getObservaciones()))
                .collect(Collectors.toList());
    }

    public List<IncidenteDTO> obtenerPorEstadoYComunidadToDTO(String estado, String idComunidad) {
        Comunidad comunidad = repoComunidades.buscarPorId(Integer.parseInt(idComunidad));
        List<Incidente> incidentes = repoIncidentes.buscarPorEstadoYComunidad(estado, comunidad);
        return incidentes.stream()
                .map(incidente -> new IncidenteDTO(incidente.getId(), incidente.getEstado().toString(), incidente.getObservaciones()))
                .collect(Collectors.toList());
    }



}
