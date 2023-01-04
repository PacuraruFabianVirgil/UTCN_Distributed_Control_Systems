package lab.scd.net.ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TerminalSend extends Thread{
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    InetAddress address = InetAddress.getByName("localhost");
    int port = 0;

    public TerminalSend(String name, int port) throws IOException {
        super(name);
        this.port = port;
        socket = new DatagramSocket();
    }

    public void run() {
        while (true) {
            byte[] buf = new byte[256];
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String aux = "";
            try {
                aux = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            buf = aux.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, port);
            try {
                socket.send(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
