package Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.Fooditem;
import com.example.teamproject.R;

import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.foodViewholder> {
    private List<Fooditem> mFooditems;

    public FoodAdapter(List<Fooditem> mFooditems) {
        this.mFooditems = mFooditems;
    }

    @NonNull
    @Override
    public foodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_food, parent, false);
        return new foodViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull foodViewholder holder, int position) {
        Fooditem fooditem = mFooditems.get(position);
        if (fooditem == null) {
            return;
        }
        holder.imageView.setImageResource(fooditem.getThumbnail());
        holder.namefood.setText(fooditem.getLabelFood());
        holder.contentfood.setText(fooditem.getContentFood());
        holder.pricefood.setText(fooditem.getPriceFood());

    }


    @Override
    public int getItemCount() {
        if (mFooditems != null) {
            return mFooditems.size();
        }
        return 0;

    }


    public class foodViewholder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView namefood, contentfood, pricefood;


        public foodViewholder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_food);
            imageView = itemView.findViewById(R.id.Im_imagefood);
            namefood = itemView.findViewById(R.id.tv_namefood);
            contentfood = itemView.findViewById(R.id.tv_contentfood);
            pricefood = itemView.findViewById(R.id.tv_pricefood);
        }
    }
}

