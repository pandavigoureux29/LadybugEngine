package com.ladybug.engine.components;

import  com.ladybug.engine.gameobject.GameObject;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;

public class Collider extends Component {
	//list of colliders attached
	protected ArrayList<Collider> m_colliders;
	
	public boolean enabled = true;
	public boolean isTrigger = false;
	
	public Vector2 min;
	public Vector2 max;
	
	public Type m_collType;
	public int LAYER;
	
	//colliders that are actually colliding 
	protected ArrayList<Collider> m_currentColliders;
	//used for exiting collisions only, these are the collider added for the current frame
	protected ArrayList<Collider> m_frameColliders;
	
	public Collider(){
		min = new Vector2();
		max = new Vector2();
		m_type = Component.TYPE.COLLIDER;
		m_frameColliders = new ArrayList<Collider>();
		m_currentColliders = new ArrayList<Collider>();
	}
	
	public ArrayList<Collider> collide(Collider other){
		//a list of gameobjects that collide with "other"
		ArrayList<Collider> collisions = new ArrayList<Collider>();

		boolean isColliding = false;
		if(other.m_collType == Type.BOX)
			isColliding = collide((BoxCollider)other);
		
		//IF the global collider is not colliding, return null
		if(! isColliding)
			return null;
		
		//if there are other colliders, detect collisions with them
		if(m_colliders != null){
			ArrayList<Collider> childColl;
			for(int i = 0; i<m_colliders.size();i++){
				childColl = m_colliders.get(i).collide(other);
				if(childColl != null)
					collisions.addAll(childColl);
			}
		}
		
		//if no sub colliders collided
		if(collisions.size() == 0)
			collisions.add(other);
		
		return collisions;
	}
	
	protected boolean collide(BoxCollider other){
		return false;
	}
	
	public void scale(float w,float h){}
	
	//-----------------------------------------
	//				COLLISION
	//-----------------------------------------
	
	public void addCollision(Collider other){
		//if(m_object.m_name == "Player")
			//System.out.println("new " + other);
		//add it as a collision for this frame
		m_frameColliders.add(other);
	}
	
	public void processCollisions(){
		//if(m_object.m_name == "Player")
			//System.out.println( m_frameColliders.size());
		checkEnteringAndStillCollisions();
		checkExitingCollisions();
		clearBuffers();
	}
	
	
	private void checkEnteringAndStillCollisions(){
		if(m_frameColliders.size() > 0){
			Iterator<Collider> it = m_frameColliders.iterator();
			while(it.hasNext()){
				Collider c = (Collider) it.next();
				//ENTERING COLLISION
				if( ! m_currentColliders.contains(c)){
					m_currentColliders.add(c);
					getObject().onCollisionEnter(c);
				//STAYING COLLISION
				}else{
					getObject().onCollisionStay(c);
				}
			}
		}
	}
	
	private void checkExitingCollisions(){
		if(m_currentColliders.size() > 0){
			//iterator on current colliders for this object
			Iterator<Collider> it = m_currentColliders.iterator();
			while(it.hasNext()){
				Collider c = (Collider) it.next();
				//if the collider doesn't exist in the colliders for this frame
				if(! m_frameColliders.contains(c)){
					//if(m_object.m_name == "Player")
						//System.out.println( "exit" + c );
					//this is an exiting collision
					getObject().onCollisionExit(c);
					it.remove();
				}else{
					
				}
			}
		}
	}
		
	private void clearBuffers(){
		m_frameColliders.clear();
	}
	
	//=====================================
	// 			GETTERS SETTERS
	//=====================================
	
	public float top(){
		return max.y;
	}
	
	public float bottom(){
		return min.y;
	}
	
	public float right(){
		return max.x;
	}
	
	public float left(){
		return min.x;
	}
	
	public Type getCollType(){
		return m_collType;
	}
	
	public enum Type{
		BOX , DISC
	}
	
	@Override 
	public void setObject(GameObject go){
		super.setObject(go);
	}
	
}
