
package com.stuffit.www.data.objects;

/**
 * @author Bikramjit
 */
public class Rate {
    private int id;
    private String comment;
    private int rate;
    
    public int getRate(){
        return rate;
    }
    
    public int getId(){
        return id;
    }
    
    public String getComment(){
        return comment;
    }
    
    public void setRate(int rate){
        this.rate = rate;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }

}
