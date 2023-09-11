import model.entities.ranking.GestorDeRankings;
import model.entities.ranking.Rankeador;

public class TestCron {

    public static class Main {
        public static void main(String[] args) {
            Rankeador rankeador = new Rankeador();
            GestorDeRankings gestor = new GestorDeRankings(rankeador);
            gestor.ejecutar();
        }
    }
}
