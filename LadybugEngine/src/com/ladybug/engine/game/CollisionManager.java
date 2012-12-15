package com.ladybug.engine.game;

import  com.ladybug.engine.gameobject.GameObject;

import java.util.ArrayList;

import  com.ladybug.engine.components.Collider;

public class CollisionManager {
	
	ArrayList<Collider> m_colliders;

	public CollisionManager(){
		m_colliders = new ArrayList<Collider>();
	}
	
	public void addCollider(Collider collider){
		m_colliders.add(collider);
	}
	
	public void update(){		
		Collider coll1,coll2;
		//go through all colliders and check collisions
		for(int i=0; i< m_colliders.size();i++){
			//if this is not the end of the array
			if(i+1 > m_colliders.size())
				break;
			//go through the rest of the array
			for(int j=i+1; j < m_colliders.size();j++){
				//get colliders
				coll1 = m_colliders.get(i);
				coll2 = m_colliders.get(j);
				//check nulls
				if(coll1 == null || coll2 == null)
					continue;
				//if the layers can't collide, skip
				if( ! LayerManager.collide(coll1.LAYER, coll2.LAYER))
					continue;
				checkCollisions(coll1, coll2);
			}
		}
		//System.out.println("========Check==========");
		//check for exiting collisions in colliders
		for(int i=0; i< m_colliders.size(); i++){
			if(m_colliders.get(i) != null)
				m_colliders.get(i).processCollisions();
		}
	}
	
	public void checkCollisions(Collider coll1, Collider coll2){
		//colliders on coll1
		ArrayList<Collider> colliders = coll1.collide(coll2);
		//for each collider
		if( colliders !=null && colliders.size()>0){
			//System.out.println(coll1.getObject().toString()+ "  " + coll2.getObject().toString());
			for(int c = 0; c< colliders.size(); c++){
				coll1.addCollision(colliders.get(c));
				colliders.get(c).addCollision(coll1);
			}
		}
	}
	
}
