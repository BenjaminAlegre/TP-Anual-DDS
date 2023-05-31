package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeVehiculo {
    CAMION_DE_CARGA("CAMION"),
    UITILITARIO_LIVIANO("UTILITARIO");
    public final String label;

    private TipoDeVehiculo(String label){
        this.label = label;
    }



    public static final Map<String, TipoDeVehiculo> BY_LABEL = new HashMap<>();
    static {
        for (TipoDeVehiculo a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static TipoDeVehiculo valueOfTipoDeVehiculo( String label){
        return BY_LABEL.get(label);
    }

}
