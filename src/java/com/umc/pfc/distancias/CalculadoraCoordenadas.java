package com.umc.pfc.distancias;

import com.umc.pfc.entidades.Coordenada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danigpam
 */
public class CalculadoraCoordenadas {

    public double calcularDistanciaEmKm(Coordenada c1, Coordenada c2) {
        
        double lat1 = c1.getLatitude();
        double lng1 = c1.getLongitude();
                        
        double lat2 = c2.getLatitude();
        double lng2 = c2.getLongitude();
                
        double r = 6371000;
        double lat_x = Math.toRadians(lat1);
        double lat_y = Math.toRadians(lat2);

        double delta_lat = Math.toRadians(lat2 - lat1);
        double delta_lng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(delta_lat / 2) * Math.sin(delta_lat / 2) +
                Math.cos(lat_x) * Math.cos(lat_y) * Math.sin(delta_lng / 2) * Math.sin(delta_lng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return r * c / 1000;
    }
    
    public Coordenada localizarMaisProximo(Coordenada alvo, List<Coordenada> candidatos) {
        Coordenada maisProximo = null;
        double menorDistancia = 0;
        
        for (Coordenada candidato : candidatos) {
            double distancia = calcularDistanciaEmKm(alvo, candidato);
            
            if (distancia < menorDistancia || menorDistancia == 0) {
                menorDistancia = distancia;
                maisProximo = candidato;
            }
        }
        
        return maisProximo;
    }
    
    public boolean estaNoRaio(Coordenada origem, Coordenada destino, double raioEmKm) {
        return calcularDistanciaEmKm(origem, destino) <= raioEmKm;
    }
    
    public List<Coordenada> filtrarPorRaio(Coordenada alvo, List<Coordenada> lista, double raioEmKm) {
        List<Coordenada> inLista = new ArrayList<>();
        
        for (Coordenada coordenada : lista) {
            if (estaNoRaio(alvo, coordenada, raioEmKm)) {
                inLista.add(coordenada);
            }
        }        
        return (inLista.size()>0) ? inLista : null;
    }
            
}
