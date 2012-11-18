package com.ladybug.engine.game;

public class LayerManager {
	public static int DEFAULT = 0;
	public static int GROUND = 1;
	public static int PLAYER = 2;
	
	static int nbLayers = 3;
	
	private static boolean[][] layerTab = new boolean[nbLayers][nbLayers];
	
	public static void Init(){
		for(int i=0; i< nbLayers ; i++){
			for(int j=0; j< nbLayers ; j++){
				layerTab[i][j] = true;
			}
		}
	}
	
	public static void setCollision(int i, int j,boolean val){
		layerTab[i][j] = val;
	}
	
	public static boolean collide(int i, int j){
		return layerTab[i][j];
	}
}
