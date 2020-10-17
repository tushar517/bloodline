package com.Adapters;

import static android.Manifest.permission.CALL_PHONE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;

import com.Adapters.SearchAdapter.ViewHolder;
import com.DataModels.Donor;
import com.bloodbank.R;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<Donor> dataSet;
  private Context context;

  public SearchAdapter(
      List<Donor> dataSet, Context context) {
    this.dataSet = dataSet;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.donor_item, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder,
      final int position) {
    String str = "Name: " + dataSet.get(position).getName();
    str += "\nCity: " + dataSet.get(position).getCity();
    str+="\nArea: "+dataSet.get(position).getArea();
    holder.message.setText(str);
    holder.callButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        // for later
        if (PermissionChecker.checkSelfPermission(context, CALL_PHONE)
            == PermissionChecker.PERMISSION_GRANTED) {
          Intent intent = new Intent(Intent.ACTION_CALL);
          intent.setData(Uri.parse("tel:" + dataSet.get(position).getNumber()));
          context.startActivity(intent);
        } else {
          ((Activity) context).requestPermissions(new String[]{CALL_PHONE}, 401);
        }
      }
    });
    holder.shareButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                holder.message.getText().toString() + "\n\nContact: " + dataSet.get(position)
                        .getNumber());
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hey, could you help here");
        context.startActivity(Intent.createChooser(shareIntent, "Share..."));
      }
    });
  }


  @Override
  public int getItemCount() {
    return dataSet.size();
  }


  class ViewHolder extends RecyclerView.ViewHolder {

    TextView message;
    ImageView imageView,shareButton, callButton;

    ViewHolder(final View itemView) {
      super(itemView);
      message = itemView.findViewById(R.id.message);
      imageView = itemView.findViewById(R.id.image);
      callButton = itemView.findViewById(R.id.call_button);
       shareButton = itemView.findViewById(R.id.share_button);
    }

  }

}
