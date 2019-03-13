package railwayarserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class TestClient
{

    public static void main(String[] args) throws IOException
    {
        while (true)
        {
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // send request
            byte[] buf = new byte[256];
            InetAddress address = InetAddress.getByName("192.168.0.61");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // display response
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);

            socket.close();
        }
    }
}
