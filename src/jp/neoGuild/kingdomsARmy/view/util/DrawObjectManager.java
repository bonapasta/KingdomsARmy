package jp.neoGuild.kingdomsARmy.view.util;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawObjectManager {
	public static ArrayList<AbstractDrawObject> list;
	public static float screenWidth;
	public static float screenHeight;
	public static int screenGrid;

	public DrawObjectManager(){
		list = new ArrayList<AbstractDrawObject>();
	}

	public void setScreenInfo(float width, float height, int grid){
		screenWidth = width;
		screenHeight =height;
		screenGrid = grid;
	}

	public void drawObjects(Canvas canvas, Paint paint, Resources resources){
		for(int i=0;i<list.size();i++){
			paint.reset();
			if(list.get(i).isVisible == true){
				list.get(i).drawObject(canvas, paint, resources);
			}
		}
	}

	public void addObject(AbstractDrawObject object){
		object.setScreenInfo(screenWidth, screenHeight, screenGrid);
		list.add(object);
	}

	public AbstractDrawObject getObject(String id){
		for(int i=0;i<list.size();i++){
			if(list.get(i).object.id.equals(id)){
				return list.get(i);
			}
		}
		return null;
	}

	public void removeObject(String id){
		for(int i=0;i<list.size();i++){
			if(list.get(i).object.id.equals(id)){
				list.remove(i);
			}
		}
	}

	public void clear(){
		list.clear();
		//list = new ArrayList<AbstractDrawObject>();
	}
}
