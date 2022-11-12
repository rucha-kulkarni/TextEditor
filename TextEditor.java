// Basic text editor using java swing
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class TextEditor implements ActionListener {

    //creating frame
    JFrame frame;
    //creating textArea
    JTextArea textArea;
    //creating jMenuBar
    JMenuBar jMenuBar;
    //creating constructor
    TextEditor(){

        //Initialising the frame
        frame = new JFrame("Text Editor");
        //Initialising the text area
        textArea = new JTextArea();
        //Initialising the menu bar
        jMenuBar = new JMenuBar();
        //Initialising the menu
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        //Initialising the file menu options
        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File ");
        JMenuItem newFile = new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);
        // Added menu options to file
        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        //Initialising the edit menu options
        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");


        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);

        //Added menu options to edit
        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(Close);

        //Added file menu and edit menu to MenuBar
        jMenuBar.add(file);
        jMenuBar.add(edit);

        // set menu bar at top
        frame.setJMenuBar(jMenuBar);

        // added text area to frame
        frame.add(textArea);

        // other frame stuff
        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if(call == "New File"){
            textArea.setText("");
        }else if(call == "Cut"){
            textArea.cut();
        }else if(call == "Copy"){
            textArea.copy();
        }else if(call == "Paste"){
            textArea.paste();
        }else if(call == "Close"){
            frame.setVisible(false);
        }else if(call == "Save File"){
            // JFileChooser is property of java swing to choose a file from folder/directory
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null); // stores 0  if file is chosen
            if(ans == jFileChooser.APPROVE_OPTION)
            {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;

                try{
                    writer = new BufferedWriter(new FileWriter(file,false));
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }

                try{
                    writer.write(textArea.getText());
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }

                try{
                    writer.flush();
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }

                try{
                    writer.close();
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }

            }
        }else if(call == "open File"){
            JFileChooser jFileChooser1 = new JFileChooser("C:");
            int ans = jFileChooser1.showOpenDialog(null); // stores 0  if file is chosen
            if(ans == jFileChooser1.APPROVE_OPTION)
            {
                File file = new File(jFileChooser1.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "",s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2 += s1 + "\n";
                    }
                    textArea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call == "Print File"){
            try {
                textArea.print();
            }
            catch(PrinterException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
