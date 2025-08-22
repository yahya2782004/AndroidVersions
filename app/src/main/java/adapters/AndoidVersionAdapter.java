package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidversions.R;
import com.example.androidversions.databinding.AndroidVersionsItemBinding;

import java.util.ArrayList;

import models.AndroidVersion;

public class AndoidVersionAdapter extends RecyclerView.Adapter <AndoidVersionAdapter.AndoidVersionViewHolder> {
    private Context context;
    private ArrayList<AndroidVersion> androidVersionList;
    public AndoidVersionAdapter(Context context, ArrayList<AndroidVersion> androidVersionList) {
        this.context = context;
        this.androidVersionList = androidVersionList;
    }

    @NonNull
    @Override
    public AndoidVersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AndroidVersionsItemBinding itemBinding = AndroidVersionsItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AndoidVersionViewHolder(itemBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AndoidVersionViewHolder holder, int position) {
        holder.itemBinding.tvName.setText(androidVersionList.get(position).getCodeName());
        holder.itemBinding.tvNumber.setText(androidVersionList.get(position).getVersion());
        holder.itemBinding.ivLogo.setImageResource(androidVersionList.get(position).getImageResId());

        if(holder.getAdapterPosition() % 2 == 0){
            holder.itemBinding.cvItem.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.evenItem));
        }else {
            holder.itemBinding.cvItem.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.oddItem));
        }

        holder.itemBinding.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = " \"You selected: " + androidVersionList.get(holder.getAdapterPosition()).getCodeName()
                        + " (" + androidVersionList.get(holder.getAdapterPosition()).getVersion() + ")\".";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return  androidVersionList.size();
    }

    public class AndoidVersionViewHolder extends RecyclerView.ViewHolder{
        private final AndroidVersionsItemBinding itemBinding;

        public AndoidVersionViewHolder(AndroidVersionsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
