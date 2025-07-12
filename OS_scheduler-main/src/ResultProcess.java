/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * @author renal
 */
public class ResultProcess {
    
    private String job_name;
    private int start_time;
    private int burst_time;
    private Process p;

    public ResultProcess(String job_name, int start_time, int burst_time) {
        this.job_name = job_name;
        this.start_time = start_time;
        this.burst_time = burst_time;
    }

    public ResultProcess(String job_name, int start_time, int burst_time, Process p) {
        this(job_name, start_time, burst_time);
        this.p = p;
    }
    
    public void setJob_number(String job_name) {
        this.job_name = job_name;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public String getJob_number() {
        return job_name;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getBurst_time() {
        return burst_time;
    }

    public Process getP() {
        return p;
    }
    
    @Override
    public String toString() {
        return  "number='" + job_name + '\'' +
                ",start=" + start_time +
                ",burst'" + burst_time + '\'' +
                '}';
    }
}
    

