import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class Teste {

    public static void main(String args[]) {

        Date data = new Date();

        System.out.println(data);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("");
        System.out.println(format.format(data));

    }

}
