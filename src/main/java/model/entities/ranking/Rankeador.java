package model.entities.ranking;

import model.entities.comunidad.Miembro;
import model.entities.entidades.Entidad;
import model.entities.notificacion.Incidente;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Entity
public class Rankeador {
    @Id
    private Integer IdRankeador;

    @OneToMany
    private List<Incidente> incidentes;

    @OneToMany
    private List<Incidente> rankings; //se va a mostrar solo el ultimo ranking

    @OneToOne
    private RankStrategy rankeador;




    public Rankeador(List<Incidente> incidentes) {
        this.incidentes = new ArrayList<>();
    }

    public Rankeador(){

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

    public Integer getIdRankeador() {
        return IdRankeador;
    }

    public void setIdRankeador(Integer idRankeador) {
        IdRankeador = idRankeador;
    }

    public List<Incidente> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<Incidente> incidentes) {
        this.incidentes = incidentes;
    }

    public List<Incidente> getRankings() {
        return rankings;
    }

    public void setRankings(List<Incidente> rankings) {
        this.rankings = rankings;
    }

    public RankStrategy getRankeador() {
        return rankeador;
    }

    public void setRankeador(RankStrategy rankeador) {
        this.rankeador = rankeador;
    }
}
