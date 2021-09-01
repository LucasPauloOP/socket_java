/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import java.io.*;
import java.net.*;

/**
 *
 * @author Lucas Paulo de Oliveira Pinto;
 * @turma CCO8;
 */


class Decodificacao {
    private String sistema, tamanhoString, operacao, num1, num2;
    
    Decodificacao(String code) {
       // this.code = code;
        this.sistema = code.substring(0, 4);
        this.tamanhoString = code.substring(4, 8);
        this.num1 = code.substring(8, 12);
        this.num2 = code.substring(12, 16);
        this.operacao = code.substring(16);
    }
    
    public String getSistema() {
        return this.sistema;
    }
    
    public String getTamanhoString() {
        return this.tamanhoString;
    } 
    
    public String getOperacao() {
        return this.operacao;
    } 
    
    public String getNum1() {
        return this.num1;
    }
    
    public String getNum2() {
        return this.num2;
    }
}



class Encrypt {
    Encrypt() {}
    
    String realizarCalculo(Double num1, Double num2, String operacao) {
       Double resultado = 0.0;
        
        switch(operacao) {
               case "SUM":
                   resultado = num1 + num2;	
               break;
               case "SUB":
                   resultado = num1 - num2;
               case "MULT":
                   resultado = num1 * num2;
               break;
               case "DIV":
                   resultado = num1 / num2;
               break;
               case "RAIZ":
                   resultado = Math.sqrt(num1);
               break;
           }
        
        return this.encryptResultado((int)Math.round(resultado));
    }
    
    String encryptResultado(int resultado) {
        String code = preencheNum(resultado);
        
        return "RESP0004" + code;
    }
    
    private String preencheNum(int num) {
       String string = Integer.toString(num);
        
      while(string.length() < 4) {
          string = '0' + string;
      }

      return string;
    }
}

public class server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Double num1, num2;
       Double resultado = 0.0;
       int port = 4800;
       String string1, string2, string3, operacao, codificacao;
       ServerSocket serverSocket = null;
       String url = "localhost";
       String sistema;
       
       try {
           serverSocket = new ServerSocket(port);
           Socket server1 = serverSocket.accept();
           
           InputStream is = server1.getInputStream();
        
           OutputStream os1 = server1.getOutputStream();
	   DataInputStream dis = new DataInputStream(is);
	   DataOutputStream dos1 = new DataOutputStream(os1);
           
           codificacao = dis.readUTF();
           
           System.out.println("codifcação " + codificacao);
           
           Decodificacao decodificacao = new Decodificacao(codificacao);
           
           System.out.println(decodificacao.getOperacao() + ' ' + decodificacao.getNum1() + ' ' + decodificacao.getNum2());
           
           operacao = decodificacao.getOperacao();
           string1 = decodificacao.getNum1();
           string2 = decodificacao.getNum2();
           sistema = decodificacao.getSistema();
           System.out.println("Operação escolhida foi " + operacao);
           num1 = Double.parseDouble(string1);
           num2 = Double.parseDouble(string2);
           
           if (!sistema.equals("CALC")) {
            dos1.writeUTF("-1");
            server1.close();
            dis.close();
           } else if (operacao.equals("DIV") && num2 == 0) {
            dos1.writeUTF("XXXX");
            server1.close();
            dis.close();
           } else {
             Encrypt encrypt = new Encrypt();
             String code = encrypt.realizarCalculo(num1, num2, operacao);
             dos1.writeUTF(code);
             server1.close();
             dis.close();
           }
           
       } catch (IOException e) {
           System.out.println(e);
           System.out.println("Error no server");
       }
    }
    
}