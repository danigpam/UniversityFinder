<%@page import="java.util.List"%>
<%@page import="com.umc.pfc.entidades.Endereco"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Encontre uma universidade</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="script/enderecos.js" type="text/javascript"></script>
        <link href="bootstrap/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="estilo/estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="pagina">
        <h1>Encontre uma Universidade</h1>
         
        <h2>Digite o seu endereço:</h2>
        <form method="post" action="BuscarUniversidades">
            <label>Cep:</label>
                <input name="cep" type="text" id="cep" value="" size="10" maxlength="9"
                       onblur="pesquisacep(this.value);" required /><br />
            <label>Rua:</label>
                <input name="rua" type="text" id="rua" size="60" required><br />
            <label>Numero:</label>
                <input name="numero" type="number" id="numero" required><br />
            <label>Complemento: </label>
                <input name="complemento" type="text" id="complemento" size="60" /><br />
            <label>Bairro:</label>
                <input name="bairro" type="text" id="bairro" size="40" required><br />
            <label>Cidade:</label>
                <input name="cidade" type="text" id="cidade" size="40" required><br />
            <label>Estado:</label>
                <input name="uf" type="text" id="uf" size="2" required><br />
            <label>Raio de busca:</label>
            <input type="number" name="raio" id="raio" value="20" required> Km<br />
                
                <input type="submit" value="Buscar">
        </form>

        <%
            List<Endereco> listaEnderecos = null;
            if ((listaEnderecos = (List<Endereco>) request.getSession().getAttribute("universidadesCadastradas")) != null) {
        %>
        <h2>Universidades cadastradas</h2>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Rua</th>
                    <th>Número</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
        <%
                for (Endereco end : listaEnderecos) {
        %>
        
            <tr>
                <td><%=end.getApelido()%></td>
                <td><%=end.getRua()%></td>
                <td><%=end.getNumero()%></td>
                <td><%=end.getBairro()%></td>
                <td><%=end.getCidade()%></td>
                <td><%=end.getEstado()%></td>
            </tr>
        <%      }
        %>
            </tbody>
        </table>
        <%
            }
        %>

        </div>
    </body>
</html>
