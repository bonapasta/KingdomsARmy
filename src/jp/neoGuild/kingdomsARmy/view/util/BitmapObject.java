package jp.neoGuild.kingdomsARmy.view.util;

import java.io.IOException;

import jp.neoGuild.kingdomsARmy.common.util.GameFileIO;
import jp.neoGuild.kingdomsARmy.model.util.GameObject;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BitmapObject extends AbstractDrawObject {


	public BitmapObject(int x, int y, int width, int height, String fileName) {
		this(new GameObject(fileName, fileName, fileName), x, y, width, height,fileName);
	}

	public BitmapObject(GameObject object, int x, int y, int width, int height, String fileName){
		super(object,x,y,width,height);
		this.setFileName(fileName);
	}

	public void setFileName(String fileName){
		try {
			this.bitmap = GameFileIO.getInstance().readBitmap(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void drawObject(Canvas canvas, Paint paint, Resources resources) {
		super.drawBitmap(canvas, paint, resources, this.bitmap);
		paint.reset();
	}

}
