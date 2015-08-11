package game.of.life;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Transient;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.of.life.Cell.cellState;

@SuppressWarnings("serial")
public class Grid extends JPanel{

	public Cell[][] gridState;
	public static int row;
	public static int column;
	private int gridWidth;
	private int generationCounter;
	
	public Grid(int width, int height, int gridWidth) {
		row = height/gridWidth;
		column = width/gridWidth;
		this.gridWidth = gridWidth;
        gridState = new Cell[row][column];       
        setupGrid();
    }
	
	public void setupGrid(){
		for (int i = 0; i < row; i++) {
			for(int j=0; j < column; j++){
				if(Math.random()>0.85){
					gridState[i][j] = new Cell(cellState.ALIVE);
				}else{
					gridState[i][j] = new Cell(cellState.DEAD);
				}
			}
		}
		
		gridState[2][2] = new Cell(cellState.ALIVE);
		gridState[2][3] = new Cell(cellState.ALIVE);
		gridState[2][4] = new Cell(cellState.ALIVE);


	}
	
	public void updateGrid() {
		Cell[][] nextGridState = new Cell[row][column]; 
		cellState[][] state = new cellState[row][column];
        for (int i = 0; i < gridState.length; i++) {
            for (int j = 0; j < gridState[i].length; j++) {
            	int aliveNeighbour = countAliveNeighbour(i, j);
            	//System.out.println(aliveNeighbour);
            	state[i][j] = gridState[i][j].getNextState(aliveNeighbour, gridState[i][j].getCellState());
            	nextGridState[i][j] = new Cell(state[i][j]);
            }
        }
        gridState = nextGridState;
    }
	
	public int countAliveNeighbour(int targetRow, int targetCol){		
		int aliveNeighbour = 0;
		ArrayList<Cell> cell = new ArrayList<Cell>();
		int neighbourTopRow = targetRow -1;
		int neighbourBottomRow = targetRow +1;
		int neighbourLeftColumn = targetCol - 1;
		int neighbourRightColumn = targetCol + 1;
		
		if(neighbourTopRow < 0){
			neighbourTopRow = row-1;
		}
		if(neighbourBottomRow == row){
			neighbourBottomRow = 0;
		}
		if(neighbourLeftColumn < 0){
			neighbourLeftColumn = row-1;
		}
		if(neighbourRightColumn == column){
			neighbourRightColumn = 0;
		}

		cell.add(gridState[neighbourTopRow][neighbourLeftColumn]);
		cell.add(gridState[neighbourTopRow][targetCol]);
		cell.add(gridState[neighbourTopRow][neighbourRightColumn]);
		cell.add(gridState[targetRow][neighbourLeftColumn]);
		cell.add(gridState[targetRow][neighbourRightColumn]);
		cell.add(gridState[neighbourBottomRow][neighbourLeftColumn]);
		cell.add(gridState[neighbourBottomRow][targetCol]);
		cell.add(gridState[neighbourBottomRow][neighbourRightColumn]);
		
		for(Cell object : cell) {
		    if(object.getCellState() == cellState.ALIVE){
		    	aliveNeighbour++;
		    }
		}
		
		return aliveNeighbour;
	}
	
	
	@Override
    @Transient
    public Dimension getPreferredSize() {
        return new Dimension(gridState[0].length * gridWidth, gridState.length * gridWidth);
    }
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
	    
	    int drawingPosition = 0;

	    for(int i = 0; i <= gridState.length; i++)
	    {
	    	drawingPosition = i*gridWidth;
	        g.drawLine(0, drawingPosition, column*gridWidth, drawingPosition);	
	    }
	    
	    for(int j = 0; j <= gridState[0].length; j++)
	    {
	    	drawingPosition = j*gridWidth;
	        g.drawLine(drawingPosition, 0, drawingPosition, row*gridWidth);
	    }
	    
	    g.setColor(Color.BLUE);
	    g.drawString("Generation: " + generationCounter++, 0, 10);
	    
	    //int counter = 0;
        
        for (int i = 0; i < gridState.length; i++) {
            for (int j = 0; j < gridState[i].length; j++) {
                if (gridState[i][j].getCellState() == cellState.ALIVE) {
                    g.setColor(Color.red);
                    //counter++;
                    g.fillRect(j * gridWidth, i * gridWidth, gridWidth, gridWidth);
                }
            }
        }
        //System.out.println(counter);
    }
	
		
	public static void main(String[] args){
		final Grid grid = new Grid(750, 750, 15);
        JFrame frame = new JFrame();
        frame.setTitle("Conway's Game Of Life");
        frame.getContentPane().add(grid);
        frame.setResizable(false);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
            
        new Timer(100, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                grid.updateGrid();
                grid.repaint();
            }
        }).start();
	}
	
}
