import java.io.*;

public class Caesar {
    public static void main(String[] args) {
        if(args.length > 1 && args.length < 4) {
            String key = args[0];
            try{
              int kNumber = Integer.parseInt(key);
            } catch(NumberFormatException e) {
                System.out.println("You noob.");
            }

            String inFile = args[1];
            String line;
            try{
                BufferedReader in = new BufferedReader(new FileReader(inFile));
                while((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }

            if (args.length == 3) {
                String outFile = args[2];
            }

        }
        else {
            System.out.println("Usage: Caesar key infile [outfile]");
        }
    }
}
