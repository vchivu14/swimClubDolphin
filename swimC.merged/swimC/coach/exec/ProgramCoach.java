package swimC.coach.exec;

import swimC.chairman.Member;
import swimC.coach.models.*;
import swimC.coach.views.*;
import swimC.coach.controllers.*;
import java.util.*;
import java.io.*;

public class ProgramCoach {
    private static ArrayList<Swimmer> swimmers;
    private static Swimmer swimmer;
    private static ArrayList<Member> members;
    private static Member member;
    private static Discipline discipline;
    private static ArrayList<Discipline> disciplines;
    private static Competition competition;
    private static ArrayList<Competition> competitions;
    private static CompetitionList competitionList;
    private static ArrayList<CompetitionList> competitionLists;
    private static TrainingResult trainingResult;
    private static ArrayList<TrainingResult> trainingResults;
    private static CompetitionResult competitionResult;
    private static ArrayList<CompetitionResult> competitionResults;

    static Scanner x;
    private static boolean program;
    static Scanner what;

    public static void editRecord(String filepath, int swimmerID, int newDiscipline) {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        int ID = 0;
        String firstName = "";
        String lastName = "";
        String mobile = "";
        int age = 0;
        int discipline = 0;
        String coach = "";
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File(filepath));

            while (x.hasNext()) {
                ID = x.nextInt();
                firstName = x.next();
                lastName = x.next();
                mobile = x.next();
                age = x.nextInt();
                discipline = x.nextInt();
                coach = x.next();

                if (ID == swimmerID) {
                    pw.println(ID + " " + firstName + " " + lastName + " " + mobile + " " + age + " " + newDiscipline
                            + " " + coach);
                } else {
                    pw.println(ID + " " + firstName + " " + lastName + " " + mobile + " " + age + " " + discipline + " "
                            + coach);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static ArrayList<Member> getMembers() {
        return members;
    }

    public static ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public static ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public static ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public static ArrayList<CompetitionList> getCompetitionLists() {
        return competitionLists;
    }

    public static ArrayList<TrainingResult> getTrainingResultsLists() {
        return trainingResults;
    }

    public static ArrayList<CompetitionResult> getCompetitionResultsLists() {
        return competitionResults;
    }

    private static void save() {
        swimmers = getSwimmers();
        members = getMembers();
        disciplines = getDisciplines();
        competitions = getCompetitions();
        competitionLists = getCompetitionLists();
        trainingResults = getTrainingResultsLists();
        competitionResults = getCompetitionResultsLists();
    }

    private static void clear() {
        swimmers.clear();
        members.clear();
        disciplines.clear();
        competitions.clear();
        competitionLists.clear();
        trainingResults.clear();
        competitionResults.clear();
    }

    private static void exec() {
        Storage.connectD();
        swimmers = Storage.getSwimmers();
        members = Storage.getMembers();
        disciplines = Storage.getDisciplines();
        competitions = Storage.getCompetitions();
        competitionLists = Storage.getCompetitionLists();
        trainingResults = Storage.getTrainingResultsLists();
        competitionResults = Storage.getCompetitionResultsLists();
    }

    private static Swimmer retrieveSwimmerFromDatabase(int option) {
        for (int i = 0; i < swimmers.size(); i++) {
            swimmer = swimmers.get(i);
            if (swimmer.getID() == option) {
                break;
            } else {
                continue;
            }
        }
        return swimmer;
    }

    private static Competition retrieveCompetitionFromDatabase(int option) {
        for (int i = 0; i < competitions.size(); i++) {
            competition = competitions.get(i);
            if (competition.getID() == option) {
                break;
            } else {
                continue;
            }
        }
        return competition;
    }

    private static Member retrieveMemberFromDatabase(int option) {
        for (int i = 0; i < members.size(); i++) {
            member = members.get(i);
            if (member.getID() == option) {
                break;
            } else {
                continue;
            }
        }
        return member;
    }

    private static Discipline retrieveDisciplineFromDatabase(int option) {
        for (int i = 0; i < disciplines.size(); i++) {
            discipline = disciplines.get(i);
            if (discipline.getID() == option) {
                break;
            } else {
                continue;
            }
        }
        return discipline;
    }

    private static boolean verifyIDDiscipline(int option) {
        boolean status = false;
        for (int i = 0; i < disciplines.size(); i++) {
            discipline = disciplines.get(i);
            if (discipline.getID() == option) {
                status = true;
                break;
            } else {
                continue;
            }
        }
        return status;
    }

    private static boolean verifyCompetitionList(int competitionID) {
        boolean status = false;
        for (int i = 0; i < competitionLists.size(); i++) {
            competitionList = competitionLists.get(i);
            int competitionListID = competitionList.getCompetitionID();
            if (competitionListID == competitionID) {
                status = true;
                break;
            } else {
                continue;
            }
        }
        return status;
    }

    public static CompetitionList retrieveCompetitionList(int i) {
        competitionList = competitionLists.get(i);
        return competitionList;
    }

    private static TrainingResult retrieveTrainingResult(int i) {
        trainingResult = trainingResults.get(i);
        return trainingResult;
    }

    private static CompetitionResult retrieveCompetitionResult(int i) {
        competitionResult = competitionResults.get(i);
        return competitionResult;
    }

    public static void ON() {
        program = true;
        menu();
    }

    public static void OFF() {
        program = false;
    }

    public static void showDisciplines() {
        Discipline model;
        DisciplineView view = new DisciplineView();
        DisciplineController controller;
        for (int i = 0; i < disciplines.size(); i++) {
            model = retrieveDisciplineFromDatabase(i);
            controller = new DisciplineController(model, view);
            controller.updateView();
        }
    }

    public static void showMembers() {
        Member model;
        MemberView view = new MemberView();
        MemberController controller;
        for (int i = 0; i < members.size(); i++) {
            model = retrieveMemberFromDatabase(i);
            controller = new MemberController(model, view);
            controller.updateView();
        }
    }

    public static void showCompetitions() {
        boolean start = false;
        while (!start) {
            Competition model;
            CompetitionView view = new CompetitionView();
            CompetitionController controllerC;
            for (int i = 0; i < competitions.size(); i++) {
                model = retrieveCompetitionFromDatabase(i);
                controllerC = new CompetitionController(model, view);
                controllerC.updateView();
            }
        }
    }

    private static String filepath = "swimmers.txt";

    public static void saveChages(String filepath, SwimmerController controller, int disciplineID) {
        String optionSave = CoachView.saveChanges();
        if (optionSave.toLowerCase().equals("y")) {
            editRecord(filepath, controller.getSwimmerID(), disciplineID);
            System.out.println("Changes saved!");
        } else if (optionSave.toLowerCase().equals("n")) {
            clear();
            exec();
            System.out.println("Changes reverted!");
        } else {
            clear();
            exec();
            System.out.println("Not a real Option!");
        }
    }

    public static void manageSwimmer() {
        boolean finding = false;
        while (!finding) {
            Swimmer model;
            SwimmerView view = new SwimmerView();
            SwimmerController controller;
            for (int i = 0; i < swimmers.size(); i++) {
                model = retrieveSwimmerFromDatabase(i);
                controller = new SwimmerController(model, view);
                controller.updateView();
            }
            int swimmerID = CoachView.chooseSwimmer();
            if (swimmers.size() > 0) {
                if (swimmerID == 0) {
                    System.out.println();
                    System.out.println("Now going back...");
                    break;
                } else if (swimmerID <= swimmers.size()) {
                    try {
                        model = retrieveSwimmerFromDatabase(swimmerID);
                        controller = new SwimmerController(model, view);
                        controller.updateView();
                    } catch (Exception e) {
                        System.out.println("No Swimmer by that ID..");
                        break;
                    }
                    
                    int disciplineID; 
                    boolean change = false;
                    while (!change) {
                        showDisciplines();
                        disciplineID = CoachView.chooseDiscipline();
                        if (disciplineID == 0) {
                           System.out.println();
                           System.out.println("Now going back...");
                           break;
                        }
                        else if (disciplineID <= disciplines.size() && verifyIDDiscipline(disciplineID)) {
                           controller.setSwimmerDiscipline(disciplineID);
                           controller.updateView();
                        }
                        else {
                           System.out.println("No Discipline found!");
                           break;
                        }

                        saveChages(filepath, controller, disciplineID);
                        change = true;
                        
                        if (change) {
                            continue;
                        } else {
                            System.out.println("Not Found...");
                        }
                        break;
                    }
                    System.out.println("Try with other Swimmer?");
                    System.out.println("<y> for Yes, <n> for No");
                    what = new Scanner(System.in);
                    if (what.next().toLowerCase().equals("y")) {
                        save();
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("No Swimmer by that ID!");
                    break;
                }
            } else {
                System.out.println("No swimmers here!");
                break;
            }
        }
    }

    public static void manageCompetitions() {
        boolean start = false;
        while (!start) {
            Competition model;
            CompetitionView view = new CompetitionView();
            CompetitionController controllerC;
            for (int i = 0; i < competitions.size(); i++) {
                model = retrieveCompetitionFromDatabase(i);
                controllerC = new CompetitionController(model, view);
                controllerC.updateView();
            }

            if (disciplines.size() > 0) {
                int option = CoachView.chooseCompetition();

                if (option == 0) {
                    System.out.println();
                    break;
                } else if (option <= competitions.size()) {

                    try {
                        model = retrieveCompetitionFromDatabase(option);
                        controllerC = new CompetitionController(model, view);
                        controllerC.updateView();
                    } catch (Exception e) {
                        System.out.println("No Competition by that ID..");
                        break;
                    }

                    boolean foundCompetitionList = false;
                    while (!foundCompetitionList) {
                        CompetitionList modelCL;
                        CompetitionListView viewCL = new CompetitionListView();
                        CompetitionListController controllerCL;
                        for (int i = 0; i < competitionLists.size(); i++) {
                            modelCL = retrieveCompetitionList(i);
                            controllerCL = new CompetitionListController(modelCL, viewCL);
                            if (controllerCL.getCompetitionID() == controllerC.getCompetitionID()) {
                                controllerCL = new CompetitionListController(modelCL, viewCL);
                                controllerCL.updateView();
                                foundCompetitionList = true;
                            } else {
                                continue;
                            }
                        }
                        if (foundCompetitionList) {
                            continue;
                        } else {
                            System.out.println("No Competition List Found...");
                        }
                        break;
                    }
                    System.out.println("Try with other competition?");
                    System.out.println("<y> for Yes, <n> for No");
                    what = new Scanner(System.in);
                    if (what.next().toLowerCase().equals("y")) {
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("No competitions found!");
                    break;
                }
            }
            else {
               System.out.println("No competitions here!");
               break;
            }
        }
    }

    public static void showTrainingResults() {
        TrainingResult model;
        TrainingView view = new TrainingView();
        TrainingController controller;
        for (int i = 0; i < trainingResults.size(); i++) {
            model = retrieveTrainingResult(i);
            controller = new TrainingController(model, view);
            controller.updateView();
        }
    }

    public static void showCompetitionResults() {
        CompetitionResult model;
        CompetitionResultView view = new CompetitionResultView();
        CResultController controller;
        for (int i = 0; i < competitionResults.size(); i++) {
            model = retrieveCompetitionResult(i);
            controller = new CResultController(model, view);
            controller.updateView();
        }
    }

    public static void menu() {
        exec();

        while (program) {
            int option = CoachView.startMenu();

            if (option == 1) {
                showMembers();
            }

            else if (option == 2) {
                manageSwimmer();
            }

            else if (option == 3) {
                showDisciplines();
            }

            else if (option == 4) {
                manageCompetitions();
            }

            else if (option == 5) {
                showTrainingResults();
            }

            else if (option == 6) {
                showCompetitionResults();
            }

            else if (option == 0) {
                System.out.println();
                System.out.println("Goodbye");
                break;
            } else {
                System.out.println("No selection made");
                continue;
            }
        }

    }
}
