package com.umc.pfc.teste;

import com.umc.pfc.distancias.CalculadoraEnderecos;
import com.umc.pfc.entidades.Endereco;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danigpam
 */
public class TesteDistancias {

    public static void main(String[] args) {
        
        Endereco umc = new Endereco("UMC", "08780911", "Av. Dr. Cândido Xavier de Almeida e Souza", 200, "", "Centro Cívico", "Mogi das Cruzes", "São Paulo", "Brasil");
        Endereco ibm = new Endereco("IBM", "04007900", "Rua Tutóia", 1157, "", "Vila Mariana", "São Paulo", "São Paulo", "Brasil");
        Endereco unisuz = new Endereco("Unisuz", "08675130", "Rua José Corrêa Gonçalves", 57, "", "Vila São Jorge", "Suzano", "São Paulo", "Brasil");
        Endereco ubc = new Endereco("UBC", "08710590", "Rua Ten. Manoel Alves dos Anjos", 580, "", "Centro", "Mogi das Cruzes", "São Paulo", "Brasil");
        
        List<Endereco> lista = new ArrayList<>();
        lista.add(ibm);
        lista.add(ubc);
        lista.add(unisuz);
        
        System.out.println("Enderecos cadastrados:");
        System.out.println(umc.getApelido()+ ": " + umc);
        System.out.println(ibm.getApelido()+ ": " + ibm);
        System.out.println(unisuz.getApelido()+ ": " + unisuz);
        System.out.println(ubc.getApelido()+ ": " + ubc);
        System.out.println();
        
        CalculadoraEnderecos calculadoraEnderecos = new CalculadoraEnderecos();
        
        NumberFormat nf = NumberFormat.getNumberInstance();
        System.out.print("Distancia entre UMC e IBM: ");
        System.out.println(nf.format(calculadoraEnderecos.calcularDistanciaEmKm(umc, ibm)) + "km\n");

        System.out.print("Local mais próximo de UMC: ");
        System.out.println(calculadoraEnderecos.localizarMaisProximo(umc, lista).getApelido() + "\n");

        System.out.print("Dentro de um raio de 30km da UMC: ");
        for (Endereco endereco : calculadoraEnderecos.filtrarPorRaio(umc, lista, 30)) {
            System.out.print(endereco.getApelido()+ " ");
        }
        
        System.out.println();
    }
}
