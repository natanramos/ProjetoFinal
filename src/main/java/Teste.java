import com.curso.betha.projetofinal.utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class Teste {

    public static void main(String args[]) {

        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date data = Utils.getData("2016-01-01");

        System.out.println(data);
        System.out.println(formato.format(data));
    }

}
