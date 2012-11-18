package com.ladybug.engine.components;

import com.badlogic.gdx.math.Vector2;

public class Rigidbody extends Component {

	//Gravity acceleration
	protected float m_acceleration;
	//Velocity of the object
	protected Vector2 m_velocity;
	//if collision with the ground
	public boolean m_onGround = false;
	
	public boolean gravity = true;
	
	public Rigidbody(){
		m_type = TYPE.RIGIDBODY;
		m_velocity = new Vector2();
	}
	
	@Override
	public void update(){
		if(gravity)
			applyGravity();
		m_object.translate(m_velocity.x, m_velocity.y);
	}
	
	// ==================================
	//	 		GRAVITY
	// ==================================
	public float getAcceleration(){
		return m_acceleration;
	}
	
	public void setAcceleration(float acc){
		m_acceleration = acc;
	}
	
	public void applyGravity(){
		if(!m_onGround){
			m_acceleration += 0.1;
			m_object.translateY(-m_acceleration);
		}
	}
	
	// ==================================
	//	 		VELOCITY
	// ==================================
	
	public Vector2 getVelocity(){
		return m_velocity;
	}
	
	public void setVelocity(Vector2 v){
		m_velocity = v;
	}
	
	public void setVelocity(float x,float y){
		m_velocity.x = x;
		m_velocity.y = y;
	}
	
	public void addVelocity(float x,float y){
		m_velocity.x += x;
		m_velocity.y += y;
	}
	
	//==========RESET====================
	@Override
	public void reset(){
		m_onGround = false;
		m_velocity = new Vector2();
		m_acceleration = 0;
	}
}
