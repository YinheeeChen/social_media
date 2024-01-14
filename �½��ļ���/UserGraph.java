import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * This class is about the data structure
 * @author Yinhe Chen, Zhengyi Li, Xu Liu
 * @version 1.0
 */
public class UserGraph implements Serializable {
    @Serial
    private static final long serialVersionUID = 6736507681297899758L;
    private ArrayList<User> userList;
    private User primeUser;
    private final String red = "\u001B[31m";
    private final String blue = "\u001B[34m";
    private final String reset = "\u001B[0m";

    /**
     * constructor
     */
    public UserGraph() {
        userList = new ArrayList<>();
        primeUser = new User(18, "GG Bond", "DIICSU", "Chengdu", getUserList());
    }

    /**
     * constructor
     * @param primeUser the user
     */
    public UserGraph(User primeUser) {
        this.primeUser = primeUser;
    }

    /**
     * Get the list
     * @return list
     */
    public ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * Get the user
     * @return user
     */
    public User getPrimeUser() {
        return primeUser;
    }

    /**
     * This method can and can only add a new friend to prime user
     * And prime user will be in the friend list of this user as well
     */
    public void addAFriendUserToPrimeUSer() {
        User newFriend = new User(getUserList());
        newFriend.initializeInform();
        //add this user to user list of the software
        getUserList().add(newFriend);
        //add this user to prime user's friend list
        getPrimeUser().addToFriendList(newFriend);
        System.out.println("Congratulations! GG Bond has a new friend now!");
        System.out.println("-----------------------------------------------\n");
    }

    /**
     * This method allows prime user, namely the user of the software to delete a friend from friend list
     * And prime user will be deleted by this friend as well
     */
    public void deleteAFriendOfPrimeUser(UserGraph userGraph) {
        getPrimeUser().displayFriendList();
        while (true) {
            try {
                int chosenID;
                System.out.println("Hello, you want to delete a friend.");
                chosenID = getInteger("Please enter the ID of a friend:");
                for (User aFriend : userGraph.getUserList()) {
                    if (aFriend.getUserId() == chosenID) {
                        getPrimeUser().getFriendSet().remove(aFriend);
                        aFriend.getFriendSet().remove(getPrimeUser());
                        System.out.println("You deleted " + aFriend.getName() + " from your friend list!");
                        return;
                    }
                }
                System.out.println(red+"Sorry. No such Id found!"+reset);
                break;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(red+"Sorry, no user found!"+reset);
                System.out.println(red+"Please try again! You can refer to the IDs given in the friend list."+reset);
            }
        }
    }

    /**
     * This method displays profile of prime user after modification
     */
    public void displayNewProfile() {
        System.out.println("Modified successfully!\n");
        System.out.println("----------This is your profile------------");
        getPrimeUser().displayProfile();
        System.out.println("\n");
    }

    /**
     * This method modify Prime user's information.
     */
    public void modifyInformation() {
        System.out.println("--------This is the profile---------");
        getPrimeUser().displayProfile();
        System.out.println("\n------------------------");
        boolean exit = false;
        while (!exit) {
            System.out.println("which information would you like to modify");
            System.out.println("#A. Name");
            System.out.println("#B. Age");
            System.out.println("#C. Hometown");
            System.out.println("#D. workplace");
            System.out.println("#E. Back to previous menu");
            String choice = getString("Please enter your choice:");
            System.out.println("-------------------------");
            switch (choice) {
                case "a", "A" -> {
                    String newName = getString("Please enter a new name:");
                    getPrimeUser().setName(newName);
                    displayNewProfile();
                }
                case "b", "B" -> {
                    try {
                        int newAge = getInteger("Please enter a new age:");
                        getPrimeUser().setAge(newAge);
                        displayNewProfile();
                    } catch (NumberFormatException | InputMismatchException e) {
                        System.out.println(red+"This is not a valid age!"+reset);
                    }
                }
                case "c", "C" -> {
                    String newHomeTown = getString("Please enter a new home town:");
                    getPrimeUser().setHomeTown(newHomeTown);
                    displayNewProfile();
                }
                case "d", "D" -> {
                    String newWorkPlace = getString("Please enter a new work place:");
                    getPrimeUser().setWorkPlace(newWorkPlace);
                    displayNewProfile();
                }
                case "e", "E" -> {
                    System.out.println("Back to previous menu!");
                    exit = true;
                }
                default -> System.out.println("Please enter a, b, c, d or e to operate");
            }
        }
    }

