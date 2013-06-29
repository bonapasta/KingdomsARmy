package jp.neoGuild.kingdomsARmy.view.util;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import jp.androidgroup.nyartoolkit.markersystem.NyARAndMarkerSystem;
import jp.androidgroup.nyartoolkit.markersystem.NyARAndSensor;
import jp.androidgroup.nyartoolkit.sketch.AndSketch;
import jp.androidgroup.nyartoolkit.utils.camera.CameraPreview;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLFpsLabel;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLView;
import jp.neoGuild.kingdomsARmy.common.util.GameFileIO;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameMode;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameModeController;
import jp.neoGuild.kingdomsARmy.model.util.AbstractARGameObjectManager;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameView;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameViewManager;
import jp.neoGuild.kingdomsARmy.view.util.andGL.TextureManager;
import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.markersystem.NyARMarkerSystemConfig;
import android.hardware.Camera;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public abstract class AbstractGameViewManager extends AndSketch implements IGameViewManager {

	//ゲーム定義
	protected IGameView view;
	protected IGameMode mode;
	protected IGameModeController controller;

	//スレッド定義
	protected Thread thread;
	protected volatile boolean running = false;

	//レイアウト定義
	protected FrameLayout frameLayout;
	protected CameraPreview cameraPreview;
	protected Camera.Size cameraSize;
	protected AndGLView andGlView;

	//AR定義
	protected NyARAndSensor sensor;
	protected NyARAndMarkerSystem markerSystem;
	protected AndGLFpsLabel fps;

	//タイマー
	protected ArrayList<TimerEventObject> timerEventList;

	@Override
	public void onStart() {
		super.onStart();
		this.timerEventList = new ArrayList<TimerEventObject>();
		this.andGlView = new AndGLView(this);

		GameFileIO.createInstance(this.getAssets());

		this.createGameObjectManager();
	}

	@Override
	protected void onStop(){
		super.onStop();
		try {
			if(this.cameraPreview != null){
				this.cameraPreview.onAcStop();
				this.cameraPreview = null;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/*
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.running = false;
		while(true){
			try{
				this.thread.join();
				break;
			}catch (InterruptedException ex){
				//リトライ
			}
		}
	}

	public void createLayout(FrameLayout frameLayout){
		this.frameLayout =  frameLayout;

		this.cameraPreview = new CameraPreview(this);
		this.cameraPreview.setFocusable(true);

		this.cameraSize = this.cameraPreview.getRecommendPreviewSize(300,200);
		this.frameLayout.addView(this.cameraPreview, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		this.frameLayout.addView(this.andGlView, 0,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		this.view = new GameView(this, this);
		this.frameLayout.addView((View)this.view);

	}

	public void setupGL(GL10 gl) {
		try {

			//TextureManagerの設定
			TextureManager.createInstance(gl);

			//ARマーカーシステムの初期設定
			this.sensor = new NyARAndSensor(this.cameraPreview,this.cameraSize.width,this.cameraSize.height,30);
			this.markerSystem=new NyARAndMarkerSystem(new NyARMarkerSystemConfig(this.cameraSize.width,this.cameraSize.height));

			//ARマーカーの初期設定
			this.addNyIdMarkers();
			this.sensor.start();

			//GL10の初期設定
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadMatrixf(this.markerSystem.getGlProjectionMatrix(),0);

			//ARオブジェクト初期設定
			this.fps=new AndGLFpsLabel(this.andGlView,"MarkerPlaneActivity");
			this.fps.prefix=this.cameraSize.width+"x"+this.cameraSize.height+":";

			this.createDrawObjectManager();
			this.controller.startCurrentMode();
		} catch (NyARException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void drawGL(GL10 gl) {
		//背景塗り潰し色の指定
		gl.glClearColor(0,0,0,0);
		//背景塗り潰し
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);

		//ARオブジェクト表示
		this.fps.draw(0, 0);

		//カード情報表示
		this.doDraw3D(gl);

		//ユーザインターフェース表示
		this.doDrawView();

	}

	public void doDraw3D(GL10 gl){
		synchronized(this.sensor){
			try {
				this.markerSystem.update(this.sensor);
				//ARオブジェクト表示
				AbstractARGameObjectManager.doDrawARObject(gl, this.markerSystem);
				//ARオブジェクト認識チェック
				for(int i=0;i<AbstractARGameObjectManager.arObjectList.size();i++){
					if(markerSystem.isExistMarker(AbstractARGameObjectManager.arObjectList.get(i).getArId())){
						this.mode.performExistArMarkerMode(AbstractARGameObjectManager.arObjectList.get(i));
					}
				}
				//タイマーオブジェクト経過チェック
				for(int i=0;i<this.timerEventList.size();i++){
					if(this.timerEventList.get(i).isCalled()==true){
						this.mode.performMode(this.timerEventList.get(i));
						this.timerEventList.remove(i);
					}
				}
			} catch (NyARException e) {
				e.printStackTrace();
			}

		}
	}

	public void setGameMode(IGameMode mode) {
		this.mode = mode;
	}

	public void callPerformMode(AbstractTouchObject object) {
		this.mode.performMode(object);
	}

	public void callEnd() {
		this.mode.end();
	}

	public void resume() {
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void doDrawView() {
		if(this.view != null){
			this.view.doDraw();
		}
	}

	public void setDrawObject(AbstractDrawObject object) {
		this.view.setDrawObject(object);
	}

	public void clearObject() {
		this.view.clearObject();
	}

	public void setTouchObject(AbstractTouchObject object) {
		this.view.setTouchObject(object);
	}

	public void createDrawObjectManager() {
		this.view.createDrawObjectManager();

	}

	public void addTimerEventObject(TimerEventObject object){
		this.timerEventList.add(object);
	}
}
