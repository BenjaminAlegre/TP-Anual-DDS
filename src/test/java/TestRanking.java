import controllers.RankingsController;
import model.entities.entidades.Entidad;
import model.entities.ranking.PosicionRanking;
import model.entities.ranking.Rankeador;
import model.repositorios.rankings.RepositorioRankings;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestRanking {

    @Test
    public void generarTest(){
        Rankeador rankeador= new Rankeador();
        List<Entidad> entidades = rankeador.repositorioEntidades.buscarTodos();
        rankeador.generarRankings(entidades);
    }

    @Test
    public void trearRanking(){
        RepositorioRankings repositorioRankings = new RepositorioRankings();
        List<PosicionRanking> posiciones = repositorioRankings.obtenerRanking("RankCantidadIncidentes");
        for (PosicionRanking p:posiciones
             ) {
            System.out.println(p.getPosicion()+" "+p.getEntidad().getNombre());
        }

    }
}
