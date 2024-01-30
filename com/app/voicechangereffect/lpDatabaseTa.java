package com.app.voicechangereffect;

import android.os.AsyncTask;

public class lpDatabaseTa extends AsyncTask<Void, Void, Void> {
    private lpTaskListener lpmDownloadListener;

    public lpDatabaseTa(lpTaskListener lptasklistener) {
        this.lpmDownloadListener = lptasklistener;
    }

    @Override
    public void onPreExecute() {
        lpTaskListener lptasklistener = this.lpmDownloadListener;
        if (lptasklistener != null) {
            lptasklistener.onPreExecuteTask();
        }
    }

    @Override
    public Void doInBackground(Void... voidArr) {
        lpTaskListener lptasklistener = this.lpmDownloadListener;
        if (lptasklistener == null) {
            return null;
        }
        lptasklistener.onDoInBackgroundTask();
        return null;
    }

    @Override
    public void onPostExecute(Void r1) {
        lpTaskListener lptasklistener = this.lpmDownloadListener;
        if (lptasklistener != null) {
            lptasklistener.onPostExecuteTask();
        }
    }
}
