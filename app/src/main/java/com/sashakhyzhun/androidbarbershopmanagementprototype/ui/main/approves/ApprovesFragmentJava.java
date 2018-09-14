package com.sashakhyzhun.androidbarbershopmanagementprototype.ui.main.approves;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.sashakhyzhun.androidbarbershopmanagementprototype.R;
import com.sashakhyzhun.androidbarbershopmanagementprototype.data.PaperORM;
import com.sashakhyzhun.androidbarbershopmanagementprototype.model.UserRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import timber.log.Timber;

/**
 * @author SashaKhyzhun
 * Created on 9/14/18.
 */
public class ApprovesFragmentJava extends Fragment {

    private RecyclerTouchListener onTouchListener;
    private ArrayList<UserRequest> requests;
    private ApprovesAdapter adapter;
    private RecyclerView recycler;
    String[] dialogItems;
    List<Integer> unclickableRows, unswipeableRows;
    private int openOptionsPosition;
    private OnActivityTouchListener touchListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("");
        requests = new ArrayList<>();
        unclickableRows = new ArrayList<>();
        unswipeableRows = new ArrayList<>();
        dialogItems = new String[25];
        for (int i = 0; i < 25; i++) {
            dialogItems[i] = String.valueOf(i + 1);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approves, container, false);

        List<UserRequest> all = PaperORM.INSTANCE.getBooksById();
        System.out.println("all size = " + all.size());
        requests.addAll(all);

        adapter = new ApprovesAdapter(getContext(), getData());
        recycler = (RecyclerView) view.findViewById(R.id.recyclerViewRequests);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        onTouchListener = new RecyclerTouchListener(getActivity(), recycler);
        onTouchListener
                .setIndependentViews(R.id.rowButton)
                .setViewsToFade(R.id.rowButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {

                    }
                })
                .setSwipeOptionViews(R.id.add, R.id.edit, R.id.change)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        if (viewID == R.id.add) {
                            message += "Add";
                        } else if (viewID == R.id.edit) {
                            message += "Edit";
                        } else if (viewID == R.id.change) {
                            message += "Change";
                        }
                        message += " clicked for row " + (position + 1);
                    }
                });

        return view;
    }

    private List<UserRequest> getData() {
        List<UserRequest> list = new ArrayList<>(25);
        for (int i = 0; i < 25; i++) {
            list.add(new UserRequest("", Calendar.getInstance(), 0, 0));
        }
        return list;
    }
    @Override
    public void onResume() {
        super.onResume();
        recycler.addOnItemTouchListener(onTouchListener); }

    @Override
    public void onPause() {
        super.onPause();
        recycler.removeOnItemTouchListener(onTouchListener);
    }


    private class ApprovesAdapter extends RecyclerView.Adapter<ApprovesAdapter.MainViewHolder> {
        LayoutInflater inflater;
        List<UserRequest> modelList;

        public ApprovesAdapter(Context context, List<UserRequest> list) {
            inflater = LayoutInflater.from(context);
            modelList = new ArrayList<>(list);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_request, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.bindData(modelList.get(position));
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {

            TextView mainText, subText;

            public MainViewHolder(View itemView) {
                super(itemView);
                mainText = (TextView) itemView.findViewById(R.id.mainText);
                subText = (TextView) itemView.findViewById(R.id.subText);
            }

            public void bindData(UserRequest rowModel) {
                mainText.setText("asd");
                subText.setText("qweqwe");
            }
        }
    }

}
