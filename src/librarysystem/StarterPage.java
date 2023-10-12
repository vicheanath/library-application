package librarysystem;




import dataaccess.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.Autoscroll;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarterPage extends JFrame{

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new StarterPage();
            frame.setTitle("Sample Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(frame);
            frame.setVisible(true);
        });

    }

    public static void centerFrameOnDesktop(Component f) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;
        int frameHeight = f.getSize().height;
        int frameWidth = f.getSize().width;
        f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
    }


    JList<String> linkList;
    JList<String> linkList1;
    JList<String> linkList2;
    JList<String> linkList3;
    //context for CardLayout
    JPanel cards;
    List<String> itemsList = Arrays.asList("Item 1", "Item 2", "Item 3","Item 4","Item 5");
    //List<String> items = new ArrayList<>(itemsList);
    String[] items = {"Item 1", "Item 2", "Item 3","Item 4","Item 5"};
    String[] items1 = {"Item 1", "Item 2"};
    String[] items2 = {"Item 3","Item 4","Item 5"};
    List<String> itemsArrayList1 = new ArrayList<>(Arrays.asList(items1));
    List<String> itemsArrayList2 = new ArrayList<>(Arrays.asList(items2));
    String[] adminRights = {"Login/Logout", "Add member", "Search Member","Add Book", "Add Book Copy", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};

    String[] librarian = {"Login/Logout", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};

    String[] bookOptions = {"Login/Logout", "Add member", "Search Member","Checkout Book","Add Book", "Add Book Copy", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};

    List<String> itemsArrayList3 = new ArrayList<>(Arrays.asList(librarian));

    public StarterPage() {
        setSize(300, 200);

        linkList = new JList<String>(items);
        linkList1 = new JList<String>(items1);
        linkList2 = new JList<String>(items2);
        linkList3 = new JList<String>(bookOptions);
        createPanels();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList3, cards);
        splitPane.setDividerLocation(50);
        //default layout for JFrame is BorderLayout; add method
        //adds to contentpane
        add(splitPane, BorderLayout.CENTER);
    }

    /* Organize panels into a CardLayout */
    public void createPanels(Auth auth) {

        JPanel[] panel=new JPanel[bookOptions.length];
        cards = new JPanel(new CardLayout());


        Menu[] menus = { new AddMember("Add member")};

        // for (int i = 0;i<bookOptions.length;i++){
        //     panel[i]=new JPanel();
        //     panel[i].add(new JLabel(bookOptions[i]));
        //     cards.add(panel[i],bookOptions[i]);
        // }
            


        //connect JList elements to CardLayout panels
        /*linkList3.addListSelectionListener(event -> {
            Integer v = linkList3.getSelectedIndex();
            String value = linkList3.getSelectedValue().toString();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, value);
        });
        */
        if (auth.equals(Auth.LIBRARIAN)){
            //System.out.println("Admin");
        }

        linkList3.addListSelectionListener(event -> {
            List<String> adminRightsList = new ArrayList<>(Arrays.asList(adminRights));
            List<String> librarianRightsList = new ArrayList<>(Arrays.asList(librarian));
            List<String> allOptionsList = new ArrayList<>(Arrays.asList(bookOptions));


            Integer v = linkList3.getSelectedIndex();
            String word = linkList3.getSelectedValue();
            //String value = linkList.getSelectedValue().toString();

            if (auth.equals(Auth.LIBRARIAN)){
                if (librarianRightsList.contains(word)){
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, String.valueOf(word));
                }
            }
            if (auth.equals(Auth.ADMIN)){
                if (adminRightsList.contains(word)){
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, String.valueOf(word));
                }
            }
            if (auth.equals(Auth.BOTH)){
                if (allOptionsList.contains(word)){
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, String.valueOf(word));
                }
            }

        });



    }




    public void createPanels() {

        JPanel[] panel=new JPanel[bookOptions.length];
        cards = new JPanel(new CardLayout());

        for (int i = 0;i<bookOptions.length;i++){
            panel[i]=new JPanel();
            panel[i].add(new JLabel(bookOptions[i]));
            cards.add(panel[i],bookOptions[i]);
        }
        //connect JList elements to CardLayout panels
        /*linkList3.addListSelectionListener(event -> {
            Integer v = linkList3.getSelectedIndex();
            String value = linkList3.getSelectedValue().toString();
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, value);
        });
        */


        linkList3.addListSelectionListener(event -> {
            List<String> adminRightsList = new ArrayList<>(Arrays.asList(adminRights));
            List<String> librarianRightsList = new ArrayList<>(Arrays.asList(librarian));
            List<String> allOptionsList = new ArrayList<>(Arrays.asList(bookOptions));


            Integer v = linkList3.getSelectedIndex();
            String word = linkList3.getSelectedValue();
            //String value = linkList.getSelectedValue().toString();
            if (adminRightsList.contains(word)){
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, String.valueOf(word));
            }

        });



    }

    private static final long serialVersionUID = -760156396736751840L;

}



class Menu{
    String name;
    Auth[] auth;
    Menu(String name, Auth[] auth){
        this.name=name;
        for (Auth a: auth){
            this.auth[a.ordinal()]=a;
        }
    }
}

class Login extends Menu{

    Login(String name){
        super(name, new Auth[]{Auth.ADMIN, Auth.BOTH});

    }
}

class AddMember extends Menu{

    AddMember(String name){
        super(name, new Auth[]{Auth.ADMIN, Auth.BOTH});
    }
}
