package Parte1;  
import java.awt.EventQueue;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.JTabbedPane;  
import javax.swing.border.EmptyBorder;  
import javax.swing.JScrollPane;  
import javax.swing.JDesktopPane;  
import javax.swing.JInternalFrame;  

public class Practica01_01_b extends JFrame {  
    private JTabbedPane contentPane;  

    public static void main(String[] args) {  
        EventQueue.invokeLater(new Runnable() {  
            public void run() {  
                try {  
                    Practica01_01_b frame = new Practica01_01_b();  
                    frame.setVisible(true);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        });  
    }  

    public Practica01_01_b() {  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setBounds(100, 100, 450, 300);  
        contentPane = new JTabbedPane();  
        setTitle("Frame Practica 01 b");  
        contentPane.add("Pestaña 1", new JPanel());  
        JScrollPane scrollPane = new JScrollPane();  
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        contentPane.add("Pestaña 2", scrollPane);  
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  
        setContentPane(contentPane);  
        
        JDesktopPane desktopPane = new JDesktopPane();  
        contentPane.addTab("Pestaña 3", null, desktopPane);  
        JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");  
        contentPane.addTab("Pestaña 4", null, internalFrame);  
        internalFrame.setVisible(true);  
    }
}