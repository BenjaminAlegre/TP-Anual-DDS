package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad;


import mi_impacto_ambiental.models.entities.UnidadDeCarbono;
import mi_impacto_ambiental.models.entities.organizacion.Organizacion;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.*;
import mi_impacto_ambiental.models.entities.organizacion.utils.huellaCarbono.factoresEmision.GestorDeFactores;
import mi_impacto_ambiental.models.entities.organizacion.utils.huellaCarbono.factoresEmision.GestorFactores;
import mi_impacto_ambiental.models.entities.persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "dato_de_emision")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_de_dato")
public class DatoDeEmision extends EntidadPersistente {
    private Actividad actividad;
    private TipoDeConsumo tipo;
    private String valor;
    private Periodicidad periodicidad;
    private String periodoDeImputacion;
    private  String anio;
    private String mes;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Organizacion organizacion;

    private Double hc_valor;
    private String hc_unidad;



    public DatoDeEmision(Actividad actividad, TipoDeConsumo tipo, String valor, Periodicidad periodicidad, String periodoDeImputacion) {

        this.actividad = actividad;
        this.tipo = tipo;
        this.valor = valor;
        this.periodicidad = periodicidad;
        this.periodoDeImputacion = periodoDeImputacion;
        this.definirAnioYMes(periodoDeImputacion, periodicidad);
//        UnidadDeCarbono hc = UnidadDeCarbono.multiplicar(fe, Double.valueOf(this.valor));
//        this.hc_valor = hc.getValor();
//        this.hc_unidad = hc.getUnidad();

    }

    public DatoDeEmision() {

    }

    public void definirAnioYMes(String periodoDeImputacion, Periodicidad periodicidad) {
        if(periodicidad.equals(Periodicidad.ANUAL)){
            this.anio = periodoDeImputacion;
        }else {
            this.anio = this.periodoDeImputacion.split("/")[1];
            this.mes = this.periodoDeImputacion.split("/")[0];
        }
    }

    public ProductoTransportado getCategoria() {
        return null;
    }

    public TipoDeVehiculo getVehiculo() {
        return null;
    }

    public String getDistanciaRecorrida() {
        return null;
    }

    public String getPeso() {
        return null;
    }


    public UnidadDeCarbono obtenerHc() {
        return new UnidadDeCarbono(this.hc_valor, this.hc_unidad);
    }

    /*public UnidadDeCarbono obtenerFeYLuegoHc() {
        this.obtenerFeDato();
        //return new UnidadDeCarbono(hc_valor, hc_unidad);
        return UnidadDeCarbono.multiplicar(fe, Double.valueOf(valor));
    }*/


    public UnidadDeCarbono obtenerHCMensual() {

        if (periodicidad == Periodicidad.ANUAL) {
            Double valor = hc_valor / 12;
            return UnidadDeCarbono.obtenerUnidad(valor);
        } else {
            return this.obtenerHc();
        }
    }

    public boolean esAplicable(String anio) {
       return this.anio.equals(anio);
    }

    public boolean esAplicable(String anio, String mes) {
        return this.anio.equals(anio) & this.mes.equals(mes);
    }

    public DatoDeEmision definirHC(UnidadDeCarbono fe){
       UnidadDeCarbono hc =  UnidadDeCarbono.multiplicar(fe,Double.valueOf(this.valor));
        this.hc_valor = hc.getValor();
        this.hc_unidad = hc.getUnidad();
        return this;
    }

    @Override
    public String toString() {
        return "DatoDeEmision{" +
                "actividad=" + actividad +
                ", tipo=" + tipo +
                ", valor='" + valor + '\'' +
                ", periodicidad=" + periodicidad +
                ", periodoDeImputacion='" + periodoDeImputacion + '\'' +
                ", organizacion=" + organizacion +
                ", hc=" + hc_valor +
                ", unidad=" + hc_unidad+
                '}';
    }
}
