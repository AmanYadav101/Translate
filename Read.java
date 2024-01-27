package Translator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.*;

public class Read {
    private static final  int CHUNK_SIZE = 400;
    static void reading(String path){
    try {
        String filepath = path;
//    String filepath = "C:\\Users\\amanb\\Documents\\example.txt";
    Path writeFile = Path.of("C:\\Users\\amanb\\Documents\\output.txt");
    String textToTranslate = readFile(filepath);
    if (!textToTranslate.isEmpty()){
        String targetLanguage = "hi";
        //Translating text
        StringBuilder translatedText = new StringBuilder();
        for (int i =0;i<textToTranslate.length();i+=CHUNK_SIZE){
            int end = Math.min(i+CHUNK_SIZE,textToTranslate.length());
            String chunk =textToTranslate.substring(i,end);
            String chunkTranslation = Translate.translateText(chunk,targetLanguage);
            translatedText.append(chunkTranslation);
        }


        //writing in a file
        Files.writeString(writeFile,translatedText.toString());

        //reading content of the output file
        String fileContent = Files.readString(writeFile);
        System.out.println(fileContent);
    }else {
        System.out.println("Input file is empty");
    }
} catch (IOException e) {
        e.printStackTrace();
    }
    }
    private static String readFile(String filePath) throws IOException{

        Path path = Paths.get(filePath);
        return Files.readString(path);

    }


}
