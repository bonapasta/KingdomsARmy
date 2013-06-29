package jp.neoGuild.kingdomsARmy.controller;

import java.util.ArrayList;

import jp.androidgroup.nyartoolkit.sketch.AndSketch;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameMode;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameModeController;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameViewManager;

public abstract class AbstractGameController implements IGameModeController {
	protected AndSketch andSketch;
	protected IGameViewManager viewManager;
	protected ArrayList<IGameMode> modeList;
	protected int currentMode = 0;

	public AbstractGameController(AndSketch andSketch, IGameViewManager viewManager){
		this.andSketch = andSketch;
		this.viewManager = viewManager;
		this.modeList = new ArrayList<IGameMode>();
		this.createModes();
	}

	public void startCurrentMode() {
		this.modeList.get(this.currentMode).start();
	}

	public void changeNextMode() {
		this.currentMode ++;
		if(this.modeList.size() <= this.currentMode){
			this.currentMode = 0;
		}
		this.startCurrentMode();
	}

}
