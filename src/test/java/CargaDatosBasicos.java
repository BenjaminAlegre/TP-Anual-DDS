import model.entities.comunidad.*;
import model.entities.entidades.*;
import model.entities.localizacion.Provincia;
import model.entities.notificacion.EstadoIncidente;
import model.entities.notificacion.Incidente;
import model.entities.servicio.*;
import model.repositorios.*;
import model.repositorios.incidentes.RepositorioIncidentes;
import model.repositorios.localizacion.RepositorioProvincias;
import model.repositorios.rankings.RepositorioTramo;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static model.entities.entidades.TipoOrganizacion.BANCO;
import static model.entities.entidades.Transporte.FERROCARRIL;
import static model.entities.entidades.Transporte.SUBTE;

public class CargaDatosBasicos {

    // Despues de la carga de datos hay que correr el  TestRanking
    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioServicios repositorioServicios = new RepositorioServicios();
    RepositorioMiembroComunidad repositorioMiembroComunidad = new RepositorioMiembroComunidad();


    @Test
    public void traerProvinciasDeAPI() throws Exception {
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        List<Provincia> provincias =repositorioProvincias.buscarTodos();
        for (Provincia p: provincias
        ) {
            System.out.println(p.id);
            System.out.println(p.nombre);
            System.out.println(" ");
        }

        Assert.assertEquals(provincias.size(),24);
    }

    @Test
    public void cargarUsuario(){
        String[][] usuarios = {{"juanrodriguez10@example.com", "1234"},
                            {"maria1234@example.com", "1234"},
                            {"matiasgarcia@example.com", "1234"}};

        Usuario usuario = new Usuario("miembro@miembro.com", "1234", Rol.USUARIO);
        Usuario usuario2 = new Usuario("entidad@entidad.com", "1234", Rol.USUARIO);
        Usuario usuario3 = new Usuario("administrador@admnistrador.com", "1234", Rol.ADMINSTRADOR);
        repositorioUsuarios.agregar(usuario);
        repositorioUsuarios.agregar(usuario2);
        repositorioUsuarios.agregar(usuario3);
    }



    @Test
    public void cargarMiembrosYComunidades(){
        String[][] miembros = {{"Lionel", "Messi", "miembro@miembro.com","+541138157280"},
                {"Rodrigo", "De Paul", "entidad@entidad.com", "+5491138157280"},
                {"Angel", "Di María", "administrador@admnistrador.com", "+5491138157280"},
                {"Dibu", "Martinez", "miembro2@miembro.com", "+5491138157280"}};
        for (String[] dato: miembros
             ) {
            Miembro miembro = new Miembro(dato[0], dato[1], dato[2], dato[3]);
            miembro.setMedioNotificacion(MedioNotificacion.WHATSAPP);
            repositorioMiembros.agregar(miembro);
        }

        cargarComunidades();
        cargarIncidentes();
        testAgregarMiembroAComunidadTipo();

    }

    private void cargarComunidades() {

        String[] comunidades = {"Baños de Subtes", "Escaleras de subtes", "Ascensores Lineas", "Baños Supermercados", "Comunidad embarazo", "Comunidad Elevadores", "Comunidad Ascensor" , "Comunidad Molinetes", "Comunidad Accesibilidad", "Comunidad Seguridad", "Comunidad Infantes"};
        //Cargar comunidades
        for (String nombre: comunidades
             ) {
            Comunidad comunidad = new Comunidad(nombre);
            repositorioComunidades.agregar(comunidad);
        }
    }

