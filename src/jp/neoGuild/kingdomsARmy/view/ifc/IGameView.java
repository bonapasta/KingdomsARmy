package jp.neoGuild.kingdomsARmy.view.ifc;


import jp.neoGuild.kingdomsARmy.view.util.AbstractDrawObject;
import jp.neoGuild.kingdomsARmy.view.util.AbstractTouchObject;
import android.view.SurfaceHolder;

public interface IGameView extends SurfaceHolder.Callback{
	public void doDraw();
	public void setDrawObject(AbstractDrawObject object);
	public void setTouchObject(AbstractTouchObject object);
	public void clearObject();
	public void createDrawObjectManager();
}
