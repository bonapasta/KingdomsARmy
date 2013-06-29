package jp.neoGuild.kingdomsARmy.view.util.andGL;

import javax.microedition.khronos.opengles.GL10;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Texture {

	/*
	 * Bitmapをテクスチャとして登録する
	 */
	public static int setBitmap(GL10 gl10,Bitmap bitmap){
		int textureId = 0;
		int textureIds[] = new int[1];

		gl10.glGenTextures(1, textureIds, 0);
		textureId = textureIds[0];
		gl10.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		return textureId;
	}

	/*
	 * フィルタを設定する
	 */
	public static void setFilters(GL10 gl10, int minFilter, int magFilter,int textureId){
		gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

		gl10.glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}

	/*
	 * テクスチャを貼り付ける
	 */
	public static void bindTexture(GL10 gl10, int textureId){
		gl10.glEnable(GL10.GL_TEXTURE_2D);
		gl10.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
	}

	/*
	 * テクスチャを破棄する
	 */
	public static void dispose(GL10 gl10, int textureId){
		gl10.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		int[] textureIds = {textureId};
		gl10.glDeleteTextures(1, textureIds, 0);
	}
}
