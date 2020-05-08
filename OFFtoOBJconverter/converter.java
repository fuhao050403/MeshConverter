/*************************************************************
* .off to .obj converter
* Student: Fu Hao(20N8100013G)
* Chuo University
* Information and System Engineering
* Date: 2020/5/8
**************************************************************/

import java.io.*;
import java.util.Scanner;

class fileManager
{
    public static void CreateResultFile() throws IOException
    {
        try
        {
            File file = new File(System.getProperty("user.home") + "/Desktop", "lion.obj");
            // Create Result.txt file to the desktop
            if(file.createNewFile())
            {
                System.out.println("File created: " + file.getName());
            }
            else
            {
                file.delete();
                if(file.createNewFile())
                { 
                    System.out.println("File created: " + file.getName());
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

class mesh2c
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Read data from file "lion.off" on Desktop
        File file = new File(System.getProperty("user.home") + "/Desktop", "lion.off");
        Scanner s = new Scanner(file);
        s.nextLine(); // Skip first line of context

        // Create result file
        try { fileManager.CreateResultFile(); }
        catch(IOException exc) { System.out.println("Error: " + exc.getMessage()); }
        
        // Initializing PrintWriter instance for writing data to result file
        PrintWriter pw = new PrintWriter(System.getProperty("user.home") + "/Desktop/lion.obj");

        // Get number of vertices which lion.off has
        String str_num_of_vertices = s.next();
        String str_num_of_faces    = s.next();
        int num_of_vertices = Integer.parseInt(str_num_of_vertices);
        int num_of_faces    = Integer.parseInt(str_num_of_faces);
        s.nextLine();

        String res;
        for(int i = 0; i < num_of_vertices; i++)
        {
            res = "v " + s.next() + " " + s.next() + " " + s.next();
            pw.println(res);
        }

        for(int i = 0; i < num_of_faces; i++)
        {
            res = "f";
            int num_of_v = s.nextInt();
            for(int j = 0; j < num_of_v; j++)
            {
                res += " " + Integer.toString(s.nextInt() + 1);
            }
            pw.println(res);
        }

        s.close();
        pw.close();
    }
}