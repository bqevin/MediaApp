package com.projects.kevinbarassa.mediaapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        sectionAdapter.addSection(new NewsSection(NewsSection.WORLD));
        sectionAdapter.addSection(new NewsSection(NewsSection.BUSINESS));
        sectionAdapter.addSection(new NewsSection(NewsSection.TECHNOLOGY));
        sectionAdapter.addSection(new NewsSection(NewsSection.SPORTS));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setTitle(R.string.nav_top_stories);
            }
        }
    }

    class NewsSection extends StatelessSection {

        final static int WORLD = 0;
        final static int BUSINESS = 1;
        final static int TECHNOLOGY = 2;
        final static int SPORTS = 3;

        final int topic;

        String title;
        List<String> list;
        int imgPlaceholderResId;

        public NewsSection(int topic) {
            super(R.layout.top_story_header, R.layout.top_story_footer, R.layout.top_story_item);

            this.topic = topic;

            switch (topic) {
                case WORLD:
                    this.title = getString(R.string.world_topic);
                    this.list = getNews(R.array.news_world);
                    this.imgPlaceholderResId = R.drawable.img1;
                    break;
                case BUSINESS:
                    this.title = getString(R.string.biz_topic);
                    this.list = getNews(R.array.news_biz);
                    this.imgPlaceholderResId = R.drawable.newsname1;
                    break;
                case TECHNOLOGY:
                    this.title = getString(R.string.tech_topic);
                    this.list = getNews(R.array.news_tech);
                    this.imgPlaceholderResId = R.drawable.ic_devices_other_black_48dp;
                    break;
                case SPORTS:
                    this.title = getString(R.string.sports_topic);
                    this.list = getNews(R.array.news_sports);
                    this.imgPlaceholderResId = R.drawable.hot96;
                    break;
            }

        }

        private List<String> getNews(int arrayResource) {
            return new ArrayList<>(Arrays.asList(getResources().getStringArray(arrayResource)));
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String[] item = list.get(position).split("\\|");

            itemHolder.tvHeader.setText(item[0]);
            itemHolder.tvDate.setText(item[1]);
            itemHolder.imgItem.setImageResource(imgPlaceholderResId);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
        }

        @Override
        public RecyclerView.ViewHolder getFooterViewHolder(View view) {
            return new FooterViewHolder(view);
        }

        @Override
        public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

            footerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on footer of Section %s", title), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        public HeaderViewHolder(View view) {
            super(view);

            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;

        public FooterViewHolder(View view) {
            super(view);

            rootView = view;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final ImageView imgItem;
        private final TextView tvHeader;
        private final TextView tvDate;

        public ItemViewHolder(View view) {
            super(view);

            rootView = view;
            imgItem = (ImageView) view.findViewById(R.id.imgItem);
            tvHeader = (TextView) view.findViewById(R.id.tvHeader);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
        }
    }
}
