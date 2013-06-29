package jp.neoGuild.kingdomsARmy.model.ifc;

import java.io.IOException;
import java.util.ArrayList;

import jp.neoGuild.kingdomsARmy.model.util.GameObject;

public interface IGameObjectManager {
	public GameObject createObject(ArrayList<String> list);
	public void read(String fileName) throws IOException;
	public GameObject getObjectById(String id);
	public GameObject getObjectByType(String type);
}
