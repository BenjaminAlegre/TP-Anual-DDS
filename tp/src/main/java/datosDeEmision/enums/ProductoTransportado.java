package mi_impacto_ambiental.models.entities.organizacion.utils.ImportadorDeDatos.src.main.java.datosDeEmision.enums;


import java.util.HashMap;
import java.util.Map;

public enum ProductoTransportado {
    MATERIA_PRIMA("MATERIA_PRIMA"),
    INSUMO("INSUMOS"),
    PRODUCTOS_VENDIDOS("PRODUCTOS_VENDIDOS"),
    RESIDUOS("REIDUOS");


    public final String label;

    private ProductoTransportado(String label){
        this.label = label;
    }



    public static final Map<String, ProductoTransportado> BY_LABEL = new HashMap<>();
static {
        for (ProductoTransportado a : values()) {
        BY_LABEL.put(a.label, a);
        }
        }



public static ProductoTransportado valueOfProductoTransportados( String label){
        return BY_LABEL.get(label);
        }

}
