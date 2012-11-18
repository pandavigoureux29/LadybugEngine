package com.ladybug.engine.game;

import  com.ladybug.engine.gameobject.GameObject;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game2D implements ApplicationListener{
	
	public static int WIDTH = 640;
	public static int HEIGHT = 360;
	
	public static Game2D instance;
			
	public static SpriteBatch batch;
	
	//Scenes of the games
	public  ArrayList<Scene> m_scenes;
	//first scene to play
	private Scene m_firstScene;
	//current scene
	Scene sceneCurrent;

	public Game2D(){
		
	}
	
	/**
	 * Add a scene to the scene list of the game
	 */
	public void addScene(Scene scene){
		//if no scene has been added, create the list
		if(m_scenes == null)
			m_scenes = new ArrayList<Scene>();
		//add scene
		m_scenes.add(scene);
		//set first scene by default
		if(m_firstScene == null)
			m_firstScene = scene;
	}
	
	/**
	 * Do not override
	 */
	@Override
	public void create() {		
		instance = this;
		
		Global.assets = new AssetManager();
		//this creates the array of layers for collision
		LayerManager.Init();
		setLayersCollisions();
	
		//take the first scene first
		sceneCurrent = m_firstScene;
		Global.currentScene = sceneCurrent;
		//begin loading first scene
		m_firstScene.load();
		m_firstScene.init();
		
	}
	
	protected void setLayersCollisions(){
	}
	
	@Override
	public void dispose() {
		Global.assets.dispose();
		sceneCurrent.dispose();
	}

	@Override
	public void render() {	
		Global.currentScene.render();
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
