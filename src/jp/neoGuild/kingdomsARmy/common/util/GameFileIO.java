package jp.neoGuild.kingdomsARmy.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameFileIO {
	AssetManager assets;

	private static GameFileIO fileIO;

	public static final void createInstance(AssetManager assets){
		fileIO = new GameFileIO(assets);
	}

	public static final GameFileIO getInstance(){
		return fileIO;
	}

	private GameFileIO(AssetManager assets){
		this.assets = assets;
	}

	public InputStream readAsset(String fileName) throws IOException{
		return this.assets.open(fileName);
	}

	public Bitmap readBitmap(String fileName) throws IOException{
		return BitmapFactory.decodeStream(this.readAsset(fileName));
	}

	public InputStream readFile(String fileName) throws IOException{
		return new FileInputStream(fileName);
	}

	public OutputStream writeFile(String fileName) throws IOException{
		return new FileOutputStream(fileName);
	}
}
