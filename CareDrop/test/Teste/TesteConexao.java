/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Teste;

import java.sql.Connection;
import util.ConnectionFactory;

/**
 *
 * @author gabri
 */
public class TesteConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection conexao =  ConnectionFactory.getConnection();
            System.out.println("Conexão com banco do CareDrop realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco: ");
            e.printStackTrace();;
        }
    }
}
