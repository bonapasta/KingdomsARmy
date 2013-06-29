package jp.neoGuild.kingdomsARmy.view.util;

import java.io.IOException;

import jp.neoGuild.kingdomsARmy.common.util.GameFileIO;
import jp.neoGuild.kingdomsARmy.model.util.GameObject;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public abstract class AbstractDrawObject {
	public GameObject object;
	private int x;
	private int y;
	private int width;
	private int height;
	public float screenWidth;
	public float screenHeight;
	private int grid;
	public boolean isVisible;
	public boolean isEnabled;
	protected Bitmap bitmap;

	public AbstractDrawObject(
			GameObject object,
			int x,
			int y,
			int width,
			int height){

		this.object = object;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isVisible = true;
		this.isEnabled = true;

	}

	public abstract void drawObject(Canvas canvas, Paint paint, Resources resources);

	public void setScreenInfo(float width, float height, int grid){
		this.screenWidth = width;
		this.screenHeight =height;
		this.grid = grid;
	}

	public float getX0(){
		return this.screenWidth / this.grid * this.x;
	}

	public float getY0(){
		return this.screenHeight / this.grid * this.y;
	}

	public float getWidth(){
		return this.screenWidth / this.grid * this.width;
	}

	public float getHeight(){
		return this.screenHeight / this.grid * this.height;
	}

	public float getX1(){
		return this.getX0() + this.getWidth();
	}

	public float getY1(){
		return this.getY0() + this.getHeight();
	}

	public float getXCenter(){
		return this.getX0() + (this.getWidth()/2);
	}

	public float getYCenter(){
		return this.getY0() + (this.getHeight()/2);
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public float getXGrid(){
		return this.screenWidth / this.grid;
	}

	public float getYGrid(){
		return this.screenHeight / this.grid;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public GameObject getObject() {
		return object;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}

	protected void drawBitmap(Canvas canvas, Paint paint, Resources resources, Bitmap bitmap){
		int baseWidth = bitmap.getWidth();
		int baseHeight = bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(this.getWidth()/baseWidth, this.getHeight()/baseHeight);
		Bitmap drawBitmap = Bitmap.createBitmap(
			bitmap,
			0,
			0,
			baseWidth,
			baseHeight,
			matrix,
			true);
		canvas.drawBitmap(drawBitmap, this.getX0(), this.getY0(), paint);

	}

	public void setFileName(String fileName){
		try {
			this.bitmap = GameFileIO.getInstance().readBitmap(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
