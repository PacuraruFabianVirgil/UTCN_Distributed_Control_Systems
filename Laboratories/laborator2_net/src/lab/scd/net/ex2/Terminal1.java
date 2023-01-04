package lab.scd.net.ex2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Terminal1{
    public static void main(String[] args) throws IOException {
        TerminalSend ts = new TerminalSend("Terminal 1 Send", 4444);
        ts.start();
        int port = 4445;
        DatagramSocket socket = new DatagramSocket(port);
        DatagramPacket packet = null;
        InetAddress address = InetAddress.getByName("localhost");
        String firstAux = new String(new byte[256]);
        String secondAux = new String(new byte[256]);
        while (true) {
            byte[] buf = new byte[256];
            String aux = "";
            packet = new DatagramPacket(buf, buf.length, address, port);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            aux = new String(packet.getData()).replaceAll("\0", "");
            if(!aux.equals(secondAux)){
                secondAux = aux;
            }
            if(!firstAux.equals(secondAux)){
                firstAux = secondAux;
                ts.interrupt();
                System.out.print("\r");
                System.out.println(firstAux);
                ts.resume();
            }
        }
    }
}
