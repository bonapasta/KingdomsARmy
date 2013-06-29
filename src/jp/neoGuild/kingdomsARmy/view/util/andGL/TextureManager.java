package jp.neoGuild.kingdomsARmy.view.util.andGL;

import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import android.graphics.Bitmap;


public class TextureManager {
	private static TextureManager instance;

	private HashMap<String,Integer> textureIds;
	private GL10 gl;

	public static void createInstance(GL10 gl){
		instance = new TextureManager(gl);
	}

	public static TextureManager getInstance(){
		return instance;
	}

	private TextureManager(GL10 gl){
		this.gl = gl;
		this.textureIds = new HashMap<String,Integer>();
	}

	public int setTexture(String key, Bitmap bitmap){
		int textureId = Texture.setBitmap(this.gl, bitmap);
		this.textureIds.put(key, textureId);
		bitmap.recycle();
		return textureId;
	}

	public int getTexture(String key){
		return this.textureIds.get(key);
	}

	public void disposeTexture(String key){
		Texture.dispose(this.gl, this.textureIds.get(key));
		this.textureIds.remove(key);
	}

	public void disposeTexture(int textureId){
		Texture.dispose(this.gl, textureId);
		for(Map.Entry<String, Integer> e : this.textureIds.entrySet()){
			if(e.getKey().equals(textureId)){
				this.textureIds.remove(e.getKey());
			}
		}
	}

	public void disposeAll(){
		for(Map.Entry<String, Integer> e : this.textureIds.entrySet()){
			this.textureIds.remove(e.getKey());
		}
	}
}
