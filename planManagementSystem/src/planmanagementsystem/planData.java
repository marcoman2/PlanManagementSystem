/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planmanagementsystem;

import java.sql.Date;

/**
 *
 * @author WINDOWS 10
 */
public class planData {
    
    private Integer planID;
    private String plan;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private String status;
    private String planner;
    
    public planData(Integer planID, String plan, Date startDate, Date endDate
            , Date createdDate, String status, String planner){
        this.planID = planID;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.status = status;
        this.planner = planner;
    }    
    
    public Integer getPlanID(){
        return planID;
    }
    public String getPlan(){
        return plan;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public String getStatus(){
        return status;
    }
    public String getPlanner(){
        return planner;
    }
}
