package jp.neoGuild.kingdomsARmy.view.util;

import jp.neoGuild.kingdomsARmy.common.util.Text;
import jp.neoGuild.kingdomsARmy.model.util.GameObject;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class MessageObject extends AbstractDrawObject {
	public static final String TYPE = "MESSAGE";
	public boolean square = false;

	private int color;
	public MessageObject(String message, int x, int y, int width, int height,int color) {
		super(new GameObject(TYPE,message,TYPE), x, y, width, height);
		this.color = color;
	}

	@Override
	public void drawObject(Canvas canvas, Paint paint, Resources resources) {
		if(super.bitmap != null){
			super.drawBitmap(canvas, paint, resources, super.bitmap);
		}

		//名前の描画
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(this.color);
		paint.setTextSize(Text.getTextSize(this.getHeight()));
		canvas.drawText(
			super.object.name,
			Text.getTextStartXPoint(this.getXCenter(),this.getHeight(),super.object.name),
			this.getY1()-(this.getHeight()/4.0f),
			paint);

		if(this.square == true){
			int color = this.color;
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(color);
			canvas.drawRect(
					this.getX0(),
					this.getY0(),
					this.getX1(),
					this.getY1(),
					paint);
		}

		paint.reset();
	}

}
