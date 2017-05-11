package com.umc.pfc.controle;

import com.umc.pfc.entidades.Endereco;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danigpam
 */
@WebServlet(name = "ListarUniversidades", urlPatterns = {"/ListarUniversidades"})
public class ListarUniversidades extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Endereco> lista = (List<Endereco>) request.getSession(true).getAttribute("universidadesCadastradas");
        if (lista == null) {
            lista = listarUniversidadesPreCadastradas(request);
            request.getSession(true).setAttribute("universidadesCadastradas", lista);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    }

    public List<Endereco> listarUniversidadesPreCadastradas(HttpServletRequest request) {
        List<Endereco> lista = new ArrayList<>();
        
        Endereco umc = new Endereco("UMC", "08780911", "Av. Dr. Cândido Xavier de Almeida e Souza", 200, "", "Centro Cívico", "Mogi das Cruzes", "São Paulo", "Brasil");
        Endereco unisuz = new Endereco("Unisuz", "08675130", "Rua José Corrêa Gonçalves", 57, "", "Vila São Jorge", "Suzano", "São Paulo", "Brasil");
        Endereco ubc = new Endereco("UBC", "08710590", "Rua Ten. Manoel Alves dos Anjos", 580, "", "Centro", "Mogi das Cruzes", "São Paulo", "Brasil");
        Endereco uninove = new Endereco("Uninove", "02111030", "R. Itauna", 74, "", "Vila Maria Baixa", "São Paulo", "São Paulo", "Brasil");
        Endereco uspLeste = new Endereco("USP Leste", "03828000", "Rua Arlindo Béttio", 1000, "", "Jardim Keralux", "São Paulo", "São Paulo", "Brasil");
        Endereco mackenzie = new Endereco("Mackenzie", "01302907", "R. da Consolação", 930, "", "Consolação", "São Paulo", "São Paulo", "Brasil");
  
        lista.add(umc);
        lista.add(ubc);
        lista.add(unisuz);
        lista.add(uninove);
        lista.add(uspLeste);
        lista.add(mackenzie);
        
        Collections.sort(lista, Endereco.COMPARATOR_APELIDO);
        return lista;
    }
}
