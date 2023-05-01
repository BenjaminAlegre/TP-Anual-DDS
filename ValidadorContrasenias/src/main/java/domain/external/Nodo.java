package mi_impacto_ambiental.models.entities.organizacion.utils.ValidadorContrasenias.src.main.java.domain.external;

public class Nodo {

        public Nodo padre;
        public Nodo izquierda;
        public Nodo derecha;
       // public Integer indice;
        public String info;

        public Nodo() {
            //indice = index;
            padre = null;
            izquierda = null;
            derecha = null;
            info = null;
        }
}

