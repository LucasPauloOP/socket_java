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
 * @author Lucas Paulo de Oliveira Pinto;
 * @turma CCO8;
 */

class Codificacao{
    private String op;
    private String num1, num2;
    private String code;
    
   Codificacao(String op, String num1, String num2) {
       this.op = op;
       this.num1 = num1;
       this.num2 = num2;
   }
   
  public String constroiCode() {
      String code = "CALC0012";
      this.num1 = this.preencheNum(this.num1);
      this.num2 = this.preencheNum(this.num2);
      
      this.SelecionaOperacao();
      
      this.setCode(code + this.num1 + this.num2 + this.op);
      return this.code;
  }
  
  private void SelecionaOperacao() {
     switch(this.op) {
         case "1":
             this.op = "SUM";
         break;
         case "2":
             this.op = "SUB";
         break;
         case "3":
             this.op = "DIV";
         break;
         case "4":
             this.op = "MULT";
         break;
         case "5":
             this.op = "RAIZ";
         break;
     }
  }
  
  private String preencheNum(String num) {
      while(num.length() < 4) {
          num = '0' + num;
      }

      return num;
  }
  
  private void setCode(String code) {
      this.code = code;
  }
}

public class cliente {
    public static void main(String args[]){
		String num1, num2, operacao;
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
			System.out.println("1)Soma \n2)Subtração \n3)Multiplicação \n4)Divisão \n5) Raiz");
			operacao = bf.readLine();

			System.out.println("Enviar um número : ");
			num1 = bf.readLine();
                        
                        int verificarn1 = Integer.parseInt(num1);
                        
                        if (verificarn1 < 0) num1 = Integer.toString(verificarn1 * -1);
                        
                        if (!operacao.equals("5")) {
                            System.out.println("Enviar um número: ");
                            num2 = bf.readLine();
                        } else {
                            num2 = num1;
                        }
                        
                
                        Codificacao codific = new Codificacao(operacao, num1, num2);
                        dos1.writeUTF(codific.constroiCode());

                        resultado = dis.readUTF();
			System.out.println("O resultado é: " + resultado.substring(8, 12));

			dos1.flush();	
			dos1.close();
		}
		catch(IOException e) {
			System.out.println("ERROR: não encontro servidor");
		}
	}
}