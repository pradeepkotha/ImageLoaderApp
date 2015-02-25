package com.example.imageloaderapp;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class ImageHandler extends Handler
{
	private static final int STARTING = 0;
	private static final int SUCCESS = 1;
	private static final int ERROR = 2;
	private CustomImage customImage;
	
	public ImageHandler(CustomImage customImage)
	{
		this.customImage = customImage;
	}
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (msg != null) {
			switch (msg.what) {
			case SUCCESS:
				customImage.setImageBitmap((Bitmap)msg.obj);
				//invalidate();
				break;
				
			case STARTING:
				// on starting
				break;
			case ERROR:
				// Error
				break;
			}
		}
	}
	
	public void onSuccess(Bitmap bitmap)
	{
		Message msg = new Message();
		msg.what = SUCCESS;
		msg.obj = bitmap;
		sendMessage(msg);
		
	}
}
