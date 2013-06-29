package jp.neoGuild.kingdomsARmy.model;

import java.util.ArrayList;

import jp.neoGuild.kingdomsARmy.model.util.GameObject;

public abstract class AbstractMission extends GameObject{
	public int turn = 0;
	public int maxTurn = 0;
	public String activePlayerId;

	public AbstractMission(ArrayList<String> list){
		super(list);
		this.maxTurn = Integer.parseInt(list.remove(0));
		this.activePlayerId = list.remove(0);
	}

	public abstract boolean isTurnEnd();
	public abstract boolean isGameEnd();

	public void addTurn(){
		this.turn++;
	}
}
