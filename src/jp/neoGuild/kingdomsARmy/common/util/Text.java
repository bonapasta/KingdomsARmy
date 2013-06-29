package jp.neoGuild.kingdomsARmy.common.util;

public class Text {
	public static float getTextSize(float height){
		return height * 0.6f;
	}

	public static  float getTextStartXPoint(float xCenter, float height, String text){
		return xCenter -((float)text.length() / 2.0f * getTextSize(height));
	}
}
