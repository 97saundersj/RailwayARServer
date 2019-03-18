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
        
        for (int i = 1; i < 19; i++)
        {
        	trackList.add("green");
        }
        


        serverThread.setData(trackList);
        System.out.println(trackList.toString());
        
        Scanner sc = new Scanner(System.in);

        int trainLocation = 0;
        while (true)
        {
            trackList = new ArrayList<>();

            sc.nextLine();

            for (int i = 0; i < 17; i++)
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

            

            serverThread.setData(trackList);

            System.out.println(trackList.toString());
            if (trainLocation > 16)
            {
                trainLocation = 0;
            } else
            {
                trainLocation++;
            }
        }
    }
}
    
    
    

