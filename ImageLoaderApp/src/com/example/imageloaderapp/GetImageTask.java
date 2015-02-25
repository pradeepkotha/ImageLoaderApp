package com.example.imageloaderapp;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GetImageTask extends Thread 
{
	public String url;
	public ImageHandler handler;
	public GetImageTask(String url, ImageHandler handler) {
		this.url = url;
		this.handler = handler;
	}
	@Override
	public void run() {
		super.run();
		try {
			URL mUrl = new URL(url);
			Bitmap bitmap = BitmapFactory.decodeStream(mUrl.openConnection().getInputStream());
			handler.onSuccess(bitmap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}
}