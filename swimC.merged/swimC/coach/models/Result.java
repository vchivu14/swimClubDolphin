package swimC.coach.models;


public class Result {
   private int id;
   private int swimmerID;
   private int disciplineID;
   private int rally;
   private double time;
   private String date;
   
   public int getID() {
      return id;
   }
   public void setID(int i) {
      this.id = i;
   }
   public int getSwimmerID() {
      return swimmerID;
   }
   public void setSwimmerID(int i) {
      this.swimmerID = i;
   }
   public int getDisciplineID() {
      return disciplineID;
   }
   public void setDisciplineID(int i) {
      this.disciplineID = i;
   }
   public int getRally() {
      return rally;
   }
   public void setRally(int i) {
      this.rally = i;
   }
   public double getTime() {
      return time;
   }
   public void setTime(double i) {
      this.time = i;
   }
   public String getDate() {
      return date;
   }
   public void setString(String s) {
      this.date = s;
   }
}