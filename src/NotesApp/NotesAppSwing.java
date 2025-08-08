package NotesApp;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class NotesAppSwing  extends JFrame {
 private JTextArea textArea;
 private JButton saveButton,loadButton;
 private static final String FILE_NAME="notes.txt";
 public NotesAppSwing() {
     setTitle("Notes App");
     setSize(600, 500);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setLocationRelativeTo(null);

     textArea = new JTextArea();
     textArea.setFont(new Font("Arial", Font.PLAIN, 14));

     saveButton = new JButton("Save Note");
     loadButton = new JButton("Load Notes");

     JPanel bottonPanel = new JPanel();
     bottonPanel.add(saveButton);
     bottonPanel.add(loadButton);

     setLayout(new BorderLayout());
     add(new JScrollPane(textArea), BorderLayout.CENTER);
     add(bottonPanel, BorderLayout.SOUTH);

     saveButton.addActionListener(e -> saveNote());
     loadButton.addActionListener(e -> loadNotes());
 }
     private void saveNote(){
         try(FileWriter fw=new FileWriter(FILE_NAME,true)) {
             String note = textArea.getText().trim();
             if (!note.isEmpty()) {
                 fw.write(note + "\n");
                 JOptionPane.showMessageDialog(this, "Note saved sucessfully!");
                 textArea.setText("");
             } else {
                 JOptionPane.showMessageDialog(this,"Please enter some text");
             }
         }
             catch(IOException e){
                 JOptionPane.showMessageDialog(this,"Error writing to file" +e.getMessage());
             }
         }
    private void loadNotes(){
     textArea.setText("");
        try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            StringBuilder notes = new StringBuilder();
            while ((line = br.readLine()) != null) {
                notes.append(line).append("\n");
            }
            textArea.setText(notes.toString());
        }
            catch(FileNotFoundException e) {
                JOptionPane.showMessageDialog(this,"No notes file found.Please save a note First.");
            }

        catch(IOException e){
            JOptionPane.showMessageDialog(this,"Error writing to file" +e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new NotesAppSwing().setVisible(true);
        });
    }



}



