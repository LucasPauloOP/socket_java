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
 * @author lucaspauloop
 */


public class server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int num1, num2;
       int resultado = 0;
       int port = 4800;
       String string1, string2, string3, operacao;
       ServerSocket serverSocket = null;
       String url = "localhost";
       
       try {
           serverSocket = new ServerSocket(port);
           Socket server1 = serverSocket.accept();
           
           InputStream is = server1.getInputStream();
        
           OutputStream os1 = server1.getOutputStream();
	   DataInputStream dis = new DataInputStream(is);
	   DataOutputStream dos1 = new DataOutputStream(os1);
           
           
           System.out.println("Aguardando a operação: ");
           
           operacao = dis.readUTF();
           
           System.out.println("Operação escolhida foi " + operacao);
           
           System.out.println("Lendo primeiro número: ");
           string1 = dis.readUTF();
           
           System.out.println("Lendo primeiro número: ");
           string2 = dis.readUTF();
           
           num1 = Integer.parseInt(string1);
           num2 = Integer.parseInt(string2);
           
           if(operacao.equals("1")){
		resultado = num1 + num2;	
            } else if (operacao.equals("2")) {
		resultado = num1 + num2;
	    } else if (operacao.equals("3")) {
		resultado = num1 + num2;
	    } else if (operacao.equals("4")) {
		resultado = num1 + num2;
	    } else{
		System.out.print("Error");
	    }
            
           System.out.println("O resultado é: " + resultado);
           dos1.writeUTF(Integer.toString(resultado));
           
           server1.close();
           dis.close();
           
       } catch (IOException e) {
           System.out.println(e);
           System.out.println("Error no server");
       }
    }
    
}