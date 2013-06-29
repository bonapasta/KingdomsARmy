package jp.neoGuild.kingdomsARmy.view.util;

import java.io.IOException;

import jp.neoGuild.kingdomsARmy.common.util.GameFileIO;
import jp.neoGuild.kingdomsARmy.common.util.Text;
import jp.neoGuild.kingdomsARmy.model.util.GameObject;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public abstract class AbstractButtonObject extends AbstractTouchObject {
	public static final String TYPE = "BUTTON";
	public static final int WIDTH = 6;
	public static final int HEIGHT = 2;

	private Bitmap bitmap;

	public abstract int getColor(Resources resources);
	public abstract int getTextColor(Resources resources);
	public abstract String getFileName();

	public AbstractButtonObject(String id, String name, int x, int y) {
		this(id,name, x, y, WIDTH, HEIGHT);
	}

	public AbstractButtonObject(String id, String name, int x, int y, int width, int height) {
		this(new GameObject(id,name,TYPE), x, y, width, height);
	}

	public AbstractButtonObject(GameObject object, int x, int y, int width, int height) {
		super(object, x, y, width, height);
		try {
			this.bitmap = GameFileIO.getInstance().readBitmap(this.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setType() {
		super.type = TYPE;
	}

	@Override
	public void drawObject(Canvas canvas, Paint paint, Resources resources) {
		//背景の描画
		this.drawBitmap(canvas, paint, resources, this.bitmap);

		//枠の描画
		int color = this.getColor(resources);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(color);
		canvas.drawRect(
				this.getX0(),
				this.getY0(),
				this.getX1(),
				this.getY1(),
				paint);

		//名前の描画
		color = this.getTextColor(resources);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);
		paint.setTextSize(Text.getTextSize(this.getHeight()));
		canvas.drawText(
			super.object.name,
			Text.getTextStartXPoint(this.getXCenter(),this.getHeight(),super.object.name),
			this.getY1()-(this.getHeight()/4.0f),
			paint);
		paint.reset();
	}
}
