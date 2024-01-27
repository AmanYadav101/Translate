package Translator;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UI  extends  JFrame{
    private  final ArrayList<String> path =new ArrayList<>(9);
    private final JTextArea fileName = new JTextArea();
    private final JTextArea output = new JTextArea();

    UI(){
        setSize(500,650);
        JLabel selectedFilesLabel = new JLabel("Selected Files");
        JButton selectButton = new JButton("Select Files");
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        JTextArea fileNames= new JTextArea();
        Font fontNormal = new Font("Arial",Font.PLAIN,16);
        selectedFilesLabel.setBounds(150,60,200,30);
        selectButton.setBounds(175,20,150,30);
        submitButton.setBounds(210,300,80,30);
        clearButton.setBounds(310,300,80,30);

        this.fileName.setBounds(75,60,350,230);
        this.fileName.setEditable(false);
        this.output.setBounds(75,330,350,230);
        this.output.setEditable(false);

        selectedFilesLabel.setFont(fontNormal);
        selectedFilesLabel.setHorizontalAlignment(selectedFilesLabel.CENTER);
        fileNames.setEditable(false);
        add(this.fileName);
        add(selectedFilesLabel);
        add(selectButton);
        add(submitButton);
        add(clearButton);
        add(this.output);
        selectFileWindow(selectButton);
        submitFiles(submitButton);
        clearFiles(clearButton);
        setLayout(null);
        setVisible(true);
        setTitle("Language Translator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        output.setFont(new Font("Nirmala UI", Font.PLAIN, 12)); // Example font setting

    }

    private void clearFiles(JButton clearButton) {
        clearButton.addActionListener((e)->{
            path.clear();
            fileName.setText(""); // Clear the JTextArea

        });
    }

    private void add(StringBuilder fileName) {

    }


    private void submitFiles(JButton submit) {
        Read other = new Read();
        submit.addActionListener((e)->{
            synchronized (path) {
                for (String path : this.path) {
                    other.reading(path);

                }
                try {
                    loadContentIntoOutputTextArea("C:\\Users\\amanb\\Documents\\output.txt");
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    private void loadContentIntoOutputTextArea(String fileName) throws FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\amanb\\Documents\\output.txt"), StandardCharsets.UTF_8))) {
                StringBuilder content  = new StringBuilder();
                String line;
                while ((line =reader.readLine())!=null){
                    content.append(line).append("\n");
            }
                output.setText(content.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void selectFileWindow(JButton select) {
        select.addActionListener(e ->{
            JFrame f1 =new JFrame();
            JFileChooser fc = new JFileChooser();
            fc.setBounds(20,20,30,30);
            f1.setSize(400,400);
            f1.setVisible(true);
            f1.add(fc);
            selectedPath(fc,f1);

        }
        );
    }

    private void selectedPath(JFileChooser fileChooser, JFrame frame) {
        int result = fileChooser.showOpenDialog(frame);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            frame.dispose();
            fileName.append(selectedFile.getAbsolutePath()+"\n");
            path.add(selectedFile.getAbsolutePath());
        }
        frame.dispose();
    }

}
