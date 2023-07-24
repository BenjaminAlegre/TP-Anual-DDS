package model.entities.ranking;

import model.entities.entidades.Entidad;
import model.entities.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Rankeador {
    private List<Incidente> incidentes;

    public Rankeador(List<Incidente> incidentes) {
        this.incidentes = new ArrayList<>();
    }
/*
    public Ranking makeRanking(){
        return new Ranking(
                this.mayorTiempoDeCierre(),
                this.mayorCantidadIncidentes(),
                this.mayorImpacto()

                //TODO hacer logica de los metodos
        )
    }

    private List<Entidad> mayorTiempoDeCierre(){

    }
    private List<Entidad> mayorCantidadIncidentes(){

    }
    private List<Entidad> mayorImpacto(){

    }*/
}
