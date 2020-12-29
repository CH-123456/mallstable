package com.example.mallstable.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

public class GlideModelConfig implements GlideModule {
    int diskSize = 1024*1024*10;//磁盘大小
    int memorySize = (int)(Runtime.getRuntime().maxMemory())/8;//内存大小
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //定义缓存大小和位置
         builder.setDiskCache(new InternalCacheDiskCacheFactory(context,diskSize));//磁盘中
          builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,"cahce",diskSize));//sd卡中

          builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
