/*
 * ServerSimplu.java
 */

/**
 * Class created by @author Mihai HULEA at Feb 23, 2005.
 * 
 * This class is part of the laborator2_serverclientmonofir project.
 * 
 * 1. Modificati aplicatia sever astfel incat dupa tratarea unui client acesta sa revina 
 * in astepatare pentru a procesa noi cereri. 
 * 
 * 2. Modificati aplicatia server astefl incat aceasta sa accepte conexiuni sosite 
 * numai de pe anumite IP-uri.
 */
package lab.scd.net.socket;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;



public class ServerSimplu {
  public static void main(String[] args) throws IOException{

    ServerSocket ss=null;
    Socket s=null;
    
    try{
      String line="";
      ss = new ServerSocket(1900); //creaza obiectul serversocket
      System.out.println("Serverul asteapta conexiuni...");
      s = ss.accept(); //incepe asteptarea de conexiuni  pe portul 1900
      //in momentul in care un client s-a  conectat ss.accept() returneaza
      //un obiect de tip Socket care identifica conexiunea

      //creaza fluxurile de intrare iesire
      BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

      PrintWriter out = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(
              s.getOutputStream())),true);
      
      //extrage adresa de ip si portul de pe care clientul s-a conectat
      InetSocketAddress remoteadr = (InetSocketAddress)s.getRemoteSocketAddress();
      String remotehost = remoteadr.getHostName();
      int remoteport = remoteadr.getPort();
      
      System.out.println("Client nou conectat: "+remotehost+":"+remoteport);
      
      while(!line.equals("END")){
        line = in.readLine(); //citeste datele de la client
        System.out.println("Server a receptionat:"+line);
        int n = Integer.parseInt(line);
        out.println("ECHO "+ n); //trimite date la client
        out.flush();
      }
      
      System.out.println("Aplicatie server gata.");
      
    }catch(Exception e){e.printStackTrace();}
     finally{
      ss.close();
      if(s!=null) s.close();
     }
  }
}
