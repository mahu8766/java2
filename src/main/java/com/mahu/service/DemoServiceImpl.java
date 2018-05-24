package com.mahu.service;

import sun.nio.ch.ThreadPool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DemoServiceImpl implements  IDemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
    public static void main(String[] args)
    {
        DemoServiceImpl _s=new  DemoServiceImpl();

            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                    200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));

                for(int i=0;i<15;i++){
                    MyTask myTask = new MyTask(i);
                    executor.execute(myTask);
                    System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                            executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
                }
                executor.shutdown();


    }


}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {

            RandomAccessFile aFile = new RandomAccessFile("C:\\Users\\hp\\Desktop\\a.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read

                while(buf.hasRemaining()){
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}


