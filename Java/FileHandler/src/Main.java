import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        // Test ex 1        
        int[] result = countChars("countChars.txt");

        for(int x : result)
        {
            System.out.println(x);
        }
        
        // Test ex 2
        search("inputFile.txt", "outputFile.txt", "egzamin");
    }
    
    public static void search(String inputFile, String outputFile, String word)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)))
        {
            int lineNumber = 1;
            String line;
            while((line = br.readLine()) != null)
            {
                if(line.contains(word))
                {
                    bw.write(Integer.toString(lineNumber) + ": " + line);
                    bw.newLine();
                }
                ++lineNumber;
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static int[] countChars(String filePath)
    {
        // 0 - # of chars, 1 - # of white spaces, 2 - number of words
        int[] results = new int[3];
        char[] fileContent = getFileContentAsCharArray(filePath);

        results[0] = fileContent.length;
        results[1] = frequencyOfElement(fileContent, ' ')
                + frequencyOfElement(fileContent, '\t')
                + frequencyOfElement(fileContent, '\n');
        results[2] = countWords(fileContent);

        return results;
    }

    private static char[] getFileContentAsCharArray(String filePath)
    {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while((ch = br.read()) != -1)
            {
                stringBuilder.append((char)ch);
            }

            return stringBuilder.toString().toCharArray();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return new char[0];
    }

    private static int frequencyOfElement(char[] array, char searchElem)
    {
        int c = 0;
        for(char element : array)
        {
            if(element == searchElem)
                ++c;
        }
        return c;
    }

    private static int countWords(char[] content)
    {
        final int OUT = 0;
        final int IN = 1;
        int state = OUT;
        int wc = 0;
        int i = 0;

        while (i < content.length)
        {
            if (content[i] == ' ' || content[i] == '\n' || content[i] == '\t')
                state = OUT;

            else if (state == OUT)
            {
                state = IN;
                ++wc;
            }
            ++i;
        }
        return wc;
    }
}