package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad;

import lombok.Getter;
import lombok.Setter;
import mi_impacto_ambiental.models.entities.UnidadDeCarbono;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Getter
@Setter
@Entity
@DiscriminatorValue("dato_logistica")
public class DatoDeLogistica extends DatoDeEmision {

    private ProductoTransportado categoria; //TODO cambbiar tipos si es necesario
    private TipoDeVehiculo vehiculo;
    private String distanciaRecorrida;
    private String peso;
    @Transient
    private int k;

    public DatoDeLogistica(Actividad actividad, TipoDeConsumo tipo, String valor, Periodicidad periodicidad, String periodoDeImputacion, ProductoTransportado categoria, TipoDeVehiculo vehiculo, String distanciaRecorrida, String peso) {
        super(actividad, tipo, valor, periodicidad, periodoDeImputacion);
        this.categoria = categoria;
        this.vehiculo = vehiculo;
        this.distanciaRecorrida = distanciaRecorrida;
        this.peso = peso;
    }

    public DatoDeLogistica() {
        this.setActividad(Actividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS);
    }

    public DatoDeLogistica definirHC(UnidadDeCarbono fe, Double k){
     UnidadDeCarbono hc =   UnidadDeCarbono.multiplicar(fe,
                k * Double.valueOf(distanciaRecorrida) * Double.valueOf(peso));
     super.setHc_valor(hc.getValor());
     super.setHc_unidad(hc.getUnidad());
     return this;
    }


    @Override
    public String toString() {
        return "DatoDeLogistica{" +
                "categoria=" + categoria +
                ", vehiculo=" + vehiculo +
                ", distanciaRecorrida='" + distanciaRecorrida + '\'' +
                ", peso='" + peso + '\'' +
                ", k=" + k +
                '}';
    }

    /*@Override
    public UnidadDeCarbono obtenerHc() {
        //UnidadDeCarbono hc = new UnidadDeCarbono();
        //hc.setValor( Double.valueOf(distanciaRecorrida) * Double.valueOf(peso) * super.getFe().getValor() * k);
        //return hc;
        return UnidadDeCarbono.multiplicar(this.getFe(),
                this.getGestorDeFactores().getK() * Double.valueOf(distanciaRecorrida) * Double.valueOf(peso));
    }

    @Override
    public UnidadDeCarbono obtenerFeYLuegoHc() {
        this.obtenerFeDato();
        return UnidadDeCarbono.multiplicar(this.getFe(),
                this.getGestorDeFactores().getK() * Double.valueOf(distanciaRecorrida) * Double.valueOf(peso));
    }*/

}
