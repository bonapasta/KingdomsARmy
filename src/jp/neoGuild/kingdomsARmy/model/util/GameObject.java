package jp.neoGuild.kingdomsARmy.model.util;

import java.util.ArrayList;

/*
 * ゲームで使用するObjectの基底クラス
 */
public class GameObject {
	public String id;
	public String name;
	public String type;

	public static final String CLASS_NAME = "GameObject";
	/*
	 * @param id,name,typeのStringがセットされたArrayList
	 */
	public GameObject(ArrayList<String> list){
		this.id = list.remove(0);
		this.name = list.remove(0);
		this.type = list.remove(0);
	}

	public GameObject(String id, String name, String type){
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/*
	 * 引数のGameObjectが同じものかどうか比較する
	 * @param 比較したいGameObject
	 * @return true:同じ false:違う
	 */
	public boolean equals(GameObject object){
		boolean isEqual = true;

		if(this.id != object.id){
			isEqual = false;
		}

		if(this.type.equals(object.type)){
			isEqual = false;
		}

		return isEqual;
	}

	public String getClassName(){
		return this.type;
	}
}
