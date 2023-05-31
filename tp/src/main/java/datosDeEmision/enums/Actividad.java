package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums;

import java.util.HashMap;
import java.util.Map;

public enum Actividad {
    COMBUSTION_FIJA("COMBUSTION_FIJA"),
    COMBUSTION_MOVIL("COMBUSTION_MOVIL"),
    ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA("ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA"),
    LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS("LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS");

    public final String label;

    private Actividad(String label){
        this.label = label;
    }



    public static final Map<String, Actividad> BY_LABEL = new HashMap<>();
    static {
        for (Actividad a : values()) {
        BY_LABEL.put(a.label, a);
        }
    }

    public static Actividad valueOfActividad( String label){
        return BY_LABEL.get(label);
    }

}
