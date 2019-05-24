package com.hsc.restproducer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

@RestController
public class GreetingControllerImpl implements GreetingController {

    private Queue<RequestSave> requestQueue = new LinkedList<>();
    private Stack<RequestSave> requestStack = new Stack<>();
    private boolean isCrowded = false;
    private int count = 0;

    @Override
    public void greeting(@PathVariable("username") String username, HttpServletResponse response) throws InterruptedException, IOException {

        /*
        System.out.println("Get a request!");
        System.out.println("Current solution is: " + (isCrowded ? "busy" : "not busy"));
        System.out.println("Current wait length is: " + (isCrowded ? requestStack.size() : requestQueue.size()));
        */

        if(requestQueue.size() == 3){
            isCrowded = true;
            requestStack = new Stack<>(); //initialize the stack for busy solution
            for(RequestSave save : requestQueue){
                requestStack.push(save);
            }
        }
        else if(requestStack.size() < 3){
            isCrowded = false;
            requestQueue = new LinkedList<>();  //initialize the queue for easy solution
            for(RequestSave save: requestStack) {
                requestQueue.offer(save);
            }
        }

        RequestSave newSave = new RequestSave(response, Instant.now());
        // save the request and its time
        if(isCrowded){
            requestStack.push(newSave);
        }
        else{
            requestQueue.offer(newSave);
        }

        // sleep 1s to simulate producing resources
        Thread.sleep(1000);

        // make response
        RequestSave finishedSave;
        if(isCrowded){
            finishedSave = requestStack.pop();
            System.out.println(Duration.between(finishedSave.getTime(), Instant.now()).toMillis());
            /*
            while(((new Date()).getTime() - finishedSave.getTime().getTime()) / 1000 % 60 > 3 ) {
                finishedSave = requestStack.pop();
                if(requestStack.size() == 0)
                    return;
            }
            */
        }
        else {
            if(requestQueue.size() == 0)
                return;
            finishedSave = requestQueue.poll();
            System.out.println(Duration.between(finishedSave.getTime(), Instant.now()).toMillis());
            /*
            while(((new Date()).getTime() - finishedSave.getTime().getTime()) / 1000 % 60 > 3 ) {
                finishedSave = requestQueue.poll();
                if(requestQueue.size() == 0)
                    return;
            }
            */
        }

        PrintWriter out = finishedSave.getResponse().getWriter();
        out.println(getString(username));
        return;
    }

    private String getString(String username){
        return String.format("Hello %s!\n", username);
    }
}