/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.*;
import java.io.*;

/**
 *
 * @author visitante
 */
public class cliente {
    public static void main(String args[]){
		String cad1,cad2,cad3;
		String resultado = "";
		String url = "localhost";
		int port = 4800;

		try{
			Socket sc1 = new Socket(url, port);
			InputStreamReader isr = new InputStreamReader( System.in );
			InputStream is = sc1.getInputStream();
			BufferedReader bf = new BufferedReader(isr);	
			OutputStream os1 = sc1.getOutputStream();
			DataOutputStream dos1 = new DataOutputStream(os1);
			DataInputStream dis = new DataInputStream(is);

			System.out.println("Qual o tipo de operação: ");
			System.out.println("1)Soma 2)Subtração 3)Multiplicação 4)Divisão");
			cad3 = bf.readLine();
			dos1.writeUTF(cad3);

			System.out.println("Enviar primeiro número : ");
			cad1 = bf.readLine();
			dos1.writeUTF(cad1);

			System.out.println("Enviar segundo número: ");
			cad1 = bf.readLine();
			dos1.writeUTF(cad1);

			resultado = dis.readUTF();
			System.out.println("O resultado é: " + resultado);

			dos1.flush();	
			dos1.close();
		}
		catch(IOException e){	
			System.out.println("ERROR: não encontro servidor");
		}
	}
}