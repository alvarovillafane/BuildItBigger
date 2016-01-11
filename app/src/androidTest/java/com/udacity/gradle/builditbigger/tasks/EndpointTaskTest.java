package com.udacity.gradle.builditbigger.tasks;


import android.content.Context;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * Created by alvaro on 9/01/2016.
 */
public class EndpointTaskTest extends AndroidTestCase implements EndpointsAsyncTask.TaskCallback {

    CountDownLatch signal;
    EndpointsAsyncTask endpointsAsyncTask;
    Context context;
    private final String TAG = EndpointTaskTest.class.getSimpleName();

    public EndpointTaskTest() {
        super();
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
        endpointsAsyncTask = new EndpointsAsyncTask(this);

    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    @UiThreadTest
    public void testAsyncTask() throws Throwable {
        endpointsAsyncTask.execute();
        signal.await(30, TimeUnit.SECONDS);
    }

    @Override
    public void onTaskCompleted(String joke) {
        if( joke != null && "".equals(joke) ){
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
        signal.countDown();
    }
}
