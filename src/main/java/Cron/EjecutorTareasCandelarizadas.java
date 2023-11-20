package Cron;


import model.entities.ranking.Rankeador;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class EjecutorTareasCandelarizadas {

    private SchedulerFactory crearScheduler = new StdSchedulerFactory();
    private Scheduler scheduler;

    {
        try {
            scheduler = crearScheduler.getScheduler();
        } catch (SchedulerException e) {
            System.out.print("Error al crear scheduler");
        }
    }

    JobDetail job = JobBuilder.newJob(NotificadorPorHorario.class)
            .withIdentity("enviarNotificacionPorHorario")
            .build();

    Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("trigger")
            .withSchedule(CronScheduleBuilder.cronSchedule("0,15,30,45 * * * *"))
            .build();

    public void enviarNotificacionesPorHorario() {
        try {
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
        }
    }

    JobDetail job2 = JobBuilder.newJob(Rankeador.class)
            .withIdentity("generarRankings")
            .build();

    Trigger trigger2 = TriggerBuilder.newTrigger()
            .withIdentity("trigger")
            .withSchedule(CronScheduleBuilder.cronSchedule("0 23 ? * SUN"))
            .build();

    public void generarRankings() {
        try {
            scheduler.start();
            scheduler.scheduleJob(job2, trigger2);
        } catch (SchedulerException e) {
            System.out.print("Error al ejecutar scheduler");
        }
    }

}

