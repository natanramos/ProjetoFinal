import com.curso.betha.projetofinal.utils.Conexao;

import java.sql.Connection;

/**
 * Created by NatanRamos on 01/10/2016.
 */
public class Teste {

    public static void main(String args[]) {

        Conexao conexao = new Conexao();

        Connection conn = conexao.getConnection();

    }

}
