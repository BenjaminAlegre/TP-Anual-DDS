package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoDeConsumo {
    GAS_NATURAL("GAS_NATURAL"),
    DIESEL_GASOIL("DIESEL/GASOIL"),// TODO chequear que se ponga
    KEROSENE("KEROSENE"),
    FUEL_OIL("FUEL_OIL"),
    NAFTA("NAFTA"),
    CARBON("CARBON"),
    CARBON_DE_LEÑA("CARBON_DE_LEÑA"),
    LEÑA("LEÑA"),
    ELECTRICIDAD("ELECTRICIDAD");


    public final String label;

    private TipoDeConsumo(String label){
        this.label = label;
    }



    public static final Map<String, TipoDeConsumo> BY_LABEL = new HashMap<>();
    static {
        for (TipoDeConsumo t : values()) {
            BY_LABEL.put(t.label, t);
        }
    }

    public static TipoDeConsumo valueOfTipo( String label){
        return BY_LABEL.get(label);
    }
}