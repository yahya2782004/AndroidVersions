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

public class AndroidVersionAdapter extends RecyclerView.Adapter <AndroidVersionAdapter.AndroidVersionViewHolder> {
    //Context from the activity
    private Context context;
    // Data
    private ArrayList<AndroidVersion> androidVersionList;

    //Constructor: passes context and data list into the adapter
    public AndroidVersionAdapter(Context context, ArrayList<AndroidVersion> androidVersionList) {
        this.context = context;
        this.androidVersionList = androidVersionList;
    }

    @NonNull
    @Override
    public AndroidVersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate item layout using ViewBinding and retuen as AndroidVersionViewHolder
        AndroidVersionsItemBinding itemBinding = AndroidVersionsItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new AndroidVersionViewHolder(itemBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AndroidVersionViewHolder holder, int position) {
        //Bind the data at this position to the UI components
        holder.itemBinding.tvName.setText(androidVersionList.get(position).getCodeName());
        holder.itemBinding.tvNumber.setText(androidVersionList.get(position).getVersion());
        holder.itemBinding.ivLogo.setImageResource(androidVersionList.get(position).getImageResId());

        //Alternate background colors for even/odd rows
        if(holder.getAdapterPosition() % 2 == 0){
            holder.itemBinding.cvItem.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.evenItem));
        }else {
            holder.itemBinding.cvItem.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.oddItem));
        }

        //Click listener for each item in the list
        holder.itemBinding.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show a Toast with the selected Android version details
                String msg = " \"You selected: " + androidVersionList.get(holder.getAdapterPosition()).getCodeName()
                        + " (" + androidVersionList.get(holder.getAdapterPosition()).getVersion() + ")\".";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        //Total number of items in the list
        return  androidVersionList.size();
    }

    //ViewHolder to access the item elements
    public class AndroidVersionViewHolder extends RecyclerView.ViewHolder{
        private final AndroidVersionsItemBinding itemBinding;
        public AndroidVersionViewHolder(AndroidVersionsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
