package com.ladybug.engine.components;

import com.ladybug.engine.gameobject.Renderer;

public class Script extends Component {
	
	public Script(){
		super();
		m_type = TYPE.SCRIPT;
	}
	
	public Rigidbody getRigidbody(){
		return getObject().rigidbody;
	}
	
	public Collider getCollider(){
		return getObject().collider;
	}
	
	public Renderer getRenderer(){
		return getObject().renderer;
	}

	//-----------------------------------------
	//				COLLISION
	//-----------------------------------------
	
	public void OnCollisionEnter(Collider collider){}
	
	public void OnCollisionStay(Collider collider){}
	
	public void OnCollisionExit(Collider collider){}
	
}
