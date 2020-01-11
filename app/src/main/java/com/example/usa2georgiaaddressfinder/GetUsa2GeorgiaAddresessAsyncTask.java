package com.example.usa2georgiaaddressfinder;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class GetUsa2GeorgiaAddresessAsyncTask extends AsyncTask<Void, Void, List<Usa2GeorgiaAddress>> {

  private Callback mCallback;

  @Override
  protected void onPreExecute() {
    if (mCallback != null) {
      mCallback.onStart();
    }
  }

  @Override
  protected List<Usa2GeorgiaAddress> doInBackground(Void... voids) {
    List<Usa2GeorgiaAddress> usa2GeorgiaAddresses = new ArrayList<>();
    try {
      Document doc = Jsoup.connect("https://www.usa2georgia.com/").get();
      Elements addressItems = doc.getElementsByClass("address_item");
      for (Element addressItem : addressItems) {
        Usa2GeorgiaAddress usa2GeorgiaAddress = new Usa2GeorgiaAddress();
        usa2GeorgiaAddress.setAddress(addressItem.getElementsByClass("address_title").get(0).text());
        usa2GeorgiaAddress.setWorkingHours(addressItem.getElementsByClass("work_hours").get(0).wholeText());
        usa2GeorgiaAddresses.add(usa2GeorgiaAddress);
      }
    } catch (Exception ex) {
      Log.d("Error", ex.getMessage());
    }
    return usa2GeorgiaAddresses;
  }

  @Override
  protected void onPostExecute(List<Usa2GeorgiaAddress> usa2GeorgiaAddresses) {
    if (mCallback != null) {
      mCallback.onResult(usa2GeorgiaAddresses);
    }
  }

  interface Callback {
    void onStart();

    void onResult(List<Usa2GeorgiaAddress> usa2GeorgiaAddresses);
  }

  public void setCallback(Callback mCallback) {
    this.mCallback = mCallback;
  }
}
