package jp.neoGuild.kingdomsARmy.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jp.neoGuild.kingdomsARmy.common.util.GameFileIO;
import jp.neoGuild.kingdomsARmy.model.ifc.IGameObjectManager;
import android.util.Log;

public abstract class AbstractGameObjectManager implements IGameObjectManager {
	protected ArrayList<GameObject> objectList;
	protected GameFileIO fileIO;
	/*
	 * コンストラクタ
	 */
	public AbstractGameObjectManager(){
		this.objectList = new ArrayList<GameObject>();
		this.fileIO = GameFileIO.getInstance();
	}

	/*
	 * @see org.KingdomsARmy.model.ifc.IGameObjectManager#create()
	 */
	public void read(String fileName) throws IOException{
		InputStreamReader inputStreamReader = new InputStreamReader(this.fileIO.readAsset(fileName));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = bufferedReader.readLine();
		while((str = bufferedReader.readLine())!= null){
			Log.d("AbstractGameObjectManager.read",str);
			String[] data = str.split("\t");
			ArrayList<String> list = new ArrayList<String>();
			for(int i=0;i<data.length;i++){
				list.add(data[i]);
			}
			objectList.add(this.createObject(list));
		}

		bufferedReader.close();
	}

	/*
	 * @see org.KingdomsARmy.model.ifc.IGameObjectManager#getObjectById(int)
	 */
	public GameObject getObjectById(String id) {

		Log.d("AbstractGameObjectManager.getObjectById","id:"+id);

		for(int i=0;i<objectList.size();i++){
			Log.d("AbstractGameObjectManager.getObjectById",objectList.get(i).id+","+objectList.get(i).name);
			if(this.objectList.get(i).id.equals(id)){
				Log.d("AbstractGameObjectManager.getObjectById","hit!");
				return this.objectList.get(i);
			}
		}
		return null;
	}

	public GameObject getObjectByType(String type) {
		for(int i=0;i<objectList.size();i++){
			if(this.objectList.get(i).type.equals(type)){
				return this.objectList.get(i);
			}
		}
		return null;
	}

	public void printListForLog(){
		for(int i=0;i<this.objectList.size();i++){
			GameObject object = this.objectList.get(i);
			Log.d("AbstractGameObjectManager.printListForLog",object.id+":"+object.name+":"+object.type);
		}
	}

	public int size(){
		return this.objectList.size();
	}

	public GameObject get(int i){
		return this.objectList.get(i);
	}

}
