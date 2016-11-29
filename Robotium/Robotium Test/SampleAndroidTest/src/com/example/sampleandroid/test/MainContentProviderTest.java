package com.example.sampleandroid.test;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.example.sampleandroid.MyTodoContentProvider;
import com.example.sampleandroid.TodoTable;

/**
 * Created by Oxygen Developments.
 * Author: Dmitriy Tarasov (dm.vl.tarasov@gmail.com)
 * Date: 18.06.11
 * Time: 14:36
 */
public class MainContentProviderTest extends ProviderTestCase2<MyTodoContentProvider> {

    public MainContentProviderTest() {
        super(MyTodoContentProvider.class, MyTodoContentProvider.AUTHORITY);
    }

    public void testInsert() {
    	ContentValues values = new ContentValues();
		values.put(TodoTable.COLUMN_CATEGORY, "category "+1);
		values.put(TodoTable.COLUMN_SUMMARY, "summary "+1);
		values.put(TodoTable.COLUMN_DESCRIPTION, "description "+1);
        Uri inserted = getMockContentResolver().insert(
				MyTodoContentProvider.CONTENT_URI, values);
        assertNotNull(inserted);
        assertEquals(inserted.getLastPathSegment(), "1");        
        
    }

    public void testSelect() {
        testInsert();
        Cursor c = getMockContentResolver().query(MyTodoContentProvider.CONTENT_URI, null, null, null, null);
        Log.i("TAG","Cursor :"+c.getCount());
        assertTrue(c.moveToFirst());
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            assertTrue(c.getInt(c.getColumnIndex(BaseColumns._ID)) > 0);
        }
    }

    public void testDelete() {
        testInsert();
        int deleted = getMockContentResolver().delete(MyTodoContentProvider.CONTENT_URI, null, null);
        assertEquals(deleted, 1);
    }

    public void testDeleteById() {
        testInsert();
        int deleted = getMockContentResolver().delete(ContentUris.withAppendedId(MyTodoContentProvider.CONTENT_URI, 1), null, null);
        assertEquals(deleted, 1);
    }
}
