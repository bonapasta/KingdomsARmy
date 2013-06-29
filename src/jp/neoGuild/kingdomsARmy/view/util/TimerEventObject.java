package jp.neoGuild.kingdomsARmy.view.util;

import java.util.Date;

import jp.neoGuild.kingdomsARmy.model.util.GameObject;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TimerEventObject extends AbstractTouchObject {
	public static final String TYPE = "TIMER";
	public static final int X = 0;
	public static final int Y = 0;
	public static final int WIDTH = 0;
	public static final int HEIGHT = 0;

	private Date startDate;
	private Date currentDate;
	private long delay = 0;

	public TimerEventObject(String name, long delay) {
		super(new GameObject(TYPE,name,TYPE), X, Y, WIDTH, HEIGHT);

		this.startDate = new Date();
		this.currentDate = new Date();
		this.delay = delay;
	}

	@Override
	protected void setType() {
		super.type = TYPE;
	}

	@Override
	public void drawObject(Canvas canvas, Paint paint, Resources resources) {}

	public boolean isCalled(){
		boolean result = false;

		//現在時刻取得
		this.currentDate = new Date();

		//現在時刻が待ち時間を経過した
		if(this.currentDate.getTime() >= this.startDate.getTime() + this.delay){
			result = true;
		}

		return result;
	}

}
