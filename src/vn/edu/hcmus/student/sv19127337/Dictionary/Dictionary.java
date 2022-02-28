package vn.edu.hcmus.student.sv19127337.Dictionary;

import java.util.*;
import java.io.*;

/**
 * vn.edu.hcmus.student.sv19127337.Dictionary
 * Created by ltpbao
 * Date 12/27/2021 - 6:04 PM
 * Description: ...
 */
public class Dictionary {
    private Vector<String> slang;
    private Vector<String> meaning;

    /**
     * Constructor
     */
    public Dictionary() {
        this.slang = new Vector<>();
        this.meaning = new Vector<>();
    }

    public void setDict(Dictionary d) {
        this.slang = d.getSlang();
        this.meaning = d.getMeaning();
    }

    /**
     * getSlang() method
     * @return this.slang
     */
    public Vector<String> getSlang() {
        return slang;
    }

    /**
     * getMeaning() method
     * @return this.meaning
     */
    public Vector<String> getMeaning() {
        return meaning;
    }

    /**
     * getSize() method
     * @return this.slang.size()
     */
    public long getSize() {
        return slang.size();
    }

    /**
     * searchBySlang() method
     * @param sl String
     * @return Vector String
     */
    public Vector<String> searchBySlang(String sl) {
        Vector<String> result = new Vector<>();

        for (int i = 0; i < slang.size(); i++) {
            if (i == slang.indexOf(sl)) result.add(meaning.get(i));
        }

        if (result.size() == 0) result.add("No result");
        return result;
    }

    /**
     * searchByMeaning() method
     * @param mean String
     * @return Vector String
     */
    public Vector<String> searchByMeaning(String mean) {
        Vector<String> result = new Vector<>();

        for (int i = 0; i < meaning.size(); i++) {
            if (meaning.get(i).contains(mean)) result.add(slang.get(i));
        }

        if (result.size() == 0) result.add("No result");
        return result;
    }

    /**
     * addWord() method
     * @param sl String
     * @param mean String
     */
    public void addWord(String sl, String mean) {
        slang.add(sl);
        meaning.add(mean);
    }

    /**
     * editWord() method
     * @param sl String
     * @param newSl String
     * @param newMean String
     * @return boolean
     */
    public boolean editWord(String sl, String newSl, String newMean) {
        int ind = slang.indexOf(sl);
        if (ind < 0) return false;

        slang.set(ind, newSl);
        meaning.set(ind, newMean);
        return true;
    }

    /**
     * deleteWord() method
     * @param sl String
     * @return boolean
     */
    public boolean deleteWord(String sl) {
        int ind = slang.indexOf(sl);
        if (ind < 0) return false;

        slang.remove(ind);
        meaning.remove(ind);
        return true;
    }

    /**
     * randSlang() method
     * return random index in dictionary
     * @return int
     */
    public int randSlang() {
        Random r = new Random();

        return r.nextInt(slang.size());
    }

    /**
     * output() method
     */
    public void output() {
        for (int i = 0; i<slang.size(); i++) {
            System.out.println(slang.get(i) + "\t" + meaning.get(i) + "\n");
        }
    }

    /**
     * addMeaning() method
     * to support readFromFile() method
     * @param mean String
     * @param index int
     */
    private void addMeaning(String mean, int index) {
        meaning.set(index, meaning.get(index) + "| " + mean);
    }

    /**
     * readFromFile() method
     * @param fileName String
     * @return Dictionary Object
     * @throws IOException "Failed"
     */
    public Dictionary readFromFile(String fileName) throws IOException {
        Dictionary result = new Dictionary();
        String line;
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            while (true) {
                line = br.readLine();
                if (line == null) break;

                else {
                    if (line.indexOf('`') >= 0) {
                        String[] part = line.split("`");
                        result.addWord(part[0], part[1]);
                        count += 1;
                    }

                    else {
                        result.addMeaning(line, count-1);
                    }
                }
            }

            br.close();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failed");
        }

        return result;
    }
}
