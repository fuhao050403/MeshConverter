/*************************************************************
* .obj to .off converter
* Student: Fu Hao(20N8100013G)
* Chuo University
* Information and System Engineering
* Date: 2020/5/8
**************************************************************/

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class fileManager
{
    public static void CreateResultFile() throws IOException
    {
        try
        {
            File file = new File(System.getProperty("user.home") + "/Desktop", "lion.off");
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
        // Read data from file "lion.obj" on Desktop
        File file = new File(System.getProperty("user.home") + "/Desktop", "lion.obj");
        Scanner s = new Scanner(file);

        // Create result file
        try { fileManager.CreateResultFile(); }
        catch(IOException exc) { System.out.println("Error: " + exc.getMessage()); }
        
        // Initializing PrintWriter instance for writing data to result file
        PrintWriter pw = new PrintWriter(System.getProperty("user.home") + "/Desktop/lion.off");

        ArrayList<String> result = new ArrayList<String>();
        result.add("OFF");

        int num_of_vertices = 0;
        while(s.next().equals("v"))
        {
            num_of_vertices++;

            String res = s.nextLine().substring(1);
            result.add(res);
        }
        //result.add(1, Integer.toString(num_of_vertices));

        String[] faces = s.nextLine().substring(1).split(" ");
        String res = Integer.toString(faces.length);
        for(int i = 0; i < faces.length; i++)
        {
            res += " " + (Integer.parseInt(faces[i]) - 1);
        }
        result.add(res);

        int num_of_faces = 1;
        while(s.hasNext() && s.next().equals("f"))
        {
            num_of_faces++;

            faces = s.nextLine().substring(1).split(" ");
            res = Integer.toString(faces.length);
            for(int i = 0; i < faces.length; i++)
            {
                res += " " + (Integer.parseInt(faces[i]) - 1);
            }
            result.add(res);
        }

        result.add(1, Integer.toString(num_of_vertices) + " " + Integer.toString(num_of_faces) + " 0");
        
        for(int i = 0; i < result.size(); i++)
        {
            pw.println(result.get(i));
        }

        s.close();
        pw.close();
    }
}