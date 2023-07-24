package model.entities.ranking;

import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.incidentes.Incidente;

import java.util.ArrayList;
import java.util.List;

public class Rankeador {
    private List<Incidente> incidentes;
    private List<Ranking> rankings; //se va a mostrar solo el ultimo ranking

    public Rankeador(List<Incidente> incidentes) {
        this.incidentes = new ArrayList<>();
    }

    public Rankeador(){

    }
    public Ranking makeRanking(){
        return new Ranking();
    }

    public void agregarRanking(Ranking ranking) {
        rankings.add(ranking);
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
