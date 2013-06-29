package jp.neoGuild.kingdomsARmy.view.util;

import jp.androidgroup.nyartoolkit.sketch.AndSketch;
import jp.neoGuild.kingdomsARmy.common.Constants;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameView;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameViewManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements IGameView {
	private IGameViewManager manager;

	private DrawObjectManager drawObjectManager;
	private TouchObjectManager touchObjectManager;

	protected Paint paint;

	public GameView(AndSketch andSketch, IGameViewManager manager){
		super(andSketch);

		this.manager = manager;
		this.paint = new Paint();
		super.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		super.setZOrderOnTop(true);
	}

	public void surfaceChanged(
			SurfaceHolder holder,
			int format,
			int width,
			int height) {
		this.doDraw();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		this.doDraw();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void doDraw() {
		Canvas canvas = super.getHolder().lockCanvas();
		if(canvas == null){
			return;
		}

		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

		if(this.drawObjectManager != null){
			this.drawObjectManager.drawObjects(canvas, paint, super.getResources());
		}
		super.getHolder().unlockCanvasAndPost(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
		AbstractTouchObject object = this.touchObjectManager.getGameObject(event.getX(), event.getY());
		if(object != null){
			this.manager.callPerformMode(object);
		}
		return false;
	}

	public void setDrawObject(AbstractDrawObject object){
		this.drawObjectManager.addObject(object);
	}

	public void clearObject(){
		this.touchObjectManager.clear();
	}

	public void setTouchObject(AbstractTouchObject object){
		this.touchObjectManager.addObject(object);
	}

	public void createDrawObjectManager(){
		if(this.drawObjectManager == null){
			this.drawObjectManager = new DrawObjectManager();
			this.touchObjectManager = new TouchObjectManager();
		};

		this.drawObjectManager.setScreenInfo(super.getWidth()-1, super.getHeight()-1, Constants.GRID);
		this.drawObjectManager.clear();
		this.touchObjectManager.clear();
	}
}
