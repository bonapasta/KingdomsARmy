package jp.neoGuild.kingdomsARmy.view.util;

import jp.neoGuild.kingdomsARmy.model.util.GameObject;

public abstract class AbstractTouchObject extends AbstractDrawObject{
	public static final String TYPE="TOUCH_OBJECT";

	private boolean isActionDown;
	private boolean isActionUp;
	protected String type;

	protected abstract void setType();

	public AbstractTouchObject(
			GameObject object,
			int x,
			int y,
			int width,
			int height) {

		super(object, x, y, width, height);
		this.isActionDown = false;
		this.isActionUp = false;
		this.setType();
	}

	public void setActionDown(float x, float y){
		this.isActionUp = false;
		this.isActionDown = this.setAction(x, y);
	}

	public void setActionUp(float x, float y){
		this.isActionDown = false;
		this.isActionUp = this.setAction(x, y);
	}

	public boolean isTouch(float x, float y){
		if(
				(x >= this.getX0()) &&
				(x <= this.getX0() + this.getWidth()) &&
				(y >= this.getY0())&&
				(y <= this.getY0() + this.getHeight())){
			return true;
		}else{
			return false;
		}
	}

	private boolean setAction(float x, float y){
		if(
				(x >= this.getX0()) &&
				(x <= this.getX0() + this.getWidth()) &&
				(y >= this.getY0())&&
				(y <= this.getY0() + this.getHeight())){
			return true;
		}else{
			return false;
		}
	}

	public boolean isActionDown() {
		return isActionDown;
	}

	public boolean isActionUp() {
		return isActionUp;
	}

	public String getType() {
		return type;
	}


}