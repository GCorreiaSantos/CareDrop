/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.CasaRepouso;
import model.Usuario;
import util.ConnectionFactory;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class CadastroInicialDAO {
     public boolean cadastrar(CasaRepouso casa, Usuario administrador) {

        String sqlCasa = "INSERT INTO casa_repouso "
                       + "(nome, cnpj, endereco, telefone) "
                       + "VALUES (?, ?, ?, ?)";

        String sqlUsuario = "INSERT INTO usuario "
                          + "(id_casa, nome, email, senha_hash, tipo) "
                          + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = ConnectionFactory.getConnection()) {

            conexao.setAutoCommit(false);

            try {
                // 1. Cadastrar a casa de repouso
                try (PreparedStatement stmtCasa = conexao.prepareStatement(
                        sqlCasa, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    stmtCasa.setString(1, casa.getNome());
                    stmtCasa.setString(2, casa.getCnpj());
                    stmtCasa.setString(3, casa.getEndereco());
                    stmtCasa.setString(4, casa.getTelefone());

                    stmtCasa.executeUpdate();

                    try (ResultSet resultado = stmtCasa.getGeneratedKeys()) {
                        if (!resultado.next()) {
                            throw new SQLException("Não foi possível obter o ID da casa.");
                        }

                        casa.setIdCasa(resultado.getInt(1));
                    }
                }

                // 2. Cadastrar o primeiro administrador
                String senhaProtegida = PasswordUtil.gerarHash(
                    administrador.getSenhaHash()
                );

                try (PreparedStatement stmtUsuario = conexao.prepareStatement(
                        sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    stmtUsuario.setInt(1, casa.getIdCasa());
                    stmtUsuario.setString(2, administrador.getNome());
                    stmtUsuario.setString(3, administrador.getEmail());
                    stmtUsuario.setString(4, senhaProtegida);
                    stmtUsuario.setString(5, "ADMIN");

                    stmtUsuario.executeUpdate();

                    try (ResultSet resultado = stmtUsuario.getGeneratedKeys()) {
                        if (resultado.next()) {
                            administrador.setIdUsuario(resultado.getInt(1));
                        }
                    }
                }

                // 3. Confirmar as duas operações
                conexao.commit();

                administrador.setIdCasa(casa.getIdCasa());

                return true;

            } catch (SQLException | RuntimeException e) {

                conexao.rollback();
                e.printStackTrace();

                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