    private void cargarIncidentes(){
        for (int i = 1; i<= 16; i++){
            Incidente incidente = new Incidente();
//            incidente.setReportador("Reportador " + i);
            incidente.setObservaciones("Observaciones del incidente numero: " + i);
            incidente.setEstado(EstadoIncidente.ACTIVO);
            incidente.setHorarioApertura(LocalDateTime.now());
            incidente.setHorarioCierre(LocalDateTime.now().plusDays(5));

            Servicio servicio = repositorioServicios.buscarPorId(i);
            Entidad entidadAfectada = repositorioEntidades.buscarPorId(i);
            Comunidad comunidad = repositorioComunidades.buscarPorId(i);
            List<Comunidad> comunidades2 = new ArrayList<>();
            comunidades2.add(comunidad);

            incidente.setServicioAfectado(servicio);
            incidente.setEntidadAfectada(entidadAfectada);
            incidente.setComunidades(comunidades2);

            repositorioIncidentes.guardar(incidente);

            System.out.println("Incidente " + i + " guardado");
        }

    }



    @Test
    public void cargarLineas() {
        String[] nombresLineasSubte = {"Linea A", "Linea B", "Linea H"};
        String[] nombresFerrocarriles = {"Sarmiento", "Roca", "Belgrano Norte"};
        String[][] estacionesSubte = {{"Plaza Miserere", "Loria", "Pasco"},{"Medrano", "Carlos Gardel", "Pueyrredon"},{"Corrientes", "Cordoba", "Once"}};
        String[][] estacionesTrenes ={{"Once", "Caballito", "Flores"}, {"Gerli", "Lanus", "Remedios de Escalada"}, {"Retiro", "Saldias", "R. S. Ortiz"}};
        for (String subte: nombresLineasSubte
             ) {
            int contador = 0;
            LineaDeTransporte linea = new LineaDeTransporte();
            linea.setNombre(subte);
            linea.setTipo(SUBTE);
            this.agregarEstaciones(linea,estacionesSubte[contador]);
            repositorioEntidades.agregar(linea);
            contador ++;
        }
        for (String tren: nombresFerrocarriles
        ) {
            int contador = 0;
            LineaDeTransporte linea = new LineaDeTransporte();
            linea.setNombre(tren);
            linea.setTipo(FERROCARRIL);
            this.agregarEstaciones(linea, estacionesTrenes[contador]);
            repositorioEntidades.agregar(linea);
            contador ++;
        }

    }

    private void agregarEstaciones(LineaDeTransporte linea, String[] estacionesSubte) {
        for (String estacion: estacionesSubte
        ) {
                Estacion est = new Estacion();
                est.setNombre(estacion);
                linea.agregarEstacion(est);
                agregarBanios(est);
                agregarMediosDeElevacion(est);
            }
    }



    private void agregarBanios(Establecimiento est) {
        for (TipoDeBanio tipo: TipoDeBanio.values()
        ) {
            Banio banio = new Banio(tipo);
            est.agregarMonitoreable(banio);
        }
    }

    private void agregarMediosDeElevacion(Estacion est) {


        for (TipoDeElevacion tipo: TipoDeElevacion.values()
             ) {
            MedioElevacion medioElevacion = new MedioElevacion(tipo, Tramo.tramo(PuntosTramo.ANDEN, PuntosTramo.ACCESO_A_TRANSPORTE));
            MedioElevacion medioElevacion2 = new MedioElevacion(tipo, Tramo.tramo(PuntosTramo.ANDEN, PuntosTramo.ACCESO_A_TRANSPORTE));
            est.agregarMonitoreable(medioElevacion);
            est.agregarMonitoreable(medioElevacion2);
        }
    }

    @Test
    public void cargarBancos() {
        String[] nombresBancos = {"BBVA", "ICBC", "Santander"};
        String[] sucursales = {"Sucursal A", "Sucursal B", "Sucursal C"};

        for (String banco: nombresBancos
             ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(banco);
            organizacion.setTipo(BANCO);
            this.agregarSucursales(organizacion, sucursales);

            repositorioEntidades.agregar(organizacion);
        }
    }

