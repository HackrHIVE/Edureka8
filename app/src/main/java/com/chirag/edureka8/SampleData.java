package com.chirag.edureka8;

import java.io.Serializable;
import java.util.List;

public class SampleData implements Serializable {


    private List<MovieInfoBean> movieInfo;

    public List<MovieInfoBean> getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(List<MovieInfoBean> movieInfo) {
        this.movieInfo = movieInfo;
    }

    public static class MovieInfoBean {

        private String mainLead;
        private String releaseYear;
        private String imgURL;
        private String movie_name;

        public String getMainLead() {
            return mainLead;
        }

        public void setMainLead(String mainLead) {
            this.mainLead = mainLead;
        }

        public String getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(String releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getImgURL() {
            return imgURL;
        }

        public void setImgURL(String imgURL) {
            this.imgURL = imgURL;
        }

        public String getMovie_name() {
            return movie_name;
        }

        public void setMovie_name(String movie_name) {
            this.movie_name = movie_name;
        }
    }
}
