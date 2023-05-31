package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums;

import java.util.HashMap;
import java.util.Map;

public enum Periodicidad {
    ANUAL("ANUAL"),
    MENSUAL("MENSUAL");

    public final String label;

    private Periodicidad(String label){
        this.label = label;
    }



    public static final Map<String, Periodicidad> BY_LABEL = new HashMap<>();
    static {
        for (Periodicidad a : values()) {
            BY_LABEL.put(a.label, a);
        }
    }



    public static Periodicidad valueOfPeriodicidad( String label){
        return BY_LABEL.get(label);
    }

    }


