package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.tiposDeActividad;



import lombok.Setter;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.Actividad;
import mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums.TipoDeConsumo;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("dato_electrica")
public class DatoElectrica extends DatoDeEmision {

    public DatoElectrica() {
        this.setActividad(Actividad.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA);
        this.setTipo(TipoDeConsumo.ELECTRICIDAD);
    }

  /*  public UnidadDeCarbono obtenerFeDato(this){
        return getGestorDeFactores().obtenerFeElectrica();
    }*/

}