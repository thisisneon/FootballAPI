package com.example.footballapi.PL;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.footballapi.R;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Data> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Data> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.list_teams, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_contact);


        vHolder.itemTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvNameDetails = (TextView) myDialog.findViewById(R.id.tvNameDetails);
                TextView tvShortNameDetails = (TextView) myDialog.findViewById(R.id.tvShortNameDetails);
                tvNameDetails.setText(mData.get(vHolder.getAdapterPosition()).getName());



//                Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAbsoluteAdapterPosition()), Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });

        return vHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvName2.setText(mData.get(position).getName());

        // Load ảnh bằng Glide
                Glide.with(mContext)
                .load(mData.get(position).getCrestUrl())
                .into(holder.civTeams2);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout itemTeams;
        private TextView tvName2;
        private CircleImageView civTeams2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTeams = (RelativeLayout) itemView.findViewById(R.id.rlitemTeams);
            tvName2 = (TextView) itemView.findViewById(R.id.tvTeams);
            civTeams2 = (CircleImageView) itemView.findViewById(R.id.civTeams);

        }
    }
}
