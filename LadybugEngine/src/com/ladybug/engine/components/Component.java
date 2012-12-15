package com.ladybug.engine.components;

import  com.ladybug.engine.gameobject.GameObject;

public class Component {
	
	protected GameObject m_object;
	protected TYPE m_type;
	
	public boolean enabled = true;
	
	public Component(){}
	
	public void awake(){}
	
	public void start(){}
	
	public void preUpdate(){}

	public void update(){}
	
	public void postUpdate(){}
	
	//------------------------------------
	//         GETTERS SETTERS
	//------------------------------------
	
	public GameObject getObject(){
		return m_object;
	}
	
	public void setObject(GameObject go){
		m_object = go;
	}
	
	public TYPE getType(){
		return m_type;
	}
	
	public void reset(){}
	// ******* STATIC VARIABLES **********
	public enum TYPE{
		RIGIDBODY , COLLIDER , SCRIPT
	}
}
