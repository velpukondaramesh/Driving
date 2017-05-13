package com.ram.drivingtest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ram.drivingtest.Model.AnswerModel;
import com.ram.drivingtest.Model.QuestionModel;
import com.ram.drivingtest.R;

import java.util.ArrayList;

/**
 * Created by Ramesh on 13-05-2017.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private Context mContext;
    ArrayList<QuestionModel> list_questions = new ArrayList<QuestionModel>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_question;
        ImageView img_image;

        public MyViewHolder(View view) {
            super(view);
            txt_question = (TextView) view.findViewById(R.id.txt_question);
            img_image = (ImageView) view.findViewById(R.id.img_image);
        }
    }

    public QuestionAdapter(Context mContext, ArrayList<QuestionModel> list_questions) {
        this.mContext = mContext;
        this.list_questions = list_questions;
    }

    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_questions, parent, false);
        return new QuestionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final QuestionAdapter.MyViewHolder holder, final int position) {


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
        return list_questions.size();
    }
}