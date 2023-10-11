package model.repositorios.localizacion;

import db.EntityManagerHelper;
import model.entities.localizacion.Provincia;
import model.entities.normalizaciondirecciones.adapters.ServicioGeoDds;
import model.entities.normalizaciondirecciones.adapters.ServicioNormalizacion;

import java.io.IOException;
import java.util.List;


public class RepositorioProvincias {

   private ServicioNormalizacion api = new ServicioGeoDds();

    public RepositorioProvincias() throws Exception {
    }

    public void agregar(Provincia provincia) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(provincia);
        EntityManagerHelper.commit();
    }
    public List<Provincia> buscarTodos() throws IOException {
       List<Provincia> provincias = this.traerDeBaseDeDatos();
       if(provincias == null)
           return this.api.listadoProvincias();
       else
           return provincias;
    }

    public Provincia buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Provincia.class, id);
    }


    public List<Provincia> traerDeBaseDeDatos() {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Provincia.class.getName())
                .getResultList();
    }
}