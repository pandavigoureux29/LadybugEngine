package com.ladybug.engine.components;

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

	//-----------------------------------------
	//				COLLISION
	//-----------------------------------------
	
	public void OnCollisionEnter(Collider collider){}
	
	public void OnCollisionStay(Collider collider){}
	
	public void OnCollisionExit(Collider collider){}
	
}
