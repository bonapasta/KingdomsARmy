package jp.neoGuild.kingdomsARmy.common.util;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
	public static int roll(int dimension){
		Random random = new Random();
		return random.nextInt(dimension) + 1;
	}

	public static int roll(int dimension, int point, int skill){
		int successPoint = 0;

		for(int i=0;i<point;i++){
			if(roll(dimension)<=skill){
				successPoint++;
			}
		}

		return successPoint;
	}

	public static ArrayList<Integer> getSuccessRate(int dimension, int point, int skill){
		ArrayList<Integer> successRateList = new ArrayList<Integer>();

		for(int i=0;i<point;i++){
			int roll = roll(dimension);
			if(roll<=skill){
				successRateList.add(roll);
			}
		}

		return successRateList;
	}
}

