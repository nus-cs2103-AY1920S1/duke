class TaskImplementation implements TaskInterface {
    private String name;
    public TaskImplementation(String name) {
        this.name = name;
    }

    // Override
    @Override
    public String toString(){
        return this.name;
    }
          
}
