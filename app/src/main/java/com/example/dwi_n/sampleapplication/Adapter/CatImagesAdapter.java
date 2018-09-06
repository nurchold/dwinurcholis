package com.example.dwi_n.sampleapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dwi_n.sampleapplication.Interface.CommonInterface;
import com.example.dwi_n.sampleapplication.Model.CatDetails;
import com.example.dwi_n.sampleapplication.R;
import com.example.dwi_n.sampleapplication.fragment.HomeScreenFragment;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * This class handle ui repeat render & controller.
 */
public class CatImagesAdapter extends RecyclerView.Adapter<CatImagesAdapter.CatImageHolder> {

    private Context context;
    private List<CatDetails> mCatDetailList;
    private CommonInterface.RecyclerListener mRecyclerListener;

    /**
     * constructor this adapter.
     * @param context - context.
     * @param catDetailList - cat image list param.
     */
    public CatImagesAdapter(Context context, List<CatDetails> catDetailList) {
        this.context = context;
        this.mCatDetailList = catDetailList;
    }

    @NonNull
    @Override
    public CatImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_image, viewGroup, false);
        return new CatImageHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CatImageHolder catImageHolder, int i) {
        //Prepare data before rendering.
        final CatDetails catDetails = mCatDetailList.get(i);
        //User image in list as default image tile.
        Picasso.with(context).load(catDetails.getImageList().get(0)).fit().into(catImageHolder.imageView);
        catImageHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerListener.onImageSelected(catDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCatDetailList.size();
    }

    public void setCatImageListListener(CommonInterface.RecyclerListener mRecyclerListener) {
        this.mRecyclerListener = mRecyclerListener;
    }

    /**
     * Holder item this adapter.
     */
    public class CatImageHolder extends RecyclerView.ViewHolder implements Serializable {
        private ImageView imageView;

        CatImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_item);
        }
    }
}
