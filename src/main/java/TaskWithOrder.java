public class TaskWithOrder{
    int order;
    Task t;
    public TaskWithOrder(int order, Task t){
        this.order = order;
        this.t = t;
    }
    public String toString(){
        return (order+1) + "." + t;
    }
}