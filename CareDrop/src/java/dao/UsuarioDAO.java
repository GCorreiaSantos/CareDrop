/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Usuario;
import util.PasswordUtil;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GC
 */

public class UsuarioDAO {

    public boolean cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario "
                   + "(id_casa, nome, email, senha_hash, tipo) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(
                     sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, usuario.getIdCasa());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            
            String senhaProtegida = PasswordUtil.gerarHash(usuario.getSenhaHash());
            stmt.setString(4, senhaProtegida);
            
            stmt.setString(5, usuario.getTipo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet resultado = stmt.getGeneratedKeys()) {
                    if (resultado.next()) {
                        usuario.setIdUsuario(resultado.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Usuario buscarPorEmail(String email, int idCasa) {

        String sql = "SELECT * FROM usuario "
                   + "WHERE email = ? AND id_casa = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setInt(2, idCasa);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {

                    Usuario usuario = new Usuario(
                        resultado.getInt("id_casa"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("senha_hash"),
                        resultado.getString("tipo")
                    );

                    usuario.setIdUsuario(
                        resultado.getInt("id_usuario")
                    );

                    usuario.setAtivo(
                        resultado.getBoolean("ativo")
                    );

                    return usuario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Usuario autenticar(String email, int idCasa, String senha) {

        Usuario usuario = buscarPorEmail(email, idCasa);

        if (usuario == null) {
            return null;
        }

        if (!usuario.isAtivo()) {
            return null;
        }

        boolean senhaValida = PasswordUtil.verificarSenha(
            senha,
            usuario.getSenhaHash()
        );

        if (senhaValida) {
            return usuario;
        }

        return null;
    }
}
