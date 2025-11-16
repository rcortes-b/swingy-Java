package me.rcortesb.swingy.models;
import me.rcortesb.swingy.controller.GameMove;

public class GameMap {
	int x;
	int y;
	
	/* Constructor for center of the map */
	public GameMap(int val) {
		this.x = val;
		this.y = val;
	}

	/* Constructor to place villains */
	public GameMap(int p_x, int p_y) {
		this.x = p_x;
		this.y = p_y;
	}

	public boolean matchCoords(GameMap map) {
		if (this.x == map.x && this.y == map.y)
			return true;
		return false;
	}

	public void updateCoords(GameMove dir) {
		if (dir == GameMove.NORTH)
			this.y--;
		else if (dir == GameMove.SOUTH)
			this.y++;
		else if (dir == GameMove.EAST)
			this.x++;
		else
			this.x--;

	}

	public boolean isBorder(int map_size) {
		if (x == 0 || x == map_size - 1 || y == 0 || y == map_size - 1)
			return true;
		return false;
	}

}