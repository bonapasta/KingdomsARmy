package jp.neoGuild.kingdomsARmy.controller;

import jp.androidgroup.nyartoolkit.sketch.AndSketch;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameMode;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameModeController;
import jp.neoGuild.kingdomsARmy.model.util.AbstractARGameObject;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameViewManager;

public abstract class AbstractGameMode implements IGameMode {
	protected IGameModeController controller;
	protected IGameViewManager viewManager;
	protected AndSketch andSketch;

	public AbstractGameMode(AndSketch andSketch, IGameModeController controller, IGameViewManager viewManager){
		this.andSketch = andSketch;
		this.controller = controller;
		this.viewManager = viewManager;
	}

	public void start(){
		this.viewManager.setGameMode(this);
		this.createView();
		this.viewManager.doDrawView();
	}

	public void end() {
		this.viewManager.createDrawObjectManager();
		this.controller.changeNextMode();
	}

	public void performExistArMarkerMode(AbstractARGameObject object){
	}
}
