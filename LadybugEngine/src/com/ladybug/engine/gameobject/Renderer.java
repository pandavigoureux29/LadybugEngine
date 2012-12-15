package com.ladybug.engine.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ladybug.engine.components.Component;
import com.ladybug.engine.game.Global;

public class Renderer extends Component {

	//RENDERER
	public String m_textureName;
	public int m_textureHeight, m_textureWidth;
	int m_textureOriginX, m_textureOriginY;
	
	//--------------------------------------------
	//------------CONSRTUCTORS--------------------
	//--------------------------------------------
	
	public Renderer(){
		m_type = TYPE.RENDERER;
	}
	
	public Renderer(String name){
		m_type = TYPE.RENDERER;
		m_textureName = name;
	}
	
	public Renderer(String name,int w, int h){
		m_type = TYPE.RENDERER;
		m_textureName = name;
		m_textureWidth = w;
		m_textureHeight = h;
	}
	
	
	public void loadAssets(){
		//do nothing if the texture name is null or blank
		if(m_textureName == null || m_textureName == "")
			return;
		//check if the texture is loaded
		if(! Global.assets.containsAsset(m_textureName)){
			Global.assets.load(m_textureName, Texture.class); //if not load it
		}
	}
	

	/**
	 * Loads the texture and creates the sprite with the texture size
	 * @param Texture texture : the texture name (loaded internal)
	 */

	protected void loadSprite(){
		//do nothing if the texture name is null or blank
		if(m_textureName == null || m_textureName == "" || !Global.assets.isLoaded(m_textureName, Texture.class) )
			return;
		
		Texture texture;	
		texture = Global.assets.get(m_textureName, Texture.class);
		
		//if texture size = 0
		if(m_textureHeight == 0 || m_textureWidth == 0){
			m_textureHeight = texture.getHeight();
			m_textureWidth = texture.getWidth();
		}
		
		TextureRegion region = new TextureRegion(texture, m_textureOriginX, m_textureOriginY, m_textureWidth, m_textureHeight);
		
		m_object.setRegion(region);
		
		m_object.setSize(m_textureWidth, m_textureHeight);
	}
	
	public void setFrame(int i, int j){
		int w = getObject().getTexture().getWidth();
		int h = getObject().getTexture().getHeight();
		
		if(i * m_textureWidth > w -m_textureWidth)
			return;
		
		if(j * m_textureHeight > h -m_textureHeight)
			return;
		
		getObject().setRegion( i * m_textureWidth , j *  m_textureHeight, m_textureWidth, m_textureHeight);
	}

}
