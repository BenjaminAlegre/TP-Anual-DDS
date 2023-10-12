import model.entities.comunidad.MedioNotificacion;
import model.entities.comunidad.Miembro;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestMiembro {

    Miembro miembro = new Miembro("Leo", "Messi", "leo_messi@gmail.com", "+541138157280");

    @Test
    public void probarContacto(){
        miembro.setMedioNotificacion(MedioNotificacion.WHATSAPP);
        Assert.assertEquals(miembro.contacto(), "+541138157280");
        miembro.setMedioNotificacion(MedioNotificacion.CORREO_ELECTRONICO);
        Assert.assertEquals(miembro.contacto(),"leo_messi@gmail.com");
    }

}
