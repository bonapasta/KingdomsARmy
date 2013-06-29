package jp.neoGuild.kingdomsARmy.view.ifc;

import javax.microedition.khronos.opengles.GL10;

import android.widget.FrameLayout;

import jp.androidgroup.nyartoolkit.utils.gl.AndGLView;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameMode;
import jp.neoGuild.kingdomsARmy.view.util.AbstractDrawObject;
import jp.neoGuild.kingdomsARmy.view.util.AbstractTouchObject;
import jp.neoGuild.kingdomsARmy.view.util.TimerEventObject;
import jp.nyatla.nyartoolkit.core.NyARException;

public interface IGameViewManager extends Runnable,AndGLView.IGLFunctionEvent{
	public void setGameMode(IGameMode mode);
	public void callPerformMode(AbstractTouchObject object);
	public void callEnd();
	public void resume();
	public void doDrawView();
	public void doDraw3D(GL10 gl);
	public void setDrawObject(AbstractDrawObject object);
	public void clearObject();
	public void setTouchObject(AbstractTouchObject object);
	public void createDrawObjectManager();
	public void createGameObjectManager();
	public void addNyIdMarkers() throws NumberFormatException, NyARException;
	public void createLayout(FrameLayout frameLayout);
	public void addTimerEventObject(TimerEventObject object);
}
