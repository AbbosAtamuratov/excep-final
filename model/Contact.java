package model;

import exceptions.InsufficientDataException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String fullName;
    private String dateOfBirth;
    private int phoneNumber;
    private String sex;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", fullName, dateOfBirth, phoneNumber, sex);
    }

    public void parseContact(String inputData) {

        Scanner sc = new Scanner(System.in);
        Pattern phoneNumberPattern = Pattern.compile("\\+?\\d+");
        Pattern dateOfBirthPattern = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
        Pattern genderPattern = Pattern.compile("[MF]");

        Matcher matcher = genderPattern.matcher(inputData);
        String parsedSex = "";
        if (matcher.find()) {
            parsedSex = matcher.group();
            inputData = matcher.replaceFirst("");
        }

        matcher = dateOfBirthPattern.matcher(inputData);
        String parsedDateOfBirth = "";
        if (matcher.find()) {
            parsedDateOfBirth = matcher.group();
            inputData = matcher.replaceFirst("");
        }

        matcher = phoneNumberPattern.matcher(inputData);
        int parsedPhoneNumber = 0;
        if (matcher.find()) {
            parsedPhoneNumber = Integer.parseInt(matcher.group());
            inputData = matcher.replaceFirst("");
        }

        String parsedFullName = inputData;
        parsedFullName = parsedFullName.trim();

        setFullName(parsedFullName);
        setDateOfBirth(parsedDateOfBirth);
        setPhoneNumber(parsedPhoneNumber);
        setSex(parsedSex);
    }

    public Contact(String fullName, String dateOfBirth, int phoneNumber, String sex) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public Contact(){}

    public void setSex(String sex) {
        this.sex = sex;
    }

}
