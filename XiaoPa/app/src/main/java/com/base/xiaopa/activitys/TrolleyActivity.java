package com.base.xiaopa.activitys;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.view.View;

import android.view.Window;
import android.widget.CheckBox;


import android.widget.Toast;


import com.base.xiaopa.Adapter.TrolleyAdapter;
import com.base.xiaopa.base.ShopCar;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Satellite Wu on 2017/9/24.
 */
public class TrolleyActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private static List<ShopCar> mList;
    private TrolleyAdapter trolleyAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        initdatas_Trolley();
        recyclerView = (RecyclerView) findViewById(R.id.shopcar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trolleyAdapter = new TrolleyAdapter(mList, TrolleyActivity.this));


        trolleyAdapter.setOnItemClickListener(new TrolleyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(view.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                trolleyAdapter.setSelectItem(position);
            }

            @Override
            public Boolean onItemLongClickListener(View view, int position) {
              /*  trolleyAdapter.setShowBox();
                trolleyAdapter.setSelectItem(position);
                trolleyAdapter.notifyDataSetChanged();*/

                mList.remove(position);
                trolleyAdapter.notifyItemChanged(position);
                if (position != 0)
                    trolleyAdapter.notifyItemRangeChanged(0, mList.size() - position);
                else
                    trolleyAdapter.notifyItemRangeChanged(0, mList.size());
                return true;
            }
        });
        final CheckBox mqcb = (CheckBox) findViewById(R.id.qcheckbox);

        mqcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, Boolean> map = trolleyAdapter.getMap();

                if (mqcb.isChecked())
                    for (int i = 0; i < map.size(); i++) {
                        map.put(i, true);
                        trolleyAdapter.notifyDataSetChanged();
                    }
                else {
                    for (int i = 0; i < map.size(); i++) {
                        map.put(i, false);
                        trolleyAdapter.notifyDataSetChanged();
                    }


                }
            }
        });

    }

    public void initdatas_Trolley() {
        mList = new ArrayList<ShopCar>();
        String strprice = "￥";
        String xstr = "x";
        {
            ShopCar data = new ShopCar(R.drawable.product2, "三只松鼠大礼包", "8种类型", strprice + "88.8", xstr + "1");

            mList.add(data);
        }

        {
            ShopCar data = new ShopCar(R.drawable.caidai, "彩带", "军绿色", strprice + "3.29", xstr + "1");
            mList.add(data);
        }

        {
            ShopCar data = new ShopCar(R.drawable.uno, "UNO纸牌", "普通UNO+48张惩罚卡牌", strprice + "10", xstr + "1");
            mList.add(data);
        }

    }


    public void onReturn(View view) {
        Intent i = new Intent(TrolleyActivity.this, StoreActivity.class);
        startActivity(i);
        finish();

    }

    public void onBack(View view) {
        Intent i = new Intent(TrolleyActivity.this, StoreActivity.class);
        startActivity(i);
        finish();

    }


    @Override
    public void onRefresh() {

        swipeRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                String strprice = "￥";
                String xstr = "x";
                ShopCar data = new ShopCar(R.drawable.product2, "三只松鼠大礼包", "8种类型", strprice + "88.8", xstr + "1");
                mList.add(data);
                recyclerView.setAdapter(trolleyAdapter = new TrolleyAdapter(mList, TrolleyActivity.this));
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(TrolleyActivity.this, "刷新成功!", Toast.LENGTH_LONG).show();

            }
        }, 1000);


    }

  /*  class TrolleyAdapter extends RecyclerView.Adapter<TrolleyAdapter.MyViewHolder>
    {
        Context context;
        public TrolleyAdapter(Context context)
        {
            this.context=context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder =new MyViewHolder(LayoutInflater.from(TrolleyActivity.this).
                    inflate(R.layout.item_trolley_recyclerview,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.ivSChead.setBackgroundResource(mList.get(position).getSChead());
            holder.tvSCname.setText(mList.get(position).getSCname());
            holder.tvSCinformation.setText(mList.get(position).getSCinformation());
            holder.tvSCprice.setText(mList.get(position).getSCprice());
            holder.tvSCnumber.setText(mList.get(position).getSCnumber());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            ImageView ivSChead;
            TextView  tvSCname;
            TextView  tvSCinformation;
            TextView  tvSCprice;
            TextView  tvSCnumber;
            public MyViewHolder(View view)
            {
                super(view);
                ivSChead= (ImageView) view.findViewById(R.id.shead);
                tvSCname= (TextView) view.findViewById(R.id.sname);
                tvSCinformation= (TextView) view.findViewById(R.id.sinformation);
                tvSCprice= (TextView) view.findViewById(R.id.sprice);
                tvSCnumber= (TextView) view.findViewById(R.id.snumber);


            }
        }
    }*/


}
