package com.dawidweiss.carrot.controller.carrot2;

import com.dawidweiss.carrot.controller.carrot2.process.ProcessDefinition;
import com.dawidweiss.carrot.controller.carrot2.xmlbinding.query.Query;


/**
 * A request history that can be attached to QueryProcessor.
 */
public final class RequestHistory
{
    private final Query [] queries;
    private final ProcessDefinition [] processes;
    private final int max;
    private int position;
    private int last;  
    

    /**
     * Creates a new request history with some predefined length.
     */
    public RequestHistory(int length)
    {
        position = 0;
        last = 0;
        queries = new Query [ length + 1 ];
        processes = new ProcessDefinition [ length + 1 ];
        max = length + 1;
    }

    public void push(Query query, ProcessDefinition process)
    {
        synchronized (this) {
            queries[position] = query;
            processes[position] = process;
            position = (position + 1) % max;
            if (position == last) {
                last = (last + 1) % max;
            }
        }
    }

    public int getHistory(int max, Query [] query, ProcessDefinition [] process)
    {
        final int localmax = this.max; 
        synchronized (this) {
            int from = last;
            int k = 0;
            
            while (from != position && max > 0) {
                query[k] = this.queries[from];
                process[k] = this.processes[from]; 
                
                max--;
                k++;
                from = (from + 1) % localmax;
            }
            return k;
        }
    }

}
