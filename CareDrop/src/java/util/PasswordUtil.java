/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author gabri
 */
public class PasswordUtil {
     private static final int ITERACOES = 120000;
    private static final int TAMANHO_CHAVE = 256;

    public static String gerarHash(String senha) {

        try {
            byte[] salt = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            KeySpec spec = new PBEKeySpec(
                senha.toCharArray(),
                salt,
                ITERACOES,
                TAMANHO_CHAVE
            );

            SecretKeyFactory factory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();

            return ITERACOES + ":"
                + Base64.getEncoder().encodeToString(salt) + ":"
                + Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash da senha.", e);
        }
    }

    public static boolean verificarSenha(String senha, String hashArmazenado) {

        try {
            String[] partes = hashArmazenado.split(":");

            int iteracoes = Integer.parseInt(partes[0]);
            byte[] salt = Base64.getDecoder().decode(partes[1]);
            byte[] hashOriginal = Base64.getDecoder().decode(partes[2]);

            KeySpec spec = new PBEKeySpec(
                senha.toCharArray(),
                salt,
                iteracoes,
                hashOriginal.length * 8
            );

            SecretKeyFactory factory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hashCalculado = factory.generateSecret(spec).getEncoded();

            return java.security.MessageDigest.isEqual(
                hashOriginal,
                hashCalculado
            );

        } catch (Exception e) {
            return false;
        }
    }
}

