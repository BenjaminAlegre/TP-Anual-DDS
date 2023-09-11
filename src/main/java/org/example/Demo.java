package org.example;

import org.example.Competencia.Competencia;
import org.example.Ubicacion.Ubicacion;
import org.example.Corredor;
import utils.BDUtils;
import javax.persistence.EntityManager;

public class Demo {

    public static void main(String[] args) {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        em.persist(new Corredor());

        BDUtils.commit(em);
    }

}
