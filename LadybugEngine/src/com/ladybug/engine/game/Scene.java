package com.ladybug.engine.game;


import java.util.ArrayList;

import  com.ladybug.engine.gameobject.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Scene {
	public static int WIDTH = 640;
	public static int HEIGHT = 360;
	//objects of the scene
	ArrayList<GameObject> m_objects;
	//stages of the scene
	ArrayList<Stage> m_stages;
	//Camera
	OrthographicCamera m_mainCamera;
	public static SpriteBatch batch;
	
	boolean loading = true;
	boolean reseting = false;

	//collisionManager
	CollisionManager m_collisionManager;
	
	public Scene(){
		m_objects = new ArrayList<GameObject>();
		m_stages = new ArrayList<Stage>();
	}
	
	//----------------------------------------------
	//				INITIALISATION
	//----------------------------------------------
	public void init(){
				
		for(int i=0; i< m_objects.size() ; i++){
			m_objects.get(i).coreAwake();
		}
		for(int i=0; i< m_objects.size() ; i++){
			m_objects.get(i).coreStart();
		}

		initCollisionManager();
	}
	

	//====================================
	// 		COLLISION MANAGER
	//====================================
	void initCollisionManager(){
		m_collisionManager = new CollisionManager();
		//get objects of the scene
		ArrayList<GameObject> objects = getObjects();
		for(int i=0; i<objects.size();i++){
			//take all objects contained 
			ArrayList<GameObject> objs = objects.get(i).getAllObjects();
			//add every collider to the collision manager
			for(int j =0; j<objs.size();j++){
				m_collisionManager.addCollider(objs.get(j).collider);
			}
		}
	}
	
	//----------------------------------------------
	//				ASSETS LOADING
	//----------------------------------------------
	/**
	 * Load the assets into the AssetManager
	 */
	public void load(){
		batch = new SpriteBatch();	
		m_mainCamera = new OrthographicCamera();
		m_mainCamera.setToOrtho(false, WIDTH, HEIGHT);
		Global.mainCamera = m_mainCamera;
		for(int i=0; i< m_objects.size() ; i++)
			m_objects.get(i).loadAssets();
	}
	
	/**
	 * Binds the loaded assets
	 */
	public void bindAssets(){
		for(int i=0; i< m_objects.size() ; i++)
			m_objects.get(i).bindAssets();		
	}
	
	//----------------------------------------------
	//				CAMERAS
	//----------------------------------------------
	
	public void addCamera(OrthographicCamera camera){
		m_mainCamera = camera ;
	}
	
	/**
	 * Execute the update process
	 */
	public void updateObjects(){
		//preupdate
		for(int i=0; i< m_objects.size() ; i++){
			m_objects.get(i).corePreUpdate();
		}
		//update
		for(int i=0; i< m_objects.size() ; i++){
			m_objects.get(i).coreUpdate();
		}
		//postupdate
		for(int i=0; i< m_objects.size() ; i++){
			m_objects.get(i).corePostUpdate();
		}
			
	}
	
	/**
	 * Draw Objects in the scene
	 * @param batch SpriteBatch
	 */
	public void drawObjects(SpriteBatch batch){
		for(int i=0; i< m_objects.size() ; i++)
			m_objects.get(i).draw(batch);		
	}
	
	/**
	 * Add an object to the scene
	 * @param go GameObject to add
	 */
	public void addObject(GameObject go){
		m_objects.add(go);
	}
	
	/**
	 * Add a stage to the scene
	 * @param _stage Stage to add
	 */
	public void addStage(Stage _stage) {
		m_stages.add(_stage);
	}

	public void dispose() {
		batch.dispose();
		for(int i=0;i<m_objects.size();i++)
			m_objects.get(i).dispose();
	}

	public void render() {
		//while loading
		if( loading ){
			if(Global.assets.update()){
				//bind the asset
				bindAssets();
				//execute awakes & starts
				init();
				loading = false;
			}
			return;
		}
		
		if(Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)){
			reset();
			reseting = true;
		}else{
			reseting = false;
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//update objects
		updateObjects();
		//update Collision Manager
		m_collisionManager.update();
		//update camera
		m_mainCamera.update();
		
		batch.setProjectionMatrix(m_mainCamera.combined);
		batch.begin();
			drawObjects(batch);
		batch.end();
		
		for (int i=0; i<m_stages.size(); i++) {
			m_stages.get(i).act(Gdx.graphics.getDeltaTime());
			m_stages.get(i).draw();
		}
	}
	
	public void reset() {
		for(int i=0; i<m_objects.size();i++)
			m_objects.get(i).reset();
		
		for(int i=0; i<m_stages.size();i++)
			m_stages.get(i).clear();
	}
	
	public ArrayList<GameObject> getObjects() {
		return m_objects;
	}
	
	public ArrayList<Stage> getStages() {
		return m_stages;
	}

}
