package com.general.mediaplayer.blossom;

import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends UsbSerialActivity {

    @BindView(R.id.main_list)
    ListView listView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        String[] values = new String[]{"Front lawn" ,"Backyard"};
        mainAdapter = new MainAdapter(this ,values);
        mainAdapter.setListener(new MainAdapter.MainAdapterListener() {
            @Override
            public void iconOnClick(int position, boolean check) {

                switch (position)
                {
                    case 0:
                        sendCommand("A");
                        break;
                    case 1:
                        sendCommand("B");
                        break;
                }

            }
        });
        listView.setAdapter(mainAdapter);
    }

}
