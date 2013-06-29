package jp.neoGuild.kingdomsARmy.model.util;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import jp.androidgroup.nyartoolkit.markersystem.NyARAndMarkerSystem;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLView;

public abstract class AbstractARGameObject extends GameObject {

	private int arId;	//ARマーカーのハンドルID
	protected  AndGLView andGlView;
	protected boolean refresh = false;

	public AbstractARGameObject(ArrayList<String> list, AndGLView andGlView) {
		super(list);
		this.andGlView = andGlView;
	}

	public int getArId() {
		return arId;
	}

	public void setArId(int arId) {
		this.arId = arId;
	}

	public abstract void drawGL(GL10 gl, NyARAndMarkerSystem markerSystem);

	public boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
}
