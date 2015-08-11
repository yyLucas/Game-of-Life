package test.game.of.life;

import static org.junit.Assert.*;
import game.of.life.Cell;
import game.of.life.Cell.cellState;
import game.of.life.Grid;

import org.junit.Before;
import org.junit.Test;

public class GridTest {
		
	public Grid grid;
	@Before
	public void initial(){
		grid = new Grid(75, 75, 15);
		grid.gridState = new Cell[][]{
			{ new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD) },
			{ new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD) },
			{ new Cell(cellState.DEAD), new Cell(cellState.ALIVE), new Cell(cellState.ALIVE), new Cell(cellState.ALIVE), new Cell(cellState.DEAD), },
			{ new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD) },
			{ new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD), new Cell(cellState.DEAD) },
		};
	}

	@Test
	public void countAliveNeighbourTest() {
		int aliveNeighbour = grid.countAliveNeighbour(1,1);
		assertEquals(aliveNeighbour, 2);
		
		aliveNeighbour = grid.countAliveNeighbour(1,2);
		assertEquals(aliveNeighbour, 3);
		
		aliveNeighbour = grid.countAliveNeighbour(1,3);
		assertEquals(aliveNeighbour, 2);
		
		aliveNeighbour = grid.countAliveNeighbour(2,1);
		assertEquals(aliveNeighbour, 1);
		
		aliveNeighbour = grid.countAliveNeighbour(2,2);
		assertEquals(aliveNeighbour, 2);
		
		aliveNeighbour = grid.countAliveNeighbour(2,3);
		assertEquals(aliveNeighbour, 1);
		
		aliveNeighbour = grid.countAliveNeighbour(3,1);
		assertEquals(aliveNeighbour, 2);
		
		aliveNeighbour = grid.countAliveNeighbour(3,2);
		assertEquals(aliveNeighbour, 3);
		
		aliveNeighbour = grid.countAliveNeighbour(3,3);
		assertEquals(aliveNeighbour, 2);
		
		aliveNeighbour = grid.countAliveNeighbour(0,0);
		assertEquals(aliveNeighbour, 0);

	}

}
