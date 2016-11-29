package com.example.sampleandroid.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.test.mock.MockContext;

public class TestContext extends MockContext
{
    private List<Intent> mReceivedIntents = new ArrayList<Intent>();

    public String getPackageName()
    {
        return "com.example.sampleandroid.test";
    }

    public void startActivity(Intent xiIntent)
    {
        mReceivedIntents.add(xiIntent);
    }

    public List<Intent> getReceivedIntents()
    {
        return mReceivedIntents;
    }
}
