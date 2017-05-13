package com.ram.drivingtest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ram.drivingtest.Model.AnswerModel;
import com.ram.drivingtest.R;

import java.util.ArrayList;

/**
 * Created by Ramesh on 13/09/2016.
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.MyViewHolder> {

    private Context mContext;
    ArrayList<AnswerModel> list_potions = new ArrayList<AnswerModel>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_option, txt_answer;

        public MyViewHolder(View view) {
            super(view);
            txt_option = (TextView) view.findViewById(R.id.txt_option);
            txt_answer = (TextView) view.findViewById(R.id.txt_answer);
        }
    }

    public AnswerAdapter(Context mContext, ArrayList<AnswerModel> list_potions) {
        this.mContext = mContext;
        this.list_potions = list_potions;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_answer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        /*byte[] Image = list_itemmaster.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        holder.item_image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 320, 180, false));

        holder.txt_itemName.setText(obj_itemmaster.getItemName());
        String price = Double.toString(obj_itemmaster.getItemPrice());
        holder.txt_itemcost.setText(price);

        holder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Home_Activity) mContext).addOrderItemToCart(list_itemmaster.get(position), 1.0);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list_potions.size();
    }
}
