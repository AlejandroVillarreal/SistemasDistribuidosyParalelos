package practica2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {
	
	
    private boolean DEBUG = false;
    ArrayList<ArrayList<String>> ar = new ArrayList<ArrayList<String>>();
    
    public Table(String [][] dato) {
        super(new GridLayout(1,0));
 
        String[] columnNames = {"Nombre",
        						"Procesador",
                                "Velocidad",
                                "RAM",
                                "HDD Total",
                                "HDD Disponible",
                                "S.O."};
 
        
        final JTable table = new JTable(dato, columnNames);
        //ar.add("Computadora3");
        //dato[0][0] = ar.get(0).get(1);
        //dato[0][1] = ar.get(0).get(2);
        //System.out.println(dato.length); //if dato.lenght = 3
        table.setPreferredScrollableViewportSize(new Dimension(700, 70));
        table.setFillsViewportHeight(true);
 
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
 
        JScrollPane scrollPane = new JScrollPane(table);
 
        add(scrollPane);
    }
    
    public String[][] generateRows (ArrayList <ArrayList<String>> arrList) {
    	
    	String [][] rowsGenerated = {{}};
    	
    	for(int i=0;i < arrList.size(); i++) {
    		
    		for(int j=0;i < 5; j++) {
    			rowsGenerated[i][j] = arrList.get(i).get(j);
    		}
    	}
    	
    	return rowsGenerated;
    }
    
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
        
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
 
    public static void createAndShowGUI(Table newContentPane) {
        JFrame frame = new JFrame("Equipos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Table newContentPane = new Table();
        
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        
    }
    
    public void showTable(Table newContentPane) {
    	
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Practica 2");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
                //Table newContentPane = new Table();
                
                newContentPane.setOpaque(true); 
                frame.setContentPane(newContentPane);
         
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}