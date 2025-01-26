import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile(String csvFile) {
        StdIn.setFile(csvFile);
        StdIn.readLine(); // Skip header line
         
        while (!StdIn.isEmpty()) {
        String[] recordEntries = StdIn.readLine().split(",");
         
        int year = Integer.parseInt(recordEntries[0]);
        String description = recordEntries[2];
        String gender = recordEntries[52];
        String race = recordEntries[66];
        String location = recordEntries[71];
        boolean arrested = recordEntries[13].equals("Y");
        boolean frisked = recordEntries[16].equals("Y");
         
        SFRecord Record = new SFRecord(description, arrested, frisked, gender, race, location);
         
        boolean yearExists = false;
         
         
        for (SFYear year2 : database) {
        if (year2.getcurrentYear() == year) {
        year2.addRecord(Record);
        yearExists = true;
        break;
        }
        }
         
        if (!yearExists) {
        SFYear newYear = new SFYear(year);
        newYear.addRecord(Record);
        database.add(newYear);
        }
        }
        }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

     public ArrayList<SFRecord> populationStopped(int year, String race) {
        ArrayList<SFRecord> stoppedRecords = new ArrayList<>();
         
        for (SFYear y2 : database) {
        if (y2.getcurrentYear() == year) {
        for (SFRecord rec : y2.getRecordsForYear()) {
        if (rec.getRace().equalsIgnoreCase(race)) {
        stoppedRecords.add(rec);
        }
        }
        break;
        }
        }
        return stoppedRecords;
        }
         

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested(int year) {
        int fC = 0, aC = 0, tR = 0;
         
        for (SFYear yearEntry : database) {
        if (yearEntry.getcurrentYear() == year) {
        ArrayList<SFRecord> recordsForYear = yearEntry.getRecordsForYear();
        tR = recordsForYear.size();
         
        for (SFRecord record : recordsForYear) {
        if (record.getFrisked()) {
        fC++;
        }
        if (record.getArrested()) {
        aC++;
        }
        }
        break;
        }
        }
         
        double friskedPercentage = (tR != 0) ? (fC * 100.0) / tR : 0;
        double arrestedPercentage = (tR != 0) ? (aC * 100.0) / tR : 0;
         
        double[] output = {friskedPercentage, arrestedPercentage};
        return output;
        }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias(int year) {
        double blackCount = 0, whiteCount = 0, blackMaleCount = 0, blackFemaleCount = 0, whiteMaleCount = 0, whiteFemaleCount = 0;
        
        for (SFYear yearEntry : database) {
        if (yearEntry.getcurrentYear() == year) {
        ArrayList<SFRecord> recordsForYear = yearEntry.getRecordsForYear();
         
        for (SFRecord record : recordsForYear) {
        String race = record.getRace();
        String gender = record.getGender();
         
        if (race.equalsIgnoreCase("B")) {
        blackCount++;
        if (gender.equalsIgnoreCase("F")) {
        blackFemaleCount++;
        } else if (gender.equalsIgnoreCase("M")) {
        blackMaleCount++;
        }
        } else if (race.equalsIgnoreCase("W")) {
        whiteCount++;
        if (gender.equalsIgnoreCase("F")) {
        whiteFemaleCount++;
        } else if (gender.equalsIgnoreCase("M")) {
        whiteMaleCount++;
        }
        }
        }
        break;
        }
        }
         
        double blackFemalePercentage = (blackCount != 0) ? (blackFemaleCount / blackCount) * 0.5 * 100 : 0;
        double blackMalePercentage = (blackCount != 0) ? (blackMaleCount / blackCount) * 0.5 * 100 : 0;
        double whiteFemalePercentage = (whiteCount != 0) ? (whiteFemaleCount / whiteCount) * 0.5 * 100 : 0;
        double whiteMalePercentage = (whiteCount != 0) ? (whiteMaleCount / whiteCount) * 0.5 * 100 : 0;
         
        double totalFemalePercentange = blackFemalePercentage + whiteFemalePercentage;
        double totalMalePercentange = blackMalePercentage + whiteMalePercentage;
         
        double[][] genderBiasArray = {
        {blackFemalePercentage, whiteFemalePercentage, totalFemalePercentange},
        {blackMalePercentage, whiteMalePercentage, totalMalePercentange}
        };
         
        return genderBiasArray;
        }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

     public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        int crimeOne = 0;
        int crimeTwo = 0;
        int crimeOneCount = 0;
        int crimeTwoCount = 0;
         
        for (SFYear yearEntry : database) {
        if (yearEntry.getcurrentYear() == year1) {
        ArrayList<SFRecord> recordsYear1 = yearEntry.getRecordsForYear();
         
        for (SFRecord record : recordsYear1) {
        if (record.getDescription().toUpperCase().contains(crimeDescription.toUpperCase())) {
        crimeOne++;
        }
        crimeOneCount++;
        }
        }
        if (yearEntry.getcurrentYear() == year2) {
        ArrayList<SFRecord> recordsYear2 = yearEntry.getRecordsForYear();
         
        for (SFRecord record : recordsYear2) {
        if (record.getDescription().toUpperCase().contains(crimeDescription.toUpperCase())) {
        crimeTwo++;
        }
        crimeTwoCount++;
        }
        }
        }
         
        double yearOnePercentage = (crimeOneCount != 0) ? ((double) crimeOne / crimeOneCount) * 100 : 0;
        double yearTwoPercentage = (crimeTwoCount != 0) ? ((double) crimeTwo / crimeTwoCount) * 100 : 0;
         
        return yearTwoPercentage - yearOnePercentage;
        }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough(int year) {
        String[] boroughs = {"Brooklyn", "Manhattan", "Bronx", "Queens", "Staten Island"};
        int[] count = new int[boroughs.length];
        int maxValueIndex = 0;
    
        boolean yearFound = false;
    
        for (SFYear yearEntry : database) {
            if (yearEntry.getcurrentYear() == year) {
                yearFound = true;
                ArrayList<SFRecord> recordsForYear = yearEntry.getRecordsForYear();
    
                for (SFRecord record : recordsForYear) {
                    String borough = record.getLocation().toUpperCase();
                    for (int i = 0; i < boroughs.length; i++) {
                        if (borough.equals(boroughs[i].toUpperCase())) {
                            count[i]++;
                            if (count[i] > count[maxValueIndex]) {
                                maxValueIndex = i;
                            }
                            break;
                        }
                    }
                }
                break;
            }
        }
    
        if (!yearFound) {
            // Handle the case where the specified year is not found
            // You might want to throw an exception or return a default value
        }
    
        return boroughs[maxValueIndex];
    }
        
}
