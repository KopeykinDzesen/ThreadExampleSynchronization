import com.sun.org.apache.regexp.internal.RE;

public class Main {

    public static void main(String[] args) throws Exception {

        ResourceStatic.i = 5;
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread1 = new MyThread();
        myThread.start();
        myThread1.start();
        myThread.join();
        myThread1.join();

        System.out.println(ResourceStatic.i);

    }

}

class  MyThread extends Thread{
    Resource resource;

    @Override
    public void run() {
        ResourceStatic.changeIStatic();
        new ResourceStatic().changeI();
    }
}

class Resource{
    private int i;

    public int getI() {
        return i;
    }

    public synchronized void setI(int i) {
        this.i = i;
    }

    public void changeI(){
        int i = this.i;
        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }
        i++;
        this.i = i;
    }
}

class ResourceStatic{
    public static int i;

    public static void changeIStatic(){
        synchronized (Resource.class){
            int i = ResourceStatic.i;
            if(Thread.currentThread().getName().equals("one")){
                Thread.yield();
            }
            i++;
            ResourceStatic.i = i;
        }
    }

    public void changeI(){
        synchronized (this) {
            int i = ResourceStatic.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            ResourceStatic.i = i;
        }
    }
}
