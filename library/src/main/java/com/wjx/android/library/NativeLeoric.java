/*
 * Original Copyright 2015 Mars Kwok
 * Modified work Copyright (c) 2020, weishu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wjx.android.library;

/* package */class NativeLeoric {

    static {
        try {
            System.loadLibrary("leoric");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public native void doDaemon(String indicatorSelfPath, String indicatorDaemonPath, String observerSelfPath, String observerDaemonPath);

    public void onDaemonDead() {
        ILeoricProcess.Fetcher.fetchStrategy().onDaemonDead();
    }
}
