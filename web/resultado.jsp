<%@page import="java.text.DecimalFormat"%>
<%@page import="com.umc.pfc.entidades.Distancia"%>
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

        <h2>Encontramos as seguintes universidades próximas a você</h2>
        
        <p>Raio de busca: <%=request.getParameter("raio")%> Km</p>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Rua</th>
                    <th>Número</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                    <th>Distância em Km</th>
                </tr>
            </thead>
            <tbody>
        <%
            DecimalFormat df = new DecimalFormat("#.00"); 
            
            List<Distancia> listaDistancias = null;
            if ((listaDistancias = (List<Distancia>) request.getAttribute("universidadesEncontradas")) != null) {
        %>
        
        <%
                for (Distancia dist : listaDistancias) {
        %>
            <tr>
                <td><%=dist.getEndereco().getApelido()%></td>
                <td><%=dist.getEndereco().getRua()%></td>
                <td><%=dist.getEndereco().getNumero()%></td>
                <td><%=dist.getEndereco().getBairro()%></td>
                <td><%=dist.getEndereco().getCidade()%></td>
                <td><%=dist.getEndereco().getEstado()%></td> 
                <td><%=df.format(dist.getDistanciaEmKm())%></td> 
            </tr>
        <%      }
            } else {
        %>
            <tr><td colspan="7">Nenhum universidade foi encontrada no raio especificado. Tente ampliar seu raio de busca.</td></tr>
        <%
        }
        %>
            </tbody>
        </table>
            
         <%
            Distancia universidadeMaisProxima = null;
            if ((universidadeMaisProxima = (Distancia) request.getAttribute("universidadeMaisProxima")) != null) {
        %>
        <p>A universidade mais próxima a você é: <%=universidadeMaisProxima.getEndereco().getApelido()%>, a <%=df.format(universidadeMaisProxima.getDistanciaEmKm())%> Km. <%=universidadeMaisProxima.getEndereco().toString()%></p>
        <%
        }
        %>
        <h2>Faça uma nova busca:</h2>
        <form method="post" action="BuscarUniversidades">
            <label>Cep:</label>
                <input name="cep" type="text" id="cep" value="<%=request.getParameter("cep")%>" size="10" maxlength="9"
                       onblur="pesquisacep(this.value);" required /><br />
            <label>Rua:</label>
                <input name="rua" type="text" id="rua" size="60" value="<%=request.getParameter("rua")%>" required><br />
            <label>Numero:</label>
            <input name="numero" type="number" id="numero" value="<%=Integer.parseInt(request.getParameter("numero"))%>" required><br />
            <label>Complemento: </label>
                <input name="complemento" type="text" id="complemento" value="<%=request.getParameter("complemento")%>" size="60" /><br />
            <label>Bairro:</label>
                <input name="bairro" type="text" id="bairro" size="40" value="<%=request.getParameter("bairro")%>" required><br />
            <label>Cidade:</label>
                <input name="cidade" type="text" id="cidade" size="40" value="<%=request.getParameter("cidade")%>" required><br />
            <label>Estado:</label>
                <input name="uf" type="text" id="uf" size="2" value="<%=request.getParameter("uf")%>" required><br />
            <label>Raio de busca:</label>
            <input type="number" name="raio" id="raio" value="<%=request.getParameter("raio")%>" required> Km<br />
                
                <input type="submit" value="Buscar">
        </form>
        </div>
    </body>
</html>
