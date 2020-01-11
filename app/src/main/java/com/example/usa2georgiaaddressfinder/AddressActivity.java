package com.example.usa2georgiaaddressfinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

  private ListView mAddressListView;
  private Usa2GeorgiaAddressAdapter mUsa2GeorgiaAddressAdapter;
  private GetUsa2GeorgiaAddresessAsyncTask mGetUsa2GeorgiaAddresessAsyncTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address);
    mAddressListView = findViewById(R.id.addressList);
    mUsa2GeorgiaAddressAdapter = new Usa2GeorgiaAddressAdapter(this, 0, new ArrayList<Usa2GeorgiaAddress>());
    mAddressListView.setAdapter(mUsa2GeorgiaAddressAdapter);
    mGetUsa2GeorgiaAddresessAsyncTask = new GetUsa2GeorgiaAddresessAsyncTask();
    mGetUsa2GeorgiaAddresessAsyncTask.setCallback(new GetUsa2GeorgiaAddresessAsyncTask.Callback() {
      @Override
      public void onStart() {
        findViewById(R.id.loader).setVisibility(View.VISIBLE);
      }

      @Override
      public void onResult(List<Usa2GeorgiaAddress> usa2GeorgiaAddresses) {
        findViewById(R.id.loader).setVisibility(View.GONE);
        mUsa2GeorgiaAddressAdapter.clear();
        mUsa2GeorgiaAddressAdapter.addAll(usa2GeorgiaAddresses);
      }
    });
    mGetUsa2GeorgiaAddresessAsyncTask.execute();
  }

  public class Usa2GeorgiaAddressAdapter extends ArrayAdapter<Usa2GeorgiaAddress> {

    private Context mContext;

    public Usa2GeorgiaAddressAdapter(@NonNull Context context, int resource, @NonNull List<Usa2GeorgiaAddress> objects) {
      super(context, resource, objects);
      mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = layoutInflater.inflate(R.layout.view_address, parent, false);
      ((TextView) (view.findViewById(R.id.address))).setText(getItem(position).getAddress());
      ((TextView) (view.findViewById(R.id.workingHours))).setText(getItem(position).getWorkingHours());
      return view;
    }
  }
}
