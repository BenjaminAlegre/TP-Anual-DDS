package model.entities.ranking;

import lombok.Getter;
import lombok.Setter;
import model.entities.notificacion.Incidente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Rankeador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdRankeador;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Incidente> incidentes;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Incidente> rankings; //se va a mostrar solo el ultimo ranking

    @Transient
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

    }
*/

}
