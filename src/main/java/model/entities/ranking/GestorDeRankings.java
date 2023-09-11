package model.entities.ranking;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class GestorDeRankings {
    private static Timer timer = new Timer();
    private Rankeador rankeador;

    public GestorDeRankings(Rankeador rankeador) {
        this.rankeador = rankeador;
    }

    public void ejecutar() {

        GenerarRanking timerTask = new GenerarRanking();

        //timer.scheduleAtFixedRate(timerTask, 0, 40000);    //cada 4 segundos
        timer.scheduleAtFixedRate(timerTask, 0, TimeUnit.DAYS.toMillis(7));  //se ejecuta cada 7 dias
    }
}
