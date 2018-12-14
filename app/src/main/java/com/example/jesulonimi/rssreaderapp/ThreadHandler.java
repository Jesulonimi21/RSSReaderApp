package com.example.jesulonimi.rssreaderapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ThreadHandler extends AsyncTask<Void,Void,Void> {
   private RecyclerView recyclerView ;
    List<FeedItems> lf;
    String address="http://www.sciencemag.org/rss/news_current.xml";
    Context c;
    ProgressDialog progressDialog;

    public ThreadHandler(Context c,RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        this.c = c;
        progressDialog=new ProgressDialog(c);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        pocessXml(getData());
        return null;
    }

    private void pocessXml(Document data) {

//        Log.d("ROOTS",data.getDocumentElement().getNodeName());
        if(data!=null){
            progressDialog.dismiss();
          lf=new ArrayList<>();

            Node root=data.getDocumentElement();
            Node channel=root.getChildNodes().item(1);
            NodeList items=channel.getChildNodes();
            for(int i=0;i<items.getLength();i++){
                Node currentChild=items.item(i);
                if(currentChild.getNodeName().equals("item")){
                    FeedItems feedItems=new FeedItems();
                    NodeList itemChild=currentChild.getChildNodes();
                    for(int j=0;j<itemChild.getLength();j++){
                        Node current=itemChild.item(j);
                       if(current.getNodeName().equalsIgnoreCase("title")){
                           feedItems.setTitle(current.getTextContent());
                       }else if(current.getNodeName().equalsIgnoreCase("pubDate")){
                           feedItems.setPubDate(current.getTextContent());
                       }else if(current.getNodeName().equalsIgnoreCase("link")){
                           feedItems.setLink(current.getTextContent());
                       } else if(current.getNodeName().equalsIgnoreCase("description")){
                           feedItems.setDescription(current.getTextContent());
                       }else if(current.getNodeName().equalsIgnoreCase("media:thumbnail")) {
                        String url = current.getAttributes().item(0).getTextContent();
                        feedItems.setThumbnailUrl(url);
                    }

                    }

                    lf.add(feedItems);
                    Log.d("itemThumbnailurl",feedItems.getThumbnailUrl());


                }
            }

        }
    }

    @Override
    protected void onPreExecute() {
progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        recyclerView.addItemDecoration(new VerticalSpace(50));
        myAdapter adapter=new myAdapter(c,lf);
        recyclerView.setAdapter(adapter);
        super.onPostExecute(aVoid);


    }

    public Document getData(){
        try {
            URL url=new URL(address);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream  inputStream=connection.getInputStream();
            DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=builderFactory.newDocumentBuilder();
            Document dom=builder.parse(inputStream);
            return dom;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
