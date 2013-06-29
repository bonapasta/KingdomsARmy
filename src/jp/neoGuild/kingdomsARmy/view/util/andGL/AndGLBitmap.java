package jp.neoGuild.kingdomsARmy.view.util.andGL;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import jp.androidgroup.nyartoolkit.utils.gl.AndGLHelper;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLView;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLView.IGLViewEventListener;
import android.graphics.Bitmap;
import android.util.Log;

public class AndGLBitmap implements IGLViewEventListener {
	private int textureId;

	protected GL10 gl;

	private FloatBuffer vertex;
	private FloatBuffer texture;
	private ByteBuffer index;
	private int minFilter;
	private int magFilter;

	public AndGLBitmap(AndGLView andGLView, float size){
		this(andGLView, size, GL10.GL_NEAREST, GL10.GL_NEAREST);
	}

	public AndGLBitmap(AndGLView andGLView, float size,int minFilter, int magFilter) {
		andGLView._evl.add(this);

		float s=size/4;
		float[] vertices = {
			-s, -s, 0,
			s,  -s, 0,
			s,  s, 0,
			-s, s, 0,
		};
		this.vertex=AndGLHelper.makeFloatBuffer(vertices);
		byte[] indices = {0, 1, 2, 2, 3, 0};
		this.index=AndGLHelper.makeByteBuffer(indices);
		float[] textures = {
			0.0f, 1.0f,
			1.0f, 1.0f,
			1.0f, 0.0f,
			0.0f, 0.0f
		};
		this.texture=AndGLHelper.makeFloatBuffer(textures);

		this.gl = null;
		this.textureId = 0;
		this.minFilter = minFilter;
		this.magFilter = magFilter;

	}

	public void setTextureId(int textureId){
		this.textureId = textureId;
	}

	public void setBitmap(Bitmap bitmap){
		this.textureId = Texture.setBitmap(gl, bitmap);
	}

	/**
	 * This function changes the matrix mode to MODEL_VIEW , and change some parameter.
	 * @param i_x
	 * @param i_y
	 * @param i_z
	 */
	public void draw(float i_x,float i_y,float i_z){
		Log.d("AndGLBitmap.draw","start");

		if(gl == null){
			Log.d("AndGLBitmap.draw","gl is null");
			return;
		}

		if(this.textureId == 0){
			Log.d("AndGLBitmap.draw","textureId is 0");
			return;
		}

		//テクスチャを貼り付け
		Log.d("AndGLBitmap.draw","textureId is "+this.textureId);

		Texture.setFilters(gl,this.textureId, minFilter, magFilter);
		Texture.bindTexture(gl, this.textureId);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//頂点のマッピング
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0 ,this.vertex);
		//テクスチャのマッピング
		gl.glTexCoordPointer(2,GL10.GL_FLOAT, 0,this.texture);

		//描画
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glTranslatef(i_x,i_y,i_z);
		gl.glPushMatrix();
		gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE,this.index);
		gl.glPopMatrix();

		Log.d("AndGLBitmap.draw","end");
	}

	public void onGlChanged(GL10 i_gl, int i_width, int i_height) {
		if(this.gl==null){
			this.gl=i_gl;
		}
	}

	public void onGlMayBeStop() {
		this.gl=null;
	}

	public void dispose(){
		Texture.dispose(this.gl, this.textureId);
	}
}
