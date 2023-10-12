import model.entities.localizacion.Departamento;
import model.entities.localizacion.Localizacion;
import model.entities.localizacion.Provincia;
import org.junit.jupiter.api.Test;

public class TestMunicipios {

    @Test
    public void bucarporLocalizaicion(){
        Provincia buenosAires = new Provincia();

        Departamento departamento = new Departamento();

        System.out.println(this.devolverCalse(buenosAires));
        System.out.println(this.devolverCalse(departamento));
    }


    public String devolverCalse(Localizacion localizacion){
        if(localizacion.getClass().getName() == "Provincia"){
            System.out.println(localizacion.getClass().getName());
            return "si";
        }
        else
            return "no";
    }


}
