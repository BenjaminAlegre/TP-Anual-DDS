package Cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class NotificadorPorHorario implements Job {



    public void execute(JobExecutionContext context) {//TODO
        //obtener horario
        //traer los miembros con el horario de notificacion correrpondiente
        // notifiacar a cada uno
        System.out.print("Enviando por horario");
    }
}
