package com.example.javafx_lab5;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class SimpleThread extends Thread{
    private String s;
    private int time;
    private volatile boolean stopped = false;
    private TextArea textArea = null;

    public SimpleThread (String str, String time) {
        s = str;
        this.time = Integer.parseInt(time);
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public void changeName(String name) {
        s = name;
    }

    public void changeTime(String time) {
        this.time = Integer.parseInt(time);
    }

    @Override
    public void run() {
        while (!stopped) {
            System.out.println(s);
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    textArea.appendText(s + "\n");

                }
            });
            try {
                this.sleep(time);
            } catch (InterruptedException e) {
                System.out.println(s+" exit");
                return;
            }
        }
        this.interrupt();
    }

    public void stopThread() {
        stopped = true;
    }
}
