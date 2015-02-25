package com.example.imageloaderapp;

import java.io.IOException;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CustomImage extends ImageView {

	private LruCache<String, Bitmap> mMemoryCache;
	
	
	public CustomImage(Context context) {
		super(context);
		init(context);
	}

	public CustomImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public void init(Context context) {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;

		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount() / 1024;
			}
		};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void loadBitmap(String url) {
		final String imageKey = String.valueOf(getId());

		final Bitmap bitmap = getBitmapFromMemCache(imageKey);
		if (bitmap != null) {
			this.setImageBitmap(bitmap);
		} else {
			displayBitmap(url);
		}
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemCache(String key) {
		return mMemoryCache.get(key);
	}

	public void displayBitmap(final String url)
	{
		ImageHandler handler = new ImageHandler(this);
		GetImageTask imageTask = new GetImageTask(url,handler);
		imageTask.start();
	}
	
}
