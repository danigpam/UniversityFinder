package com.umc.pfc.entidades;

import com.umc.pfc.distancias.CalculadoraEnderecos;
import java.util.Comparator;

/**
 *
 * @author danigpam
 */
public class Distancia {
    private Endereco endereco;
    private Endereco origem;
    private double distanciaEmKm;

    public Distancia(Endereco endereco, Endereco origem, double distanciaEmKm) {
        this.origem = origem;
        this.endereco = endereco;
        this.distanciaEmKm = distanciaEmKm;
    }

    public Distancia(Endereco endereco, Endereco origem) {
        this.origem = origem;
        this.endereco = endereco;
        this.distanciaEmKm = new CalculadoraEnderecos().calcularDistanciaEmKm(endereco, origem);
    }

    public Endereco getOrigem() {
        return origem;
    }

    public void setOrigem(Endereco origem) {
        this.origem = origem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getDistanciaEmKm() {
        return distanciaEmKm;
    }

    public void setDistanciaEmKm(double distanciaEmKm) {
        this.distanciaEmKm = distanciaEmKm;
    }   
    
     public static Comparator COMPARATOR_DISTANCIA = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            
            Distancia e1 = (Distancia) t;
            Distancia e2 = (Distancia) t1;
            
            return new Double(e1.getDistanciaEmKm()).compareTo(e2.getDistanciaEmKm());
        }
    };
    
}
