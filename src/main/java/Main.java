import model.dao.BookDAO;
import model.dao.CategoryDAO;
import model.dao.ReservationDAO;
import model.dao.UserDAO;
import model.entity.Book;
import model.entity.Category;
import model.entity.Reservation;
import model.entity.User;
import utils.HibernateUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String loginOrReg;
        String logUsername;
        String logPassword;
        String regUsername;
        String regEmail;
        String regPassword;
        int currentUserId;
        UserDAO userDAO = new UserDAO();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\nWhat you would like to do?:\n" +
                    "1. For login.\n" +
                    "2. For register.\n" +
                    "Please select a number for operation:\n");
            loginOrReg = scan.next();
            if (loginOrReg.contains("1")) {
                System.out.println("Enter your username:");
                logUsername = scan.next();
                System.out.println("Enter your password:");
                logPassword = scan.next();
// ___________________validacija_____________________________________
                currentUserId = userDAO.findUserByUsername(logUsername).getId();

                if (userDAO.login(logUsername, logPassword)) {
                    if (userDAO.getUserRole(logUsername) == false) {
                        userFunc(currentUserId);
                    } else {
                        adminFunc();
                    }
                }
//_______________________________________________________________________________________
            } else if (loginOrReg.contains("2")) {
                System.out.println("Create your username:");
                regUsername = scan.next();
                System.out.println("Create your email:");
                regEmail = scan.next();
                System.out.println("Create your password:");
                regPassword = scan.next();
                userDAO.register(regUsername, regEmail, false, regPassword);
            }
            HibernateUtil.getSessionFactory().close();
            break;
        }
    }


    public static void adminFunc() {
        while (true) {
            BookDAO bookDao = new BookDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            Scanner scan = new Scanner(System.in);
            int adminOperation;
            String bookOperation;
            String categoryOperation;
            String categoryName;
            String bookTitle;
            String bookDescription;
            String bookIsbn;
            String bookPhoto;
            int bookNumberOfPages;
            Category bookCategory;
            int bookCategoryId;
            int bookCategoryUpdateId;
            int categoryDeleteId;
            int categoryUpdateId;
            int bookDeleteId;
            int bookUpdateId;
            String newCategoryName;
            String newTitle;
            String newDescription;
            String newIsbn;
            String newPhoto;
            int newNumberOfPages;
            Category newCategory;

            System.out.println("\nWelcome to admin panel. Please choose working panel:\n " +
                    "1. BOOKS\n" +
                    "2. BOOK CATEGORIES\n");
            adminOperation = scan.nextInt();
            if (adminOperation == 1) {
                System.out.println("\nWhich operation you would like to execute?\n" +
                        "1. For adding a new book.\n" +
                        "2. For deleting a book.\n" +
                        "3. For editing a book.\n" +
                        "4. For log out.\n" +
                        "Please enter the number of the option:\n");
                bookOperation = scan.next();
                if (bookOperation.equals("1")) {
                    System.out.println("Title of the book?:");
                    scan.nextLine();
                    bookTitle = scan.nextLine();
                    System.out.println("Description of the book?:");
                    bookDescription = scan.nextLine();
                    System.out.println("ISBN code?:");
                    bookIsbn = scan.nextLine();
                    System.out.println("Url of the book photo:");
                    bookPhoto = scan.nextLine();
                    System.out.println("Pages in the book?:");
                    bookNumberOfPages = scan.nextInt();
                    System.out.println("Category the book?:");
//                        __________________choose category_______________
                    System.out.println("Chose a number of the category id from the list below:\n");
                    System.out.println(categoryDao.searchAll());
                    bookCategoryId = scan.nextInt();
                    bookCategory = categoryDao.searchById(bookCategoryId);
                    System.out.println("\n New book is added!\n");
//                        ________________________________________________
                    Book newBook = new Book(bookTitle, bookDescription, bookIsbn, bookPhoto,
                            bookNumberOfPages, bookCategory);
                    bookDao.insert(newBook);
                } else if (bookOperation.contains("2")) {
                    bookDao.searchAll();
                    System.out.println("Enter the ID of the book to delete:");
                    bookDeleteId = scan.nextInt();
                    bookDao.deleteById(bookDeleteId);
                    System.out.println("\nThe book is deleted\n");
                } else if (bookOperation.contains("3")) {
                    System.out.println(bookDao.searchAll());
                    System.out.println("Enter id of the book to edit:\n");
                    bookUpdateId = scan.nextInt();
                    System.out.println("Enter new title of the book:\n");
                    scan.nextLine();
                    newTitle = scan.nextLine();
                    System.out.println("Enter new description of the book:\n");
                    newDescription = scan.nextLine();
                    System.out.println("Enter new ISBN code of the book:\n");
                    newIsbn = scan.nextLine();
                    System.out.println("Enter new url of the book photo:\n");
                    newPhoto = scan.nextLine();
                    System.out.println("Enter new number of pages of the book:\n");
                    newNumberOfPages = scan.nextInt();
                    System.out.println("Enter new category of the book:\n");
                    //                        __________________choose category_______________
                    System.out.println("Chose a number of the category id from the list below:\n");
                    System.out.println(categoryDao.searchAll());
                    bookCategoryUpdateId = scan.nextInt();
                    newCategory = categoryDao.searchById(bookCategoryUpdateId);
//                        ________________________________________________

                    Book updatedBook = new Book(bookUpdateId, newTitle, newDescription, newIsbn, newPhoto,
                            newNumberOfPages, newCategory);
                    bookDao.update(updatedBook);
                    System.out.println("\nThe book is updated!\n");
                }
            } else if (adminOperation == 2) {
                System.out.println("Which operation you would like to execute?\n" +
                        "1. For adding a new category.\n" +
                        "2. For deleting a category.\n" +
                        "3. For editing a category.\n" +
                        "4. For log out." +
                        "Please enter the number of the option:");
                categoryOperation = scan.next();
                if (categoryOperation.equals("1")) {
                    System.out.println("Name of the book category?:\n");
                    scan.nextLine();
                    categoryName = scan.nextLine();
                    Category addCategory = new Category(categoryName);
                    categoryDao.insert(addCategory);
                    System.out.println("\nNew category added\n");
                } else if (categoryOperation.equals("2")) {
                    categoryDao.searchAll();
                    System.out.println("Enter the ID of the category to delete:");
                    categoryDeleteId = scan.nextInt();
                    categoryDao.deleteById(categoryDeleteId);
                    System.out.println("\nThe category is deleted!\n");
                } else if (categoryOperation.equals("3")) {
                    System.out.println(categoryDao.searchAll());
                    System.out.println("Enter id of the category to edit:\n");
                    categoryUpdateId = scan.nextInt();
                    System.out.println("Enter new name of the category:\n");
                    scan.nextLine();
                    newCategoryName = scan.nextLine();
                    Category updatedCategory = new Category(categoryUpdateId, newCategoryName);
                    categoryDao.update(updatedCategory);
                    System.out.println("\nThe category is updated!\n");
                } else {
                    break;
                }
            }

        }
    }


    public static void userFunc(int userId) {
        while (true) {
            ReservationDAO reservationDAO = new ReservationDAO();
            BookDAO bookDAO = new BookDAO();
            Scanner scan = new Scanner(System.in);
            int pickedBookId;
            Book pickedBook;
            User currentUser;
            String chooseOperation;
            System.out.println("\nPlease select the operation by number:\n" +
                    "1. For searching for books.\n" +
                    "2. For log out.\n");
            chooseOperation = scan.next();
            if (chooseOperation.equals("1")) {
                System.out.println("Chose a book id from the list below:\n");
                System.out.println(bookDAO.searchAvailableBooks());
                pickedBookId = scan.nextInt();
                pickedBook = BookDAO.searchById(pickedBookId);
                currentUser = UserDAO.searchById(userId);

                Reservation reservation = new Reservation(currentUser, pickedBook);
                reservationDAO.insert(reservation);
                System.out.println("Book reserved!");
            } else {
                break;
            }
        }
    }
}