    /**
     * This method create some users by hard coding
     */
    public void createAUserGraph() {
        User Mike = new User(21, "Mike", "DIICSU", "Chengdu", getUserList());
        User Amy = new User(21, "Amy", "DIICSU", "Chengdu", getUserList());
        User Siri = new User(45, "Siri", "Los-angle", "USA", getUserList());
        User Nico = new User(19, "Nico", "DIICSU", "Dundee", getUserList());
        User Franklin = new User(34, "Franklin", "Los-angle", "Dundee", getUserList());
        User Felix = new User(12,"Felix","CSU","Hangzhou",getUserList());
        User Echo = new User(89,"Echo","Hangzhou","Changsha",getUserList());
        User Dickens = new User(28,"Dickens","Glasgow","Dundee",getUserList());
        User Pardis = new User(19,"Pardis","Dundee","Dundee",getUserList());
        User Chris = new User(21,"Chris","Dundee","Dundee",getUserList());
        User Karen = new User(29,"Karen","Dundee","Dundee",getUserList());
        //Richard has no friend.
        User Richard = new User(19, "Richard", "电子科大", "Chengdu", getUserList());
        //Amy and Nico are friends of Mike
        Mike.addToFriendList(Amy);
        Mike.addToFriendList(Nico);
        Mike.addToFriendList(Echo);
        Mike.addToFriendList(Felix);
        //Franklin and Mike are friends of Siri
        Siri.addToFriendList(Mike);
        Siri.addToFriendList(Franklin);
        Siri.addToFriendList(Dickens);
        //all the people are friends of Franklin
        Franklin.addToFriendList(Mike);
        Franklin.addToFriendList(Amy);
        Franklin.addToFriendList(Nico);
        Franklin.addToFriendList(Pardis);
        //Chris is the friend of Pardis
        Pardis.addToFriendList(Chris);
        Pardis.addToFriendList(Karen);
        // Mike,Amy and Siri are friends of prime user
        getPrimeUser().addToFriendList(Mike);
        getPrimeUser().addToFriendList(Amy);
        getPrimeUser().addToFriendList(Siri);
        getPrimeUser().addToFriendList(Pardis);
        getPrimeUser().addToFriendList(Echo);
        getPrimeUser().addToFriendList(Karen);
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
        aString = scanner.nextLine();
        return aString;
    }

    /**
     * This method allows prime user to filter his friends by given condition
     */
    public void filterFriend() {
        System.out.println("You want to filter your friends!");
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Please choose a filter:");
            System.out.println("#A. HomeTown");
            System.out.println("#B. Work Place");
            System.out.println("#C. Name");
            System.out.println("#D. Back");
            choice = sc.next();
            switch (choice) {
                case "a", "A" -> filterWithHometown(getPrimeUser(), getPrimeUser());
                case "b", "B" -> filterWithWorkplace(getPrimeUser(), getPrimeUser());
                case "c", "C" -> filterName();
                case "d", "D" -> exit = true;
            }
        }
    }

    /**
     * This method filter common friends.
     *
     * @param friend A friend of prime user
     */
    public void filterCommonFriend(User friend) {
        Set<User> commonFriend = new HashSet<>(getPrimeUser().getFriendSet());
        commonFriend.retainAll(friend.getFriendSet());
        if (commonFriend.size() == 0) {
            System.out.println(blue+"No common friend."+reset);
        } else {
            List<User> commonFriends = new ArrayList<>(commonFriend);
            for (int i = 0; i < commonFriends.size(); i++) {
                System.out.println(i + 1 + ". " + commonFriends.get(i).getName());
            }
        }
    }

    /** This method display strange user which are in the friend list of friend of prime user for prime user
     * @param friend A friend of prime user
     */
    public void showStrange(User friend) {
        Set<User> myFriends = new HashSet<>(getPrimeUser().getFriendSet()); // copy the original set
        Set<User> difference = friend.getFriendSet();
        difference.removeAll(myFriends);
        if (difference.size() == 0) {
            System.out.println(blue+"No strange friend."+reset);
        } else {
            List<User> differences = new ArrayList<>(difference);
            for (int i = 0; i < differences.size(); i++) {
                if (!Objects.equals(differences.get(i).getName(), getPrimeUser().getName())) {
                    System.out.println(i + 1 + ". " + differences.get(i).getName());
                }
            }
            System.out.println();
            filterWithHometown(getPrimeUser(), friend);
            System.out.println();
            filterWithWorkplace(getPrimeUser(), friend);
        }
    }

    /**
     * This method filter friends of prime user by their name
     */
    public void filterName() {
        String input = getString("Please enter the name:");
        List<User> name = new ArrayList<>();
        Set<User> friendSet = new HashSet<>(getPrimeUser().getFriendSet());
        for (User friend : friendSet) {
            if (friend.getName().contains(input)) {
                name.add(friend);
            }
        }
        if (name.size() == 0) {
            System.out.println(blue+"No user found"+reset);
        }
        for (int j = 0; j < name.size(); j++) {
            System.out.println(j + 1 + ". " + name.get(j).getName());
        }

    }

    /**
     * This method filter users who have same hometown with the prime user.
     */
    public void filterWithHometown(User primeUser, User aUser) {
        List<User> sameHometown = new ArrayList<>();
        for (User friend : aUser.getFriendSet()) {
            if (friend.getHomeTown().equals(primeUser.getHomeTown()) && !friend.getName().equals(primeUser.getName())) {
                sameHometown.add(friend);
            }
        }
        System.out.println("Here are users who have the same home town as you:");
        if (sameHometown.size() == 0){
            System.out.println(blue+"No user found!."+reset);
        }else {
            for (int j = 0; j < sameHometown.size(); j++) {
                if (!Objects.equals(primeUser.getName(), sameHometown.get(j).getName())) {
                    System.out.println(j + 1 + ". " + sameHometown.get(j).getName());
                }

            }
        }
    }

    /**
     * This method filter friend who has same workplace with the user.
     */
    public void filterWithWorkplace(User primeUser, User aUser) {
        List<User> sameWorkplace = new ArrayList<>();
        for (User friend : aUser.getFriendSet()) {
            if (friend.getWorkPlace().equals(primeUser.getWorkPlace()) && !friend.getName().equals(primeUser.getName())) {
                sameWorkplace.add(friend);
            }
        }
        System.out.println("Here are users who have the same work place as you:");
        if (sameWorkplace.size() == 0){
            System.out.println("Empty.");
        }else {
        for (int j = 0; j < sameWorkplace.size(); j++) {
                System.out.println(j + 1 + ". " + sameWorkplace.get(j).getName());
            }
        }
    }
}