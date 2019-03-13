/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railwayarserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 97saundersj
 */
public class ARRailwayTest
{

    public static void main(String[] args) throws IOException
    {
        ARServerThread serverThread = new ARServerThread();
        serverThread.start();

        ArrayList<String> trackList = new ArrayList<>();
        trackList.add("green");
        trackList.add("green");
        trackList.add("green");
        trackList.add("green");

        ArrayList<String> switchList = new ArrayList<>();
        switchList.add("right");

        serverThread.setData(trackList, switchList);
        System.out.println(trackList.toString() + switchList.toString());
        
        Scanner sc = new Scanner(System.in);

        int trainLocation = 0;
        while (true)
        {
            trackList = new ArrayList<>();
            switchList = new ArrayList<>();

            sc.nextLine();

            for (int i = 0; i < 3; i++)
            {
                if (i == trainLocation)
                {
                    trackList.add("red");
                } else
                {
                    trackList.add("green");
                }

            }

            trackList.add("green");
            
            if (trainLocation > 2)
            {
                switchList.add("right");
            }
            else
            {
                switchList.add("left");
            }

            serverThread.setData(trackList, switchList);

            System.out.println(trackList.toString() + switchList.toString());
            if (trainLocation > 2)
            {
                trainLocation = 0;
            } else
            {
                trainLocation++;
            }
        }
    }
    
    
}
