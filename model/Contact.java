package model;

import exceptions.InsufficientDataException;

import java.util.HashMap;
import java.util.List;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", getFullName(), getDateOfBirth(), getPhoneNumber(), getSex());
    }

    public void parseContact(String inputData) {

        // Определяем паттерны тех или иных данных
        Pattern phoneNumberPattern = Pattern.compile("\\+?\\d+");
        Pattern dateOfBirthPattern = Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
        Pattern genderPattern = Pattern.compile("[MF]");

        //ищем по паттернам данные, удаляя уже найденное из поиска
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

        // после всего этого остаётся только ФИО
        String parsedFullName = inputData.trim();

        //проверка на "пустоты"
        List values = List.of(parsedFullName, parsedDateOfBirth, parsedPhoneNumber, parsedSex);
        validate(values);


        // Заполняем данные в контакт
        setFullName(parsedFullName);
        setDateOfBirth(parsedDateOfBirth);
        setPhoneNumber(parsedPhoneNumber);
        setSex(parsedSex);
    }

    private void validate(List inputValues){
        HashMap<String, String> data = new HashMap<>();
        List<String> keys = List.of("ФИО", "дата рождения", "номер телефона", "пол");

        for (int i = 0; i < keys.size(); i++)
            data.put(keys.get(i), inputValues.get(i).toString());

        data.forEach((k, v) -> {
           if (v.isEmpty() || v.equals("0"))
               throw new InsufficientDataException("Не хватает данных! ");
        });
    }
    public Contact(){}

}
