package com.example.zikrcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ZikrAdapter extends RecyclerView.Adapter<ZikrAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Zikr> list;
    private final ArrayList<Zikr> listOfSelectedZikr = new ArrayList<>();

    public ZikrAdapter(Context context, ArrayList<Zikr> list) {
        this.context = context;
        this.list = list;
    }

    public void addZikr(Zikr zikr){
        list.add(zikr);
        this.notifyDataSetChanged();
    }

    public ArrayList<Zikr> getListOfSelectedZikr(){

        return listOfSelectedZikr;
    }
    public void refreshData(){
        listOfSelectedZikr.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ZikrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zikr_recycler_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZikrAdapter.ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getZikr());
        holder.limit.getEditText().setText(""+list.get(position).getNoOfTimesZikr());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox text;
        TextInputLayout limit;
        //checked text will be copied to another array list
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.checkbox_zikr);
            limit = itemView.findViewById(R.id.number_of_times_recycler_design);

            text.setOnClickListener(view -> {
                int pos = this.getAdapterPosition();
                if(text.isChecked()){
                    listOfSelectedZikr.add(new Zikr(list.get(pos).getZikr(),list.get(pos).getNoOfTimesZikr()));
                }

            });
        }
    }
}
