/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.logbook.facilityhero;

import android.app.Dialog;
import android.os.Handler;
import android.view.WindowManager;

public class FacilityHero extends CordovaApp {

    @Override
    protected void showSplashScreen(final int time) {
        final CordovaApp that = this;

        Runnable runnable = new Runnable() {
            public void run() {

                // Create and show the dialog
                splashDialog = new Dialog(that,
                        android.R.style.Theme_Translucent_NoTitleBar);
                // check to see if the splash screen should be full screen
                if ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
                    splashDialog.getWindow().setFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
                splashDialog.setContentView(R.layout.splash_layout);
                splashDialog.setCancelable(false);
                splashDialog.show();

                // Set Runnable to remove splash screen just in case
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        removeSplashScreen();
                    }
                }, time);
            }
        };
        this.runOnUiThread(runnable);
    }

}
