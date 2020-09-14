package com.example.teste_android.Model;


import android.os.AsyncTask;

import com.example.teste_android.View.InvestmentsActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JsonClass extends AsyncTask<Void, Void, InvestmentsActivity> {

    private final String name;
    private final String data;

    public JsonClass(String name, String data) {
        this.name = name;
        this.data=data;
    }

    @Override
    protected InvestmentsActivity doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();{
            try {

                URL url = new URL("https://floating-mountain-50292.herokuapp.com/fund.json/" + this.name+data + "/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (
    MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Gson().fromJson(resposta.toString(), InvestmentsActivity.class);
    }
}

