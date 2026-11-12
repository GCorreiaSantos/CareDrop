
package servlet;

import dao.CadastroInicialDAO;
import model.CasaRepouso;
import model.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastro-inicial")
public class CadastroInicialServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Dados da casa de repouso
        String nomeCasa = request.getParameter("nomeCasa");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        // Dados do administrador
        String nomeAdmin = request.getParameter("nomeAdmin");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Verificar campos obrigatórios
        if (nomeCasa == null || cnpj == null ||
            endereco == null || telefone == null ||
            nomeAdmin == null || email == null ||
            senha == null ||
            nomeCasa.isBlank() || cnpj.isBlank() ||
            endereco.isBlank() || telefone.isBlank() ||
            nomeAdmin.isBlank() || email.isBlank() ||
            senha.isBlank()) {

            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Preencha todos os campos."
            );
            return;
        }

        // Criar os objetos
        CasaRepouso casa = new CasaRepouso(
            nomeCasa.trim(),
            cnpj.trim(),
            endereco.trim(),
            telefone.trim()
        );

        Usuario administrador = new Usuario(
            0,
            nomeAdmin.trim(),
            email.trim(),
            senha,
            "ADMIN"
        );

        // Cadastrar no banco
        CadastroInicialDAO dao = new CadastroInicialDAO();

        boolean sucesso = dao.cadastrar(casa, administrador);

        if (sucesso) {
            response.getWriter().println(
                "Casa de repouso e administrador cadastrados com sucesso!"
            );
        } else {
            response.setStatus(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().println(
                "Não foi possível realizar o cadastro. Verifique os dados."
            );
        }
    }
}
