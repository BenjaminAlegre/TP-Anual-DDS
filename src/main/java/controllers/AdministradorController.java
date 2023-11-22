package controllers;

import cargaDeDatosMasiva.Importador;
import cargaDeDatosMasiva.ImportadorCSVAdaptada;
import lombok.Getter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AdministradorController {

    ImportadorCSVAdaptada importador = new Importador();

    public ModelAndView pantallaCargaMasiva(Request request, Response response) {
     return new ModelAndView(null, "cargaMasiva.hbs");   
    }

    public Response cargarDatos(Request request, Response response) {

        File uploadDir = new File("upload");
        uploadDir.mkdir();
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: CARGANDO DATOS....");
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: PASO PRIMER TRY ....");
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

        try (InputStream input = request.raw().getPart("archivoInput").getInputStream()) {
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: PRIMER TRY ....");

            CargadorPersonasJuridicas cargadorPersonasJuridicas =new CargadorPersonasJuridicas(input, tempFile);
            cargadorPersonasJuridicas.start();


        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        response.redirect("/login");
        return response;
    }


    public class CargadorPersonasJuridicas extends Thread {
        private Path tempFile;

        @Getter
        private InputStream input;
        public void run() {


            try {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: LLAMA A IMPORTADOR");
                 importador.generarObjetos(tempFile.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: DATOS CARGADOS");
        }

        public CargadorPersonasJuridicas(InputStream input, Path tempFile) {
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: CREANDO CARGADOR....");

            this.input = input;
            this.tempFile = tempFile;
        }
    }
}
