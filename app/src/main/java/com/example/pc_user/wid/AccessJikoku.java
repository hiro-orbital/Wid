package com.example.pc_user.wid;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccessJikoku extends AsyncTask<Void, Void, String>{

    String Name;
    String Game_id;
    String Diff;
    String Score;

    private HttpGetListener listener = null;

    AccessJikoku(String n,String id,String d,int s ){
        Name=n;
        Game_id=id;
        Diff=d;
        Score= String.valueOf(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // doInBackground前処理
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // doInBackground後処理
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "http://search.olp.yahooapis.jp/OpenLocalPlatform/V1/localSearch?appid=dj0zaiZpPWZQMFZnNWZjTWV5NCZzPWNvbnN1bWVyc2VjcmV0Jng9ZWE-&query=%E3%83%A9%E3%83%BC%E3%83%A1%E3%83%B3";

        try {
            // URLの作成
            // URLの作成
            url = new URL(urlSt);
            // 接続用HttpURLConnectionオブジェクト作成
            con = (HttpURLConnection)url.openConnection();
            // リクエストメソッドの設定
            con.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            // URL接続からデータを読み取る場合はtrue
            con.setDoInput(true);
            // URL接続にデータを書き込む場合はtrue
            con.setDoOutput(false);

            // 接続
            con.connect(); // ①

            InputStream in = con.getInputStream();
            String readSt = readInputStream(in);
            listener.GetJson(readSt);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void setListener(HttpGetListener listener){
        this.listener = listener;
    }
    public void removeListener(){
        this.listener = null;
    }


}
