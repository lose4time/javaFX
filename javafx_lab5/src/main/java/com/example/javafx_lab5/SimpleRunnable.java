package com.example.javafx_lab5;


import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class SimpleRunnable implements Runnable {
    private String s;
    private volatile boolean stopped=false;
    private TextArea textArea = null;

    public void setTextArea(TextArea tArea) {
        textArea=tArea;
    }

    public SimpleRunnable (String str){
        s=str;
    }
    @Override
    public void run() { //основная функция потокового класса
        while (!stopped){
            System.out.println(s);
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    textArea.appendText(s = "\n");
                }
            });
            try {//нужно для обработки вероятных ошибок при задержке потока
                Thread.sleep(500); //задерживаем работу потока на 0,5 секунды
            } catch (InterruptedException e) {
                System.out.println(s+" exit"); //печатаем сообщение
                return; //и выходим из потока
            }
        }
        Thread.currentThread().interrupt(); //прерываем поток объекта
    }
    public void stopThread() { //метод для остановки потока
        stopped=true;
    }
}