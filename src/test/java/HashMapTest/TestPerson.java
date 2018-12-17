package HashMapTest;

public class TestPerson {
    private String name;

    public TestPerson(String name) {
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object that){

        if(!(that instanceof TestPerson)) {
            return false;
        }

        return this.name.equals(((TestPerson) that).name);
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;

    }
}

