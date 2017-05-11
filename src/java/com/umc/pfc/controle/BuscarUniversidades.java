
package com.umc.pfc.controle;

import com.umc.pfc.distancias.CalculadoraDistancias;
import com.umc.pfc.distancias.CalculadoraEnderecos;
import com.umc.pfc.entidades.Distancia;
import com.umc.pfc.entidades.Endereco;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniellemartin
 */
@WebServlet(name = "BuscarUniversidades", urlPatterns = {"/BuscarUniversidades"})
public class BuscarUniversidades extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        
        try {
            List<Endereco> lista = (List<Endereco>) request.getSession().getAttribute("universidadesCadastradas");
            if (lista == null) {
                lista = new ListarUniversidades().listarUniversidadesPreCadastradas(request);
            }
            
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("uf");
            int raio = Integer.parseInt(request.getParameter("raio"));
            
            Endereco end = new Endereco("", cep, rua, numero, complemento, bairro, cidade, estado, "");
            
            CalculadoraEnderecos calculadoraEnderecos = new CalculadoraEnderecos();
            CalculadoraDistancias calculadoraDistancias = new CalculadoraDistancias();
            
            List<Endereco> universidadesNoRaio = calculadoraEnderecos.filtrarPorRaio(end, lista, raio);
            List<Distancia> universidadesEncontradas = calculadoraDistancias.getDistancias(universidadesNoRaio, end);
            Distancia universidadeMaisProxima = calculadoraDistancias.getDistancia(calculadoraEnderecos.localizarMaisProximo(end, lista), end);

            request.setAttribute("universidadesEncontradas", universidadesEncontradas);
            request.setAttribute("universidadeMaisProxima", universidadeMaisProxima);
            
            request.getRequestDispatcher("resultado.jsp").forward(request, response);
            
        } catch (IOException ex) {
            Logger.getLogger(BuscarUniversidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
