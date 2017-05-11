package com.umc.pfc.entidades;

import com.umc.pfc.distancias.Geocoder;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author danigpam
 */
public class Endereco implements Serializable {
    
    private String apelido;
    private String cep;
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private Coordenada coordenada;
    
    

    public Endereco(String apelido, String cep, String rua, int numero, String complemento, String bairro, String cidade, String estado, String pais) {
        this.apelido = apelido;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    
    public String getCep() {
        return cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Coordenada getCoordenada() {
        if (coordenada == null) {
            coordenada = new Geocoder().getCoordenada(this);
            coordenada.setEndereco(this);
        }
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + ". " + bairro + ", " + cidade + ". " + estado + ", " + pais + ".";
    }
    
    public static Comparator COMPARATOR_APELIDO = new Comparator() {
        @Override
        public int compare(Object t, Object t1) {
            
            Endereco e1 = (Endereco) t;
            Endereco e2 = (Endereco) t1;
            
            return e1.getApelido().toLowerCase().compareTo(e2.getApelido().toLowerCase());
        }
    };
}
