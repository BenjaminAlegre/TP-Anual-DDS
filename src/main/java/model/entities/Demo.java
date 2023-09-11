import model.entities.localizacion.*;

import utils.BDUtils;

import javax.persistence.EntityManager;

public class Demo {

    public static void main(String[] args) {
        
        EntityManager em = BDUtils.getEntityManager();
        BDUtils.comenzarTransaccion(em);
        
        em.persist(new Provincia());
        
        BDUtils.commit(em);
    }
    
}
