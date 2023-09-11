package model.entities.ranking;

import java.util.TimerTask;

public class GenerarRanking extends TimerTask {
    private Rankeador rankeador;

    @Override
    public void run() {
        generarInforme();
    }

    public void generarInforme(){
        //System.out.println("Se esta ejecutando la tarea");

        Ranking ranking = this.rankeador.makeRanking();
        this.rankeador.agregarRanking(ranking);
    }
}
