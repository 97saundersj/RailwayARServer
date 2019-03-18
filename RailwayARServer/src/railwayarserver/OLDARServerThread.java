/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayarserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class OLDARServerThread extends Thread
{

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    public String data;
    
    public OLDARServerThread( ) throws IOException
    {
        socket = new DatagramSocket(4445);
        data = "";
    }

    /**
     * sends Data to devices
     * @param trackStates example [green, green, red, green]
     * @param switchStates example [left]
     */
    public void setData(ArrayList<String> trackStates, ArrayList<String> switchStates)
    {
        JSONObject json = new JSONObject();
        
        try
        {
            JSONObject trackStatesJSON = new JSONObject();

            for (int i = 0; i < trackStates.size(); i++)
            {
                trackStatesJSON.put(Integer.toString(i), trackStates.get(i));
            }

            json.put("trackStates", trackStatesJSON);
            
            //Switch
            JSONObject switchStatesJSON = new JSONObject();
            
            for (int i = 0; i < switchStates.size(); i++)
            {
                switchStatesJSON.put(Integer.toString(i), switchStates.get(i));
            }

            json.put("switchStates", switchStatesJSON);

            //Example of JSON string
            //{"trackStates":{"0":"green","1":"green","2":"green","3":"green"},"switchStates":{"0":"right"}}
            data = json.toString();

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void run()
    {
        while (moreQuotes)
        {
            try
            {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // display response
                //String received = new String(packet.getData(), 0, packet.getLength());
                //System.out.println(received);

                // send data
                
                buf = data.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e)
            {
                e.printStackTrace();
                moreQuotes = false;
                System.out.println("Usage: java QuoteClient <hostname>");
            }
        }
        socket.close();
    }
}
