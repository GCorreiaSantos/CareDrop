
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro Inicial | CareDrop</title>
</head>
<body>

    <h1>CareDrop</h1>
    <h2>Cadastro da Casa de Repouso</h2>

    <form action="${pageContext.request.contextPath}/cadastro-inicial"
          method="POST">

        <h3>Dados da instituição</h3>

        <label>Nome da casa de repouso:</label>
        <input type="text" name="nomeCasa" required>

        <br><br>

        <label>CNPJ:</label>
        <input type="text" name="cnpj" required>

        <br><br>

        <label>Endereço:</label>
        <input type="text" name="endereco" required>

        <br><br>

        <label>Telefone:</label>
        <input type="text" name="telefone" required>

        <h3>Dados do administrador</h3>

        <label>Nome completo:</label>
        <input type="text" name="nomeAdmin" required>

        <br><br>

        <label>E-mail:</label>
        <input type="email" name="email" required>

        <br><br>

        <label>Senha:</label>
        <input type="password" name="senha" required minlength="8">

        <br><br>

        <button type="submit">Cadastrar instituição</button>

    </form>

</body>
</html>
