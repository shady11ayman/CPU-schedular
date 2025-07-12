package com.example.os;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Reda
 */

public abstract class Priority_queue implements Comparator<Process>  {

    int size;
    ArrayList<Process> process_list = new ArrayList<Process>();

    public static ArrayList<Process> global_process_total_data = new ArrayList<Process>();

    public Priority_queue(ArrayList<Process> input_list , boolean preemptive) {
        Algo.checker = true;
        Algo.checker2 = true;
        Algo.myList=input_list;
        size = 0;
        for(int i = 0 ;i < Algo.myList.size() ; i++ )
        {
            Algo.myList.get(i).setPosition(i);
        }
        Collections.sort(Algo.myList, this);
        Thread processThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if(preemptive)make_preemptive_processing(Algo.myList);
                else make_nonPreemptive_processing(Algo.myList);
            }
        });
        processThread.start();
    }
    @Override
    abstract public int compare(Process p1, Process p2);

    abstract int compare_queue(Process p1,Process p2);

    public void make_nonPreemptive_processing( ArrayList<Process> input_list) {

        Algo.timer = Algo.myList.get(0).getArriving_time();
        int i=0;
        while(i < Algo.myList.size() || size != 0)
        {
            while(i < Algo.myList.size() && Algo.timer >= Algo.myList.get(i).getArriving_time())
            {
                insert_process(Algo.myList.get(i));
                i++;
            }

            if(size!=0)
            {
                get_top().setWaiting_time(Algo.timer - get_top().getArriving_time());
                while(get_top().getRemaining_time()!=0)
                {
                    if(Algo.adding)
                    {
                        insert_process(Algo.globalProcess);
                        Algo.adding=false;
                        Algo.globalProcess=null;
                    }
                    if(Algo.checker)
                    {
                        get_top().setRemaining_time(get_top().getRemaining_time() - 1);
                        Process temp_process =copy_process(get_top());
                        Algo.global_process_Priority_queue.add(temp_process);
                        Algo.timer++;
                        if(Algo.live) {
                            long startTime = System.currentTimeMillis(); // get the current time in milliseconds
                            while (System.currentTimeMillis() < startTime + 1000) {}
                        }
                        Algo.checker = false;
                    }
                }
                get_top().setTurnaround(Algo.timer - get_top().getArriving_time());

                // ya salaam
                global_process_total_data.add(get_top());
                remove_top_process();
            }
            else
            {
                Algo.timer++;
            }
        }
        Algo.checker2=false;
    }

    public void make_preemptive_processing(ArrayList<Process> input_list) {
        Algo.timer = Algo.myList.get(0).getArriving_time();
        int i = 0;
        Process current_process = null;
        while (i < Algo.myList.size() || size != 0 || current_process != null ) {

            if(Algo.adding)
            {
                insert_process(Algo.globalProcess);
                Algo.adding=false;
                Algo.globalProcess=null;
            }
            if(Algo.checker)
            {
                while (i < Algo.myList.size() && Algo.timer >= Algo.myList.get(i).getArriving_time()){
                    insert_process(Algo.myList.get(i));
                    i++;
                }

                System.out.println("^^^^^^^^^^^^^");
                if (current_process == null || compare_queue(current_process, get_top()) > 0)
                {
                    if (current_process != null)
                    {
                        current_process.setArriving_time(Algo.timer);
                        insert_process(current_process);
                    }
                    current_process = get_top();
                    remove_top_process();
                    current_process.setWaiting_time( current_process.getWaiting_time()+(Algo.timer - current_process.getArriving_time()));
                }


                current_process.setRemaining_time(current_process.getRemaining_time() - 1);

                Process temp_process =copy_process(current_process);
                Algo.global_process_Priority_queue.add(temp_process);
                Algo.timer++;

                // adding global arr;
                if (current_process.getRemaining_time() == 0) {
                    current_process.setTurnaround(Algo.timer - current_process.getFirst_arriving_time());

                    global_process_total_data.add(current_process);
                    current_process = null;

                }
                if(Algo.live) {
                    long startTime = System.currentTimeMillis(); // get the current time in milliseconds
                    while (System.currentTimeMillis() < startTime + 1000) {}
                }
                Algo.checker=false;
            }

        }
        Algo.checker2=false;
    }


    int left_child(int i) {
        return 2 * i + 1;
    }

    int right_child(int i) {
        return 2 * i + 2;
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    Process get_top() {
        return process_list.get(0);
    }

    void insert_process(Process process) {
        // make condition if we want the pq limited

        process_list.add(size, process);  //??
        size += 1;
        int i = size - 1;
        while (i != 0 && compare_queue(process_list.get(parent(i)), process_list.get(i)) > 0) {
            Process temp_process = process_list.get(parent(i));
            process_list.set(parent(i), process_list.get(i));
            process_list.set(i, temp_process);
            i = parent(i);
        }
    }

    void remove_top_process() {
        Process max_process = process_list.get(0);
        process_list.set(0, process_list.get(size - 1));
        size--;
        max_heapify(0);
    }

    void max_heapify(int i) {
        int left = left_child(i);
        int right = right_child(i);
        // find the largest among 3 nodes
        int largest = i;
        if (left <= size && compare_queue(process_list.get(left), process_list.get(largest))< 0) {
            largest = left;
        }
        if (right <= size && compare_queue(process_list.get(right), process_list.get(largest)) < 0) {
            largest = right;
        }
        if (largest != i) {
            Process temp = process_list.get(i);
            process_list.set(i, process_list.get(largest));
            process_list.set(largest, temp);
            max_heapify(largest);
        }
    }

    void printo() {
        for(int i = 0 ;i < size ; i++ )
        {
            System.out.println(process_list.get(i).getProsess_name());
        }
    }

    Process copy_process(Process process)
    {
        Process temp_process =new Process(process.getProsess_name(),process.getArriving_time(),process.getRemaining_time(),process.priority);
        return temp_process;
    }

}