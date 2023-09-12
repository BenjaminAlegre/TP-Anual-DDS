package model.entities;

import model.entities.comunidad.*;
import model.entities.entidades.*;
import model.entities.notificacion.*;
import model.entities.localizacion.*;
import model.entities.ranking.*;
import model.entities.servicio.*;
import utils.BDUtils;
import javax.persistence.EntityManager;

public class Demo {

    public static void main(String[] args) {

        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);

        //em.persist(new Corredor());

        BDUtils.commit(em);
    }

}
