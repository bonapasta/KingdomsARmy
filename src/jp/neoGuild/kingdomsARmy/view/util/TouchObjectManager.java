package jp.neoGuild.kingdomsARmy.view.util;

import java.util.ArrayList;

public class TouchObjectManager extends DrawObjectManager {
	public static ArrayList<AbstractTouchObject> list;

	public TouchObjectManager(){
		super();
		list = new ArrayList<AbstractTouchObject>();
	}

	public void addObject(AbstractTouchObject object){
		super.addObject(object);
		list.add(object);
	}

	public AbstractTouchObject getObject(String id){
		for(int i=0;i<list.size();i++){
			if(list.get(i).object.id.equals(id)){
				return list.get(i);
			}
		}
		return null;
	}

	public AbstractTouchObject getGameObject(float x, float y){
		for(int i=0;i<list.size();i++){
			AbstractTouchObject object = list.get(i);
			if(object.isEnabled == false){
				continue;
			}
			if(object.isTouch(x, y)){
				return object;
			}
		}
		return null;
	}

	public void clear(){
		list.clear();
		list = new ArrayList<AbstractTouchObject>();
		super.clear();
	}
}
