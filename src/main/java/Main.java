public class Main {

    public static void main(String[] args) throws Exception {

        Resource resource = new Resource();
        resource.setI(5);
        MyThread myThread = new MyThread();
        myThread.setName("one");
        MyThread myThread1 = new MyThread();
        myThread.setResource(resource);
        myThread1.setResource(resource);
        myThread.start();
        myThread1.start();
        myThread.join();
        myThread1.join();

        System.out.println(resource.getI());

    }

}

class  MyThread extends Thread{
    Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.changeI();
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

    public synchronized void changeI(){
        int i = this.i;
        if(Thread.currentThread().getName().equals("one")){
            Thread.yield();
        }
        i++;
        this.i = i;
    }
}
