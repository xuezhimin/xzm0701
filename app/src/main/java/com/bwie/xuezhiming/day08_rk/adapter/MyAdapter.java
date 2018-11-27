package com.bwie.xuezhiming.day08_rk.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.xuezhiming.day08_rk.R;
import com.bwie.xuezhiming.day08_rk.bean.Bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Bean.DataBean> list;

    public MyAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.ic_launcher).build();

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_list, null);
            viewHolder.title = convertView.findViewById(R.id.txt_title);
            viewHolder.img = convertView.findViewById(R.id.image_view);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //赋值
        viewHolder.title.setText(list.get(position).getTitle());

        if (!TextUtils.isEmpty(list.get(position).getThumbnail_pic_s())) {
             //ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), viewHolder.img, options);
        } else {
            viewHolder.img.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView img;
    }

}
