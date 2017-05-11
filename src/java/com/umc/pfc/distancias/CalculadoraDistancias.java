package com.umc.pfc.distancias;

import com.umc.pfc.entidades.Distancia;
import com.umc.pfc.entidades.Endereco;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author danigpam
 */
public class CalculadoraDistancias {
    
    public List<Distancia> getDistancias(List<Endereco> listaEnderecos, Endereco enderecoAlvo) {
        if (listaEnderecos == null){ 
            return null;
        }
        
        List<Distancia> listaDistancias = new ArrayList<>();
        
        for (Endereco endereco : listaEnderecos) {
            Distancia dist = new Distancia(endereco, enderecoAlvo);
            listaDistancias.add(dist);
        }
        
        Collections.sort(listaDistancias, Distancia.COMPARATOR_DISTANCIA);
        return listaDistancias;
    }

    public Distancia getDistancia(Endereco endereco, Endereco enderecoAlvo) {
        return new Distancia(endereco, enderecoAlvo);
    }
    
}
