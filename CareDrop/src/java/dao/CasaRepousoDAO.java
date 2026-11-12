/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.CasaRepouso;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GC
 */
public class CasaRepousoDAO {
    
    public boolean cadastrar(CasaRepouso casa) {

    String sql = "INSERT INTO casa_repouso "
               + "(nome, cnpj, endereco, telefone) "
               + "VALUES (?, ?, ?, ?)";

    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(
                 sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, casa.getNome());
        stmt.setString(2, casa.getCnpj());
        stmt.setString(3, casa.getEndereco());
        stmt.setString(4, casa.getTelefone());

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            try (ResultSet resultado = stmt.getGeneratedKeys()) {
                if (resultado.next()) {
                    casa.setIdCasa(resultado.getInt(1));
                }
            }
            return true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
    
    public CasaRepouso buscarPorCnpj(String cnpj) {

    String sql = "SELECT * FROM casa_repouso WHERE cnpj = ?";

    try (Connection conexao = ConnectionFactory.getConnection();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, cnpj);

        try (ResultSet resultado = stmt.executeQuery()) {

            if (resultado.next()) {

                CasaRepouso casa = new CasaRepouso(
                resultado.getString("nome"),
                resultado.getString("cnpj"),
                resultado.getString("endereco"),
                resultado.getString("telefone")
                );

                casa.setIdCasa(resultado.getInt("id_casa"));
                casa.setNome(resultado.getString("nome"));
                casa.setCnpj(resultado.getString("cnpj"));
                casa.setEndereco(resultado.getString("endereco"));
                casa.setTelefone(resultado.getString("telefone"));

                return casa;
            }
        }

            } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
