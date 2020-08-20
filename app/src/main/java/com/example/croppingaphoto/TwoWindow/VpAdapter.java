package com.example.croppingaphoto.TwoWindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.croppingaphoto.R;
import com.example.croppingaphoto.ThreeWindow.ThreeWindowActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class VpAdapter extends RecyclerView.Adapter<VpAdapter.ViewHolder>{


    private List<ImagesList> vpList;
    private Context context;


    public VpAdapter(List<ImagesList> vpList, Context context) {
        this.vpList = vpList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListenItem = R.layout.container_slide_image;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListenItem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(vpList.get(position));

    }

    @Override
    public int getItemCount() {
        return vpList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView image;

        public String getNameFile() {
            return nameFile;
        }

        private String nameFile;
         ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ri_slider);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent threeWindowIntent = new Intent(context, ThreeWindowActivity.class);
                    threeWindowIntent.putExtra("nameFile",getNameFile());
                    context.startActivity(threeWindowIntent);
                }
            });
        }


        void bind (ImagesList icon){

            nameFile = icon.getNameFile();
            Bitmap bitmap = BitmapFactory.decodeFile(nameFile);
            if(bitmap!=null)
            image.setImageBitmap(bitmap);
        }
    }
}
