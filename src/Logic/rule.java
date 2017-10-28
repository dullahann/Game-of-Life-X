package Logic;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import Frame.view;

public class rule extends JPanel implements Runnable{
    public static boolean[][] alive;    //Ï¸°ûËÀ»î
    public static boolean[][] nextAlive;
    int row;
    int column;
	private volatile boolean isChanging = false; 
    
    public rule(int m,int n){
    	row = m;
    	column = n;
		alive = new boolean[m][n];
		nextAlive = new boolean[m][n];
    	for(int i =0;i<m;i++){
    		for(int j =0;j<n;j++){
    			alive[i][j] = false;
    			nextAlive[i][j] = false;
    		}
    	}
    }

	public void clear() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				alive[i][j] = false;
			}
		}
	}
    public void randomized() {
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                if (Math.random() > 0.5) {
                    alive[i][j] = true;
                } else {
                    alive[i][j] = false;
                }
            }
        }
    }
 
	public void line() {
		isChanging = true;
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				alive[24][25 + i] = true;
			}
			isChanging = false;
			this.notifyAll();
		}
	}
	
	public void rectangle() {
		isChanging = true;
		synchronized (this) {
			for (int i = 20; i < 30; i++) {
				for (int j = 20; j < 30; j++) {
					alive[i][j] = true;
				}
			}
			isChanging = false;
			this.notifyAll();
		}
	}
    
	private boolean isValidCell(int x, int y)
	{
		if((x >= 0) && (x < row) && (y >= 0) && (y < column))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void change(int x,int y) {
		int counter = 0;
		
		if(isValidCell(x - 1, y - 1) && (alive[x - 1][y - 1] == true))
		{
			counter++;
		}

		if(isValidCell(x, y - 1) && (alive[x][y - 1] == true))
		{
			counter++;
		}	
		
		if(isValidCell(x + 1, y - 1) && (alive[x + 1][y - 1] == true))
		{
			counter++;
		}

		if(isValidCell(x + 1, y) && (alive[x + 1][y] == true))
		{
			counter++;
		}
		
		if(isValidCell(x + 1, y + 1) && (alive[x + 1][y + 1] == true))
		{
			counter++;
		}

		if(isValidCell(x, y + 1) && (alive[x][y + 1] == true))
		{
			counter++;
		}
		
		if(isValidCell(x - 1, y + 1) && (alive[x - 1][y + 1] == true))
		{
			counter++;
		}

		if(isValidCell(x - 1, y) && (alive[x - 1][y] == true))
		{
			counter++;
		}	
		
		if(counter == 3)
		{
			nextAlive[x][y] = true;
		}
		else if(counter == 2)
		{
			nextAlive[x][y] = alive[x][y];
		}
		else
		{
			nextAlive[x][y] = false;
		}
	}
	
    
	private void sleep(int x)
	{
		try 
		{
			Thread.sleep(1000 * x);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int i = 0; i < row; i++) 
        {
            for (int j = 0; j < column; j++) 
            {
            	if(rule.alive[i][j] == true)
            	{
            		g.setColor(Color.red);
            		g.fillRect(j * 10, i * 10, 10, 10);
            	}
            	else
            	{
            		g.setColor(Color.green);
                    g.drawRect(j * 10, i * 10, 10, 10);            		
            	}
            }
        }
        
	}
	
	public void run()
	{
		while(true)
		{
			synchronized(this)
			{
				
				while(isChanging)
				{
					try 
					{
						this.wait();
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				
				repaint();
				sleep(1);
				
				for(int i = 0; i < row; i++)
				{
					for(int j = 0; j < column; j++)
					{
						change(i, j);
					}
				}
			
				boolean[][] temp = null;
				temp = alive;
				alive = nextAlive;
				nextAlive = temp;
				
				for(int i = 0; i < row; i++)
				{
					for(int j = 0; j < column; j++)
					{
						nextAlive[i][j] = false;
					}
				}
			}
		}
	}
    
}
