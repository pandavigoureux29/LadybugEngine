package com.ladybug.engine.components;

import com.badlogic.gdx.math.Rectangle;
import com.ladybug.engine.gameobject.GameObject;

public class BoxCollider extends Collider{

	protected Rectangle m_rect;
	
	//Rectangles for collision detection
	Rectangle rectTemp,rectTempOther;
	
	public BoxCollider(){ 
		m_rect = new Rectangle();
		m_collType = Type.BOX;
	}
	
	/**
	 * Create a BoxCollider with a Rectangle set with width & height
	 * @param w width
	 * @param h height
	 */
	public BoxCollider(float w, float h){
		m_rect = new Rectangle(0,0,w,h);
		m_collType = Type.BOX;
	}
	
	public BoxCollider(Rectangle rect){
		m_rect = rect;
		m_collType = Type.BOX;
	}
	
	@Override
	public void scale(float w,float h){
		m_rect.width *= w;
		m_rect.height *= h;
	}
		
	@Override 
	protected boolean collide(BoxCollider coll){
		if(getBounds().overlaps(coll.getBounds()))
			return true;
		return false;
	}
	
	
	//===================================
	// 			GETTERS SETTERS
	//===================================
	/**
	 * Returns the rectangle in the local position
	 * @return
	 */
	public Rectangle getRectangle(){
		return m_rect;
	}
	
	/**
	 * Returns the bounds of the collider in the world
	 * @return
	 */
	protected Rectangle getBounds(){
		return  new Rectangle( m_object.getPosition().x + m_rect.x, 
					m_object.getPosition().y + m_rect.y,
					m_rect.width , m_rect.height );
	}
	
	@Override
	public float top(){
		return m_object.getPosition().y + m_rect.y;
	}
	
	@Override
	public float bottom(){
		return m_object.getPosition().y + m_rect.y + m_rect.height;
	}
	
	@Override
	public float right(){
		return m_object.getPosition().x + m_rect.x + m_rect.width;
	}
	
	@Override
	public float left(){
		return m_object.getPosition().x + m_rect.x;
	}
	
	public void setRectangle(Rectangle rect){
		m_rect = rect;
	}
	
	@Override 
	public void setObject(GameObject go){
		super.setObject(go);
	}
	
}
