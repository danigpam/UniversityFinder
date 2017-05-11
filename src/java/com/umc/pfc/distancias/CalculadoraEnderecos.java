package com.umc.pfc.distancias;

import com.umc.pfc.entidades.Coordenada;
import com.umc.pfc.entidades.Endereco;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danigpam
 */
public class CalculadoraEnderecos {
    
    public double calcularDistanciaEmKm(Endereco e1, Endereco e2) {
        return new CalculadoraCoordenadas().calcularDistanciaEmKm(e1.getCoordenada(), e2.getCoordenada());
    }
    
    public Endereco localizarMaisProximo(Endereco alvo, List<Endereco> candidatos) {
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();
        for (Endereco candidato : candidatos) {
            listaCoordenadas.add(candidato.getCoordenada());
        }
        return new CalculadoraCoordenadas().localizarMaisProximo(alvo.getCoordenada(), listaCoordenadas).getEndereco();
    }
    
    public boolean estaNoRaio(Endereco origem, Endereco destino, double raioEmKm) {
        return new CalculadoraCoordenadas().estaNoRaio(origem.getCoordenada(), destino.getCoordenada(), raioEmKm);
    }
    
    public List<Endereco> filtrarPorRaio(Endereco alvo, List<Endereco> lista, double raioEmKm) {
        List<Coordenada> listaCoordenadas = new ArrayList<>();
        for (Endereco endereco : lista) {
            listaCoordenadas.add(endereco.getCoordenada());
        }
        
        listaCoordenadas = new CalculadoraCoordenadas().filtrarPorRaio(alvo.getCoordenada(), listaCoordenadas, raioEmKm);
        
        List<Endereco> listaEnderecos = new ArrayList<>();
        if (listaCoordenadas != null) {
            for (Coordenada coordenada : listaCoordenadas) {
                listaEnderecos.add(coordenada.getEndereco());
            }
        }
        
        return (listaEnderecos.size()>0) ? listaEnderecos : null;
    }
}
