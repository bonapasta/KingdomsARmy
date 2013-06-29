package jp.neoGuild.kingdomsARmy.controller.ifc;

import jp.neoGuild.kingdomsARmy.model.util.AbstractARGameObject;
import jp.neoGuild.kingdomsARmy.view.util.AbstractTouchObject;

public interface IGameMode {
	public void performMode(AbstractTouchObject object);
	public void performExistArMarkerMode(AbstractARGameObject object);
	public void start();
	public void end();
	public void createView();
}
