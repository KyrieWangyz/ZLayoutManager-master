package com.mcxtzhang.flowlayoutmanager.swipecard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class SwipeCardBeann
{private int postition;
        private String url;
        private String name;

        public SwipeCardBeann(int postition, String url, String name) {
            this.postition = postition;
            this.url = url;
            this.name = name;
        }

        public int getPostition() {
            return postition;
        }

        public SwipeCardBeann setPostition(int postition) {
            this.postition = postition;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public SwipeCardBeann setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getName() {
            return name;
        }

        public SwipeCardBeann setName(String name) {
            this.name = name;
            return this;
        }

        public static List<SwipeCardBeann> initDatas(String []strings) {
            List<SwipeCardBeann> datas = new ArrayList<>();
            int i = 1;



for (int j=0;j<strings.length;j++){
    datas.add(new SwipeCardBeann(i++, "http://p14.go007.com/2014_11_02_05/a03541088cce31b8_1.jpg", strings[j]));
}

            return datas;
        }
        public void delete(int i){

        }
}
