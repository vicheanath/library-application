package librarysystem;




import dataaccess.Auth;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarterPage extends JFrame implements LibWindow {

    public final static StarterPage INSTANCE =new StarterPage();

    private boolean isInitialized = false;

    @Override
    public void init() {
        /*JFrame frame = new JFrame("We are here");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(createPanels(Auth.LIBRARIAN));
        //frame.add(createJsplitPanal(Auth.LIBRARIAN));
        createPanels(Auth.LIBRARIAN);
        createJsplitPanal(Auth.LIBRARIAN);
        frame.pack();
        frame.setVisible(true);
        isInitialized = true;*/

        EventQueue.invokeLater(() ->
        {
            StarterPage starterPage = new StarterPage();
            JFrame frame = new StarterPage();
            frame.setTitle("Sample Frame");
            //frame.add(starterPage.createPanels(Auth.LIBRARIAN));
            //frame.add(starterPage.createJsplitPanal(Auth.LIBRARIAN));
            starterPage.createPanels(Auth.ADMIN);
            starterPage.createJsplitPanal(Auth.ADMIN);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(frame);
            frame.setVisible(true);
        });

    }

    public void init(Auth auth) {
        /*JFrame frame = new JFrame("We are here");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(createPanels(Auth.LIBRARIAN));
        //frame.add(createJsplitPanal(Auth.LIBRARIAN));
        createPanels(Auth.LIBRARIAN);
        createJsplitPanal(Auth.LIBRARIAN);
        frame.pack();
        frame.setVisible(true);
        isInitialized = true;*/

        EventQueue.invokeLater(() ->
        {
            StarterPage starterPage = new StarterPage();
            JFrame frame = new StarterPage();
            frame.setTitle("Sample Frame");
            //frame.add(starterPage.createPanels(Auth.LIBRARIAN));
            //frame.add(starterPage.createJsplitPanal(Auth.LIBRARIAN));
            starterPage.createPanels(auth);
            starterPage.createJsplitPanal(auth);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(frame);
            frame.setVisible(true);
        });

    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            StarterPage starterPage = new StarterPage();
            JFrame frame = new StarterPage();
            frame.setTitle("Sample Frame");
            starterPage.createPanels(Auth.ADMIN);
            starterPage.createJsplitPanal(Auth.ADMIN);
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


    String[] adminRights = {"Login/Logout", "Add member", "Search Member","Add Book", "Add Book Copy", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};
    String[] librarian = {"Login/Logout", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};
    String[] bookOptions = {"Login/Logout", "Add member", "Search Member","Checkout Book","Add Book", "Add Book Copy", "Check Status of Book Copy", "All Member IDs", "All Book IDs"};
    List<String> itemsArrayList3 = new ArrayList<>(Arrays.asList(librarian));

    public StarterPage() {
        setSize(300, 200);

    ;
        linkList3 = new JList<String>(bookOptions);
        createPanels(Auth.LIBRARIAN);
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList3, cards);
        splitPane.setDividerLocation(50);
        //default layout for JFrame is BorderLayout; add method
        //adds to contentpane
        add(splitPane, BorderLayout.CENTER);
    }
    private void createJsplitPanal(Auth auth) {
        String[] resultant = null;

        if (auth.equals(Auth.ADMIN)){
            resultant = adminRights;
        }else if(auth.equals(Auth.LIBRARIAN)){
            resultant = librarian;
        }else {
            resultant = bookOptions;
        }
        linkList = new JList<String>(resultant);
        //createPanels();
        // set up split panes
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
        splitPane.setDividerLocation(100);
        //default layout for JFrame is BorderLayout; add method
        //adds to contentpane
        add(splitPane, BorderLayout.CENTER);
        //return splitPane;
    }

    /* Organize panels into a CardLayout */
    public void createPanels(Auth auth) {
        String[] resultant = null;

        if (auth.equals(Auth.ADMIN)){
           resultant = adminRights;
        }else if(auth.equals(Auth.LIBRARIAN)){
            resultant = librarian;
        }else {
            resultant = bookOptions;
        }
        JPanel[] panel=new JPanel[resultant.length];
        cards = new JPanel(new CardLayout());
        linkList3 = new JList<String>(resultant);

        for (int i = 0;i<resultant.length;i++){
            panel[i]=new JPanel();
            panel[i].add(new JLabel(resultant[i]));
            cards.add(panel[i],resultant[i]);
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
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, String.valueOf(word));

        });


        //return cards;
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