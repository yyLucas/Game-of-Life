package test.game.of.life;

import static org.junit.Assert.*;

import org.junit.Test;

import game.of.life.Cell;
import game.of.life.Cell.cellState;


public class CellTest {

	@Test
	public void dieByUnderPopulation() {
		Cell cell = new Cell(Cell.cellState.ALIVE);
		cellState actual = cell.getNextState(0,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(1,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);				
	}
	
	@Test
	public void liveWithTwoOrThreeNeighbour(){
		Cell cell = new Cell(Cell.cellState.ALIVE);
		cellState actual = cell.getNextState(2,cell.state);
		assertEquals(Cell.cellState.ALIVE, actual);
		actual = cell.getNextState(3,cell.state);
		assertEquals(Cell.cellState.ALIVE, actual);
	}
	
	@Test
	public void dieByOvercrowding(){
		Cell cell = new Cell(Cell.cellState.ALIVE);
		
		cellState actual = cell.getNextState(4,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(5,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(6,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(7,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(8,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
	}
	
	@Test
	public void aliveByReproduction(){
		Cell cell = new Cell(Cell.cellState.DEAD);
		cellState actual = cell.getNextState(3,cell.state);
		assertEquals(Cell.cellState.ALIVE, actual);
	}
	
	@Test
	public void remainDead(){
		Cell cell = new Cell(Cell.cellState.DEAD);
		cellState actual = cell.getNextState(0,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(1,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(2,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(4,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(5,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(6,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(7,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
		actual = cell.getNextState(8,cell.state);
		assertEquals(Cell.cellState.DEAD, actual);
	}
	
	@Test
	public void getCellStateTest(){
		Cell cell = new Cell(Cell.cellState.DEAD);
		assertEquals(Cell.cellState.DEAD, cell.getCellState());
		cell = new Cell(Cell.cellState.ALIVE);
		assertEquals(Cell.cellState.ALIVE, cell.getCellState());
	}
}
