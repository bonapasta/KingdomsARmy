package jp.neoGuild.kingdomsARmy.model.util;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import jp.androidgroup.nyartoolkit.markersystem.NyARAndMarkerSystem;
import jp.androidgroup.nyartoolkit.utils.gl.AndGLView;
import jp.nyatla.nyartoolkit.core.NyARException;

public abstract class AbstractARGameObjectManager extends AbstractGameObjectManager {
	protected AndGLView andGlView;
	public static ArrayList<AbstractARGameObject> arObjectList;

	public AbstractARGameObjectManager(AndGLView andGlView) {
		super();
		this.andGlView = andGlView;
		arObjectList = new ArrayList<AbstractARGameObject>();
	}

	public AbstractARGameObject get(int i){
		return (AbstractARGameObject)super.objectList.get(i);
	}

	public void addNyIdMarker(NyARAndMarkerSystem markerSystem,double size) throws NumberFormatException, NyARException{
		for(int i=0;i<super.size();i++){
			AbstractARGameObject object = (AbstractARGameObject)super.objectList.get(i);
			object.setArId(markerSystem.addNyIdMarker(Long.parseLong(object.id), size));
			arObjectList.add(object);
		}
	}

	public static void doDrawARObject(GL10 gl, NyARAndMarkerSystem markerSystem){
		for(int i=0;i<arObjectList.size();i++){
			if(markerSystem.isExistMarker(arObjectList.get(i).getArId())){
				arObjectList.get(i).drawGL(gl,markerSystem);
			}
		}
	}

}
