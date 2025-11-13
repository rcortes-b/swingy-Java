package me.rcortesb.swingy.models;

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



}