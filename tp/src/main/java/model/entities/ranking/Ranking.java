package model.entities.ranking;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    List<String> mayorTiempoDeCierre;
    List<String> mayorCantidadIncidentes;
    List<String> mayorImpacto;

    public Ranking(List<String> mayorTiempoDeCierre, List<String> mayorCantidadIncidentes, List<String> mayorImpacto) {
        this.mayorTiempoDeCierre = new ArrayList<>();
        this.mayorCantidadIncidentes = new ArrayList<>();
        this.mayorImpacto = new ArrayList<>();
    }

    public Ranking(){

    }
    /*
    private List<Entidad> mayorTiempoDeCierre(){

    }
    private List<Entidad> mayorCantidadIncidentes(){

    }
    private List<Entidad> mayorImpacto(){

    }*/
}
