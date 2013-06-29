package jp.neoGuild.kingdomsARmy.controller;

import java.util.ArrayList;

import android.util.Log;

import jp.androidgroup.nyartoolkit.sketch.AndSketch;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameMode;
import jp.neoGuild.kingdomsARmy.controller.ifc.IGameModeController;
import jp.neoGuild.kingdomsARmy.view.ifc.IGameViewManager;

public abstract class AbstractGameModeFacade extends AbstractGameMode implements
		IGameModeController {

	protected ArrayList<IGameMode> modeList;
	protected int currentMode = 0;

	protected abstract boolean isModeFacadeEnd();

	public AbstractGameModeFacade(AndSketch andSketch,
			IGameModeController controller, IGameViewManager viewManager) {
		super(andSketch, controller, viewManager);

		this.modeList = new ArrayList<IGameMode>();
		this.createModes();
	}

	@Override
	public void start(){
		this.startCurrentMode();
	}

	public void startCurrentMode() {
		Log.d("AbstractGameModeFactory.startCurentMode","CURRENT_MODE:"+this.currentMode);
		this.modeList.get(this.currentMode).start();
	}

	public void changeNextMode() {
		this.currentMode ++;
		if(this.modeList.size() <= this.currentMode){
			this.currentMode = 0;
			if(this.isModeFacadeEnd() == true){
				this.end();
				return;
			}
		}
		this.startCurrentMode();
	}

}