    @Test
    public void cargarSupermercados(){
        String[] nombresOrganizaciones = {"Coto", "Dia", "Carretfour"};
        String[] sucursales ={"Sucursal 01", "Sucursal 02", "Sucursal 03"};

        for (String nombre: nombresOrganizaciones
             ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(nombre);
            organizacion.setTipo(TipoOrganizacion.SUPERMERCADO);
            this.agregarSucursales(organizacion, sucursales);
            repositorioEntidades.agregar(organizacion);
        }
    }
    @Test
    public void cargarMasSupermercados(){
        String[] nombresOrganizaciones = {"Vea", "Diarco", "Jumbo"};
        String[] sucursales ={"Sucursal 01", "Sucursal 02", "Sucursal 03"};

        for (String nombre: nombresOrganizaciones
        ) {
            Organizacion organizacion = new Organizacion();
            organizacion.setNombre(nombre);
            organizacion.setTipo(TipoOrganizacion.SUPERMERCADO);
            this.agregarSucursales(organizacion, sucursales);
            repositorioEntidades.agregar(organizacion);
        }
    }



    private void agregarSucursales(Organizacion organizacion, String[] sucursales) {
        for (String nombreSucursal : sucursales
        ) {
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(nombreSucursal);
            organizacion.agregarSucursal(sucursal);
            agregarBanios(sucursal);
        }
    }

    private void testAgregarMiembroAComunidadTipo() {

        Miembro m1 = repositorioMiembros.buscarPorId(1);
        Comunidad comunidad1 = repositorioComunidades.buscarPorId(1);
        MiembroComunidad miembroComunidad = new MiembroComunidad(comunidad1, m1, TipoMiembro.OBSERVADOR);
        repositorioMiembroComunidad.agregar(miembroComunidad);

        Comunidad comunidad2 = repositorioComunidades.buscarPorId(2);
        MiembroComunidad miembroComunidad2 = new MiembroComunidad(comunidad2, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad2);
        Comunidad comunidad9 = repositorioComunidades.buscarPorId(9);
        MiembroComunidad miembroComunidad9 = new MiembroComunidad(comunidad9, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad9);
        Comunidad comunidad10 = repositorioComunidades.buscarPorId(10);
        MiembroComunidad miembroComunidad10 = new MiembroComunidad(comunidad10, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad10);
        Comunidad comunidad11 = repositorioComunidades.buscarPorId(11);
        MiembroComunidad miembroComunidad11 = new MiembroComunidad(comunidad11, m1, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad11);

        Miembro m3 = repositorioMiembros.buscarPorId(2);
        Comunidad comunidad3 = repositorioComunidades.buscarPorId(3);
        MiembroComunidad miembroComunidad3 = new MiembroComunidad(comunidad3, m3, TipoMiembro.OBSERVADOR);
        repositorioMiembroComunidad.agregar(miembroComunidad3);

        Miembro m4 = repositorioMiembros.buscarPorId(3);
        Comunidad comunidad4 = repositorioComunidades.buscarPorId(4);
        MiembroComunidad miembroComunidad4 = new MiembroComunidad(comunidad4, m4, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad4);

        Miembro m5 = repositorioMiembros.buscarPorId(4);
        Comunidad comunidad5 = repositorioComunidades.buscarPorId(5);
        Comunidad comunidad6 = repositorioComunidades.buscarPorId(6);
        Comunidad comunidad7= repositorioComunidades.buscarPorId(7);
        Comunidad comunidad8 = repositorioComunidades.buscarPorId(8);
        MiembroComunidad miembroComunidad5 = new MiembroComunidad(comunidad5, m5, TipoMiembro.OBSERVADOR);
        MiembroComunidad miembroComunidad6 = new MiembroComunidad(comunidad6, m5, TipoMiembro.AFECTADO);
        MiembroComunidad miembroComunidad7 = new MiembroComunidad(comunidad7, m5, TipoMiembro.AFECTADO);
        MiembroComunidad miembroComunidad8 = new MiembroComunidad(comunidad8, m5, TipoMiembro.AFECTADO);
        repositorioMiembroComunidad.agregar(miembroComunidad5);
        repositorioMiembroComunidad.agregar(miembroComunidad6);
        repositorioMiembroComunidad.agregar(miembroComunidad7);
        repositorioMiembroComunidad.agregar(miembroComunidad8);

    }


}
