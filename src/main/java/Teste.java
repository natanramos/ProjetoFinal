import com.curso.betha.projetofinal.dao.UsuariosDAO;
import com.curso.betha.projetofinal.model.Usuarios;
import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class Teste {

    public static void main(String args[]) {

        UsuariosDAO dao = new UsuariosDAO();

        Usuarios usuario = dao.getUsuario(1L);

        System.out.println(usuario.toString());

        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(formato.format(new Date()));
    }

}
