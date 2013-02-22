package com.zig.idiom;

import java.util.ArrayList;

import com.zig.idiom.bean.IdiomInfo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class InitDataBase extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//haha~~~
		//just for test: does my githun configuration work well~
		super.onCreate(savedInstanceState);
		String dbPath = "file:///android_asset/data.db";
		SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
		String table = "words";
		String[] columns = new String[]{"dIndex","dContent"};
		String selection = null;
		String[] selectionArgs = new String[]{};
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		
		ArrayList<IdiomInfo> list = new ArrayList<IdiomInfo>();
		IdiomInfo info = null;
		while(cursor.moveToNext()){
			if(!(cursor.getString(cursor.getColumnIndex("dIndex")) .length() >4)){
				info = new IdiomInfo();
				info.word = cursor.getString(cursor.getColumnIndex("dIndex"));
				info.explanation = cursor.getString(cursor.getColumnIndex("dContent"));
				list.add(info);
			}
		}
		
		SQLiteDatabase myDb = this.openOrCreateDatabase("idiom", MODE_PRIVATE, null);
		String str = "create table idiom(_id INTEGER PRIMARY KEY,word TEXT,explanation TEXT)";
		myDb.execSQL(str);
		for(IdiomInfo info1:list){
			String str1 = "insert into idiom(word,explanation) Values('"+info1.word+"','"+info1.explanation+"')";
			myDb.execSQL(str1);
		}
		
		
	}
	
	
}
