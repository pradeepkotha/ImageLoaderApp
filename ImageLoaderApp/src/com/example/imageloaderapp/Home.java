package com.example.imageloaderapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class Home extends ActionBarActivity {

	public CustomImage image;
	public CustomImage imageView;
	public Context mContext;
	private String url = "http://d2k48qhp8mvs5w.cloudfront.net/server/stream/image/b4f0e4b1-b48a-4fff-86e8-f276f503f6fd/b4f0e4b1-b48a-4fff-86e8-f276f503f6fd.thumbnail";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_home);
		
		image = (CustomImage) findViewById(R.id.image);
		image.loadBitmap(url);

	}

}
