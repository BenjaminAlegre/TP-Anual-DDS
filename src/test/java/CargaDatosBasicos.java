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

    // estos test son mas extensibles y escalables ya que se peuden agregar mas lineas y estaciones solo con poner nombres
    // ademas se podria agregar que se le agrege la localizacion y ubicaciones solo agregando mas arrays y recorrerlos

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioComunidades repositorioComunidades = new RepositorioComunidades();
    RepositorioEntidades repositorioEntidades = new RepositorioEntidades();
    RepositorioIncidentes repositorioIncidentes = new RepositorioIncidentes();
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    RepositorioServicios repositorioServicios = new RepositorioServicios();


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

        Usuario usuario = new Usuario("juanrodriguez10@example.com", "1234", Rol.USUARIO);
        Usuario usuario2 = new Usuario("maria1234@example.com", "1234", Rol.USUARIO);
        Usuario usuario3 = new Usuario("matiasgarcia@example.com", "1234", Rol.ADMINSTRADOR);
        repositorioUsuarios.agregar(usuario);
        repositorioUsuarios.agregar(usuario2);
        repositorioUsuarios.agregar(usuario3);
    }



    @Test
    public void cargarMiembros(){
        String[][] miembros = {{"Lionel", "Messi", "miembro@miembro.com","+541138157280"},
                {"Rodrigo", "De Paul", "entidad@entidad.com", "+5491138157280"},
                {"Angel", "Di María", "administrador@admnistrador.com", "+5491138157280"}};
        for (String[] dato: miembros
             ) {
            Miembro miembro = new Miembro(dato[0], dato[1], dato[2], dato[3]);
            miembro.setMedioNotificacion(MedioNotificacion.WHATSAPP);
            repositorioMiembros.agregar(miembro);
        }

    }


    @Test
    public void cargarComunidades() {

        String[] comunidades = {"Baños de Subtes", "Escaleras de subtes", "Ascensores Lineas", "Baños Supermercados" };
        //Cargar comunidades
        for (String nombre: comunidades
             ) {
            Comunidad comunidad = new Comunidad(nombre);
            repositorioComunidades.agregar(comunidad);
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







}
