package com.ladybug.engine.components;

import  com.ladybug.engine.gameobject.GameObject;

public class Component {
	
	protected GameObject m_object;
	protected TYPE m_type;
	protected String m_componentClassName = "Component";
	
	public boolean enabled = true;
	
	public Component(){
		String s = this.getClass().toString() ;		
		s = s.substring(s.lastIndexOf(".")+1);
		m_componentClassName = s;
	}
	
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
	
	public String getComponentClassName(){
		return m_componentClassName;
	}
	
	public void reset(){}
	// ******* STATIC VARIABLES **********
	public enum TYPE{
		RIGIDBODY , COLLIDER , SCRIPT, RENDERER
	}
}
