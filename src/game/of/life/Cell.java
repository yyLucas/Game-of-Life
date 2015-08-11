package game.of.life;

public class Cell {
	
	public cellState state;
	
	public Cell(cellState state){
		this.state = state;
	}
	
	public enum cellState{
		ALIVE,DEAD;
	}
	
	public cellState getCellState(){
		return state;
	}
	
	public cellState getNextState(int aliveNeighhbour, cellState state){
		if (state == cellState.ALIVE){
			if (aliveNeighhbour <2 || aliveNeighhbour > 3){
				return cellState.DEAD;
			}else{
				return cellState.ALIVE;
			}
		}else if(state == cellState.DEAD){
			if (aliveNeighhbour == 3){
				return cellState.ALIVE;
			}
		}
		return state;		
	}
	
	
	
}
