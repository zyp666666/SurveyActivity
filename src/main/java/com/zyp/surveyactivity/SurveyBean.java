package com.zyp.surveyactivity;

/**
 * Created by zyp on 2018/6/20.
 */

public class SurveyBean {

    private String   title ;
    private String[] sel ;

    public SurveyBean() {
    }

    public SurveyBean(String title, String[] sel) {
        this.title=title;
        this.sel=sel;

    }

    public  String[] getSel() {
        return sel;
    }

    public  void setSel(String[] sel) {
        this.sel = sel;
    }

    public String getTitle() {
        return title;
    }

    public  void setTitle(String title) {
        this.title = title;
    }




}
