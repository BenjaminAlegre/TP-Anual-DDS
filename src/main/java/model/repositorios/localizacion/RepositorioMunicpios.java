package model.repositorios.localizacion;

import db.EntityManagerHelper;
import model.entities.localizacion.Departamento;

import model.entities.localizacion.Municipio;
import model.entities.localizacion.Provincia;
import model.entities.normalizaciondirecciones.adapters.ServicioGeoDds;
import model.entities.normalizaciondirecciones.adapters.ServicioNormalizacion;
import model.entities.normalizaciondirecciones.entidadesDeNormalizacion.ListadoMunicipios;

import java.io.IOException;
import java.util.List;

public class RepositorioMunicpios {

    private ServicioNormalizacion api = new ServicioGeoDds();

    public RepositorioMunicpios() throws Exception {
    }

    public void agregar(Municipio municipio) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(municipio);
        EntityManagerHelper.commit();
    }

    public List<Municipio> buscarPorDepartamento(Departamento departamento) throws IOException {
        List<Municipio> persisitido = this.traerDeBaseDeDatos( departamento);
        if(persisitido == null) {
            return this.trearDesdeApi(departamento);
        }
        else
            return persisitido;
    }


    public List<Municipio> traerDeBaseDeDatos(Departamento departamento) {
        return EntityManagerHelper.getEntityManager()
                .createQuery("from" + Provincia.class.getName() + " where municipio_id='" + departamento.nombre + "'").getResultList();
    }

    private List<Municipio> trearDesdeApi(Departamento departamento) throws IOException {
        ListadoMunicipios listado = this.api.listadoMunicipiosDepartamento(departamento);
        for (Municipio m : listado.municipios
        ) {
            m.setDepartamento(listado.nombreDepartamento());
            this.agregar(m);
        }
        return  listado.municipios;
    }

}
