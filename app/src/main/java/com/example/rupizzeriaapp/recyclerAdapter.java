package com.example.rupizzeriaapp;

/**
 * class for the recycler view adapter
 * @author Vinay Kumar, Noel Declaro
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.
        Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<topping> toppingsList;
    private RecyclerViewClickListener listener;

    /**
     * constructor for toppings recycler adapter creation
     * @param toppingsList list of toppings to add
     * @param listener object to apply
     */
    public recyclerAdapter(ArrayList<topping> toppingsList,
                           RecyclerViewClickListener listener){
        this.toppingsList = toppingsList;
        this.listener = listener;
    }

    /**
     * sub class for the view holder of the each item in the recycler view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView toppingText;
        private ImageView toppingImage;

        /**
         * constructor to create view of each item
         * @param view reference
         */
        public MyViewHolder(final View view){
            super(view);
            toppingText = view.findViewById(R.id.topping);
            toppingImage = view.findViewById(R.id.imagetopping);
            view.setOnClickListener(this);
        }

        /**
         * method to handle onclick actions
         * @param view reference
         */
        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    /**
     * method to setup view for the recycler item
     * @param parent reference
     * @param viewType reference
     * @return the created view
     */
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder
    (@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * method to bind view holder
     * @param holder reference for adapter
     * @param position placement of view
     */
    @Override
    public void onBindViewHolder
    (@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String topping = toppingsList.get(position).getTopping();
        Integer image = toppingsList.get(position).getImage();
        holder.toppingText.setText(topping);
        holder.toppingImage.setImageResource(image);
    }

    /**
     * method to get amount of items
     * @return integer of items
     */
    @Override
    public int getItemCount() {
        return toppingsList.size();
    }

    /**
     * method to valid class implementation
     */
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
