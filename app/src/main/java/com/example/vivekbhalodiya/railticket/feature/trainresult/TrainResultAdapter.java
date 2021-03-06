package com.example.vivekbhalodiya.railticket.feature.trainresult;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.vivekbhalodiya.railticket.api.model.TrainBetween.TrainsItem;
import com.example.vivekbhalodiya.railticket.api.model.TrainFare.ClassesItem;
import com.example.vivekbhalodiya.railticket.databinding.ListOfTrainsBinding;
import com.example.vivekbhalodiya.railticket.feature.fareavailabilty.AvailabilityAdapter;
import com.example.vivekbhalodiya.railticket.feature.fareavailabilty.ClassessAdapter;
import java.util.ArrayList;

/**
 * Created by vivekbhalodiya on 2/16/18.
 */

public class TrainResultAdapter extends RecyclerView.Adapter<TrainResultAdapter.ViewHolder> {

  private ArrayList<TrainsItem> listOfTrains;
  private ArrayList<ArrayList<ClassesItem>> avaiableClassessList;
  private TrainResultViewModel viewModel;
  private TrainResultActivity trainResultActivity;
  private ClassessAdapter classessAdapter;

  TrainResultAdapter(ArrayList<TrainsItem> listOfTrains, ArrayList<ArrayList<ClassesItem>> avaiableClassessList, TrainResultViewModel viewModel,
      TrainResultActivity trainResultActivity) {
    this.listOfTrains = listOfTrains;
    this.avaiableClassessList = avaiableClassessList;
    this.viewModel = viewModel;
    this.trainResultActivity = trainResultActivity;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ListOfTrainsBinding binding = ListOfTrainsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding);
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    Context context = holder.binding.getRoot().getContext();
    AvailabilityAdapter availabilityAdapter=new AvailabilityAdapter(context,trainResultActivity);

    holder.binding.arrivalTime.setText(listOfTrains.get(position).getDestArrivalTime());
    holder.binding.departureTime.setText(listOfTrains.get(position).getSrcDepartureTime());
    holder.binding.destinationName.setText(listOfTrains.get(position).getToStation().getName());
    holder.binding.duration.setText(listOfTrains.get(position).getTravelTime());
    holder.binding.originName.setText(listOfTrains.get(position).getFromStation().getName());
    holder.binding.tainName.setText(listOfTrains.get(position).getName());
    holder.binding.tainNumber.setText(listOfTrains.get(position).getNumber());

    classessAdapter = new ClassessAdapter(avaiableClassessList.get(holder.getAdapterPosition()),listOfTrains.get(holder.getAdapterPosition()),
        context,availabilityAdapter);

    holder.binding.claassessRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    holder.binding.claassessRecyclerview.setAdapter(classessAdapter);


    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.binding.getRoot().getContext());
    linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
    holder.binding.fareAvailabilityRecyclerview.setLayoutManager(linearLayoutManager);
    holder.binding.fareAvailabilityRecyclerview.setHasFixedSize(true);
    holder.binding.fareAvailabilityRecyclerview.setAdapter(availabilityAdapter);

  }

  @Override public int getItemCount() {
    return listOfTrains.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private ListOfTrainsBinding binding;

    private ViewHolder(ListOfTrainsBinding binding) {

      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
