import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The whole functions of the software
 * @author Yinhe Chen, Zhengyi Li
 * @version 1.0
 */
public class WeLink {
    private UserGraph weLinkGraph;
    public WeLink(){}
    public WeLink(UserGraph userGraph) {
        this.weLinkGraph = userGraph;
    }

    public UserGraph getWeLinkGraph() {
        return weLinkGraph;
    }

    /**
     * constructor
     * @param weLinkGraph
     */
    public void setWeLinkGraph(UserGraph weLinkGraph) {
        this.weLinkGraph = weLinkGraph;
    }

    /**
     * main entrance
     * @param args
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        WeLink weLink = new WeLink();
        weLink.operation();
    }

    /**
     * Initialise the graph
     * @return usergraph
     * @throws Exception e
     */
    private UserGraph InitGraph() throws Exception {
        File f = new File("myObjects.txt");
        UserGraph weLinkGraph = null;
        if (!f.exists()) {
            weLinkGraph = initializeProject();
            writeFile(weLinkGraph);
        } else {
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream oi = new ObjectInputStream(fi);
            weLinkGraph = (UserGraph) oi.readObject();
        }
        return weLinkGraph;
    }

    /**
     * Simplify the code
     * @throws Exception e
     */
    public void operation() throws Exception {
        WeLink weLink = new WeLink();
        setWeLinkGraph(weLink.InitGraph());
        weLink.mainMenu(getWeLinkGraph());
    }

    /**
     * Write the graph into a file
     * @param weLinkGraph the graph
     * @throws Exception e
     */
    private void writeFile(UserGraph weLinkGraph) throws Exception {
        FileOutputStream f = new FileOutputStream("myObjects.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(weLinkGraph);
        o.close();
        f.close();
    }


    /**
     * Initialize the project
     * @return a graph
     */
    public UserGraph initializeProject() {
        UserGraph weLinkGraph = new UserGraph();
        weLinkGraph.createAUserGraph();
        return weLinkGraph;
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
     * The main menu of the software
     */
    public void mainMenu(UserGraph weLinkGraph) throws Exception {
        String choice;
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("+-----------------------+");
            System.out.println("|   Welcome to WeLink!  |");//加颜色
            System.out.println("+-----------------------+");
            System.out.println("  Please enjoy!!!  ");
            System.out.println("#A. Show my profile");
            System.out.println("#B. Modify my profile");
            System.out.println("#C. Show my friend list");
            System.out.println("#D. Post");
            System.out.println("#E. exit");
            choice = getString("Please enter your choice:");
            switch (choice) {
                case "a", "A" -> weLinkGraph.getPrimeUser().displayProfile();
                case "b", "B" -> weLinkGraph.modifyInformation();
                case "c", "C" -> {
                    weLinkGraph.getPrimeUser().displayFriendList();
                    friendListMenu(weLinkGraph);
                }
                case "d", "D" -> {
                    PostArea postArea = new PostArea();
                    postArea.postOne();
                }
                case "e", "E" -> {
                    System.out.println("Good bye!");
                    writeFile(weLinkGraph);
                    exit = true;
                }
                default -> System.out.println("Sorry, this is not a valid choice! Try again!");
            }
            System.out.println("------------------------------------------------\n");
        }
    }


    /**
     * menu that contains the  operations for friend List
     */
    public void friendListMenu(UserGraph weLink) {
        String choice;
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("#A. Display the profile of a friend");
            System.out.println("#B. Add new user as my friend.");
            System.out.println("#C. Delete a friend.");
            System.out.println("#D. Filter friends.");
            System.out.println("#E. Back to previous menu.");
            choice = getString("Please enter your choice:");
            switch (choice) {
                case "a", "A" -> chooseAndDisplay(weLink);
                case "b", "B" -> weLink.addAFriendUserToPrimeUSer();
                case "c", "C" -> weLink.deleteAFriendOfPrimeUser(weLink);
                case "d", "D" -> weLink.filterFriend();
                case "e", "E" -> exit = true;
            }
        }
    }

    /**
     *  Menu which contains operations on a chosen friend
     */
    public void friendMenu(User aFriend, UserGraph weLink) {
        boolean exit = false;
        String choice;
        while (!exit) {
            System.out.println();
            System.out.println("#1. Show " + aFriend.getName() + "'s information.");
            System.out.println("#2. Show " + aFriend.getName() + "'s friend list.");
            System.out.println("#3. Back to previous menu");
            choice = getString("Please enter your choice:");
            switch (choice) {
                case "1" -> aFriend.displayProfile();
                case "2" -> {
                    aFriend.displayFriendList();
                    friendFriMenu(weLink, aFriend);
                }
                case "3" -> exit = true;
            }

        }
    }

    /**
     * This method displays a menu of
     */
    public void friendFriMenu(UserGraph weLink, User aFriend) {
        boolean exit = false;
        String choice;
        while (!exit) {
            System.out.println();
            System.out.println("#1. Show common friend.");
            System.out.println("#2. Show strange friend");
            System.out.println("#3. Back to previous menu");
            choice = getString("Please enter your choice:");
            switch (choice) {
                case "1" -> weLink.filterCommonFriend(aFriend);
                case "2" -> weLink.showStrange(aFriend);
                case "3" -> exit = true;
            }
        }
    }

    /**
     * This method allows prime user to choose a friend and display his person profile
     */
    public void chooseAndDisplay(UserGraph weLink) {
        while (true) {
            try {
                int chosenID;
                System.out.println("Hello, you want to display the profile of a friend.");
                chosenID = getInteger("Please enter the ID of a friend:");
                for (User aFriend : weLink.getUserList()) {
                    if (aFriend.getUserId() == chosenID) {
                        friendMenu(aFriend, weLink);
                    }
                }
                break;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Sorry, no user found!");
                System.out.println("Please try again! You can refer to the IDs given in the friend list.");
            }
        }
    }
}

