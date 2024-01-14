import java.io.Serializable;
import java.util.*;

/**
 * All the profile of user and methods
 * @author XU Liu, Zhengyi Li, Yinhe Chen
 * @version 1.0
 */
public class User implements Serializable {
    private int userId;
    private int age;
    private String name;
    private String workPlace;
    private String homeTown;
    private Set<User> friendSet;
    private final String red = "\u001B[31m";
    private final String blue = "\u001B[34m";
    private final String reset = "\u001B[0m";
    public User(ArrayList<User> userList) {
        friendSet = new HashSet<>();
        userId = userList.size()+1;
        userList.add(this);
    }

    /**
     * constructor
     * @param age user's age
     * @param name user's name
     * @param workPlace user's workplace
     * @param homeTown user's hometown
     * @param userList user's friends
     */
    public User(int age, String name, String workPlace, String homeTown,ArrayList<User> userList) {
        this.age = age;
        this.name = name;
        this.workPlace = workPlace;
        this.homeTown = homeTown;
        friendSet = new HashSet<>();
        userId = userList.size() + 1;
        //add to user list
        userList.add(this);
    }

    /**
     * Get the age of user
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Set the age of user
     * @param age user's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the name of user
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the age of user
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the workplace of user
     * @return workplace
     */
    public String getWorkPlace() {
        return workPlace;
    }

    /**
     * Set the workplace of the user
     * @param workPlace user's workplace
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
    /**
     * Get the hometown of user
     * @return user's hometown
     */
    public String getHomeTown() {
        return homeTown;
    }

    /**
     * Set the hometown of users
     * @param homeTown user's hometown
     */
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    /**
     * Set the friend list
     * @return the friend list
     */
    public Set<User> getFriendSet() {
        return friendSet;
    }

    /**
     * Get the id of user
     * @return user's id
     */
    public int getUserId() {
        return userId;
    }


    /**
     * This method requires user to enter an integer to operate
     *
     * @param userPrompt Descriptive information of the integer
     * @return The integer user entered
     */
    public int getInteger(String userPrompt) {
        int aInteger;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(userPrompt);
            aInteger = scanner.nextInt();
        } catch (NumberFormatException | InputMismatchException e) {
            throw e;
        }
        return aInteger;
    }


    /**
     * This method requires user to enter a string to operate
     *
     * @param userPrompt Descriptive information of the string
     * @return The string user entered
     */
    public String getString(String userPrompt) {
        String aString;
        Scanner scanner = new Scanner(System.in);
        System.out.println(userPrompt);
        aString = scanner.next();
        return aString;
    }



    /**
     * This method add a user to the user list.
     */
    public void initializeInform() {
        int age;
        String name;
        String homeTown;
        String workPlace;
        System.out.println("Please set the following information:");
        // age
        age = getInteger("Please enter your age:");
        this.setAge(age);
        // name
        name = getString("Please enter your name:");
        this.setName(name);
        // home
        homeTown = getString("Please enter your home town:");
        this.setHomeTown(homeTown);
        // workPLace
        workPlace = getString("Please enter your work place:");
        this.setWorkPlace(workPlace);
        System.out.println("Thanks " + this.name + " ! Your file is created!");
    }

    /**
     * This method add a user to friend list.
     * Notice that the user takes this action is added to target user's friend list
     * This is useful for hard code some users before use this software
     */
    public void addToFriendList(User aUser) {
        if (friendSet.contains(aUser)) System.out.println(aUser.getName() + " is already in your friend list");
        if (aUser.equals(this)) System.out.println(red+"Sorry, you can not add yourself into your friend list!"+reset);
        friendSet.add(aUser);
        aUser.friendSet.add(this);
    }


    /** This method compares if two users are the same user
     *
     * @param aUser  The user to compare
     * @return  true if the same user,otherwise false
     */
    public boolean equals (User aUser){
        return this.userId == aUser.userId;
    }

    /**
     * This method show the friend list of the input user.
     */
    public void displayFriendList(){
        System.out.println(this.getName()+"'s friend list:");
        if(this.friendSet.size() == 0){
            System.out.println("Empty.");
        }else {
            for (User aFriend : this.friendSet) {
                System.out.println(aFriend.getName() + "   \t   Id: " + aFriend.getUserId());
            }
            System.out.println("--------------------------------");
        }
    }


    /** This method displays personal profile of a user
     */
    public void displayProfile(){
        System.out.println("Hello, this is the personal profile of "+ blue+this.getName()+reset);
        System.out.println("The name is: "+blue+this.getName()+reset);
        System.out.println("The age is: "+blue+this.getAge()+reset);
        System.out.println("The hometown is: "+blue+this.getHomeTown()+reset);
        System.out.println("The work place is: "+blue+this.getWorkPlace()+reset);
    }
}