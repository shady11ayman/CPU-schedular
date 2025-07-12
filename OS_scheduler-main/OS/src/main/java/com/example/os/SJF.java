/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.os;
import java.util.ArrayList;

/**
 *
 * @author Reda
 */

public class SJF extends Priority_queue{
    
    public SJF(ArrayList<Process> input_list, boolean preem) throws InterruptedException {
        super(input_list,preem);
    }
    
    @Override
    public int compare(Process p1, Process p2) {
        
            if (p1.getArriving_time()== p2.getArriving_time()) 
            {
                 if (p1.getRemaining_time() == p2.getRemaining_time())
                 {
                     return p1.getPosition() - p2.getPosition();
                 }
                 else 
                 {
                     return p1.getRemaining_time() - p2.getRemaining_time();
                 }
                 
            } 
            else 
            {
                return p1.getArriving_time() - p2.getArriving_time();
            }
        }

    @Override
    int compare_queue(Process p1, Process p2) {
        
            if (p1.getRemaining_time()== p2.getRemaining_time()) 
            {
                 if (p1.getArriving_time() == p2.getArriving_time()) // if ml7ash lazma azoon
                 {
                     return p1.getPosition() - p2.getPosition();
                 }
                 else 
                 {
                     return p1.getArriving_time() - p2.getArriving_time();
                 }
                 
            } 
            else 
            {
                return p1.getRemaining_time() - p2.getRemaining_time();
            }
        }
    
}
