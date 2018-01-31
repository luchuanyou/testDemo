package com.base;

import java.io.*;

/**
 * 序列化
 */
public class TestSerialize {

    public static void main(String[] args) {
        Person person = new Person("mark",12);

        //序列化
//        serialize(person);
        //反序列化
        deserialize();
    }
    /**
     * 序列化对象
     * @param person
     */
    public static void serialize(Person person){
        try {
            FileOutputStream fos = new FileOutputStream("D:\\temp\\person.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.println("Person--Jay,24---Written");
            System.out.println("Name is: "+person.getName());
            System.out.println("Age is: "+person.getAge());

            oos.writeObject(person);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /**
     * 反序列化对象，和序列化时的序列化id如果不一样就会报错
     */
    public static void deserialize() {
        try {
            FileInputStream fis = new FileInputStream("D:\\temp\\person.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Person person = (Person)ois.readObject();
            System.out.println("\n--------------------\n");
            System.out.println("Person--Jay,24---Restored");
            System.out.println("Name is: "+person.getName());
            System.out.println("Age is: "+person.getAge());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

class Person implements Serializable {

    /**
     *
     */
//    private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = 2L;

    private String name;
    private Integer age;


    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Person(String name, Integer age) {

        super();
        this.name = name;
        this.age = age;
    }


    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }


    public Integer getAge() {

        return age;
    }


    public void setAge(Integer age) {

        this.age = age;
    }

}
