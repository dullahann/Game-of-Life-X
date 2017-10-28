package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.rule;


public class view extends JFrame {
   
    public static JPanel[][] block;     //Ï¸°ûÐ¡¸ñ×Ó
    private final rule temp;
    
	public view(int m, int n) {
		
		temp = new rule(m, n);
		new Thread(temp).start();
		add(temp);
		
    }

	public static void main(String[] args) {
		int m = 60;
		int n = 60;
		view frame = new view(m, n);
		
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);
        
        JMenu options = new JMenu("Options");
        menu.add(options);
        
        JMenuItem arrow = options.add("Line");
        arrow.addActionListener(frame.new LineActionListener());
        
        JMenuItem square = options.add("Square");
        square.addActionListener(frame.new SquareActionListener());        
        
        JMenuItem random = options.add("Ramdom");
        random.addActionListener(frame.new RandomActionListener());   
        
        JMenu help = new JMenu("Help");        
        menu.add(help);
        
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    
    class LineActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		temp.clear();
    		temp.line();
    	}
    }
    class SquareActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		temp.clear();
    		temp.rectangle();
    	}
    }
    class RandomActionListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e) 
    	{
    		temp.clear();
    		temp.randomized();
    	}
    }


}
