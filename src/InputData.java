import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class InputData implements Inputable,Writable{
    private final People people;
    public InputData() {
        this.people = new People();
    }
    @Override
    public void input() throws RuntimeException {
        while(true) {
            System.out.println("Введите данные через пробел в любом порядке. Они должны содеражть Фамилию, Имя, " +
                    "Отчество, дату рождения (в формате дд/мм/гггг), номер мобильного телефона (11 чисел) и " +
                    "пол (f/m): ");
            try {
                Scanner scanner = new Scanner(System.in);
                String inputDataFromUser = scanner.nextLine();
                if(inputDataFromUser.split(" ").length != 6) {
                    throw new RuntimeException("Введено неверное количество данных!");
                }
                people.setSex(checkGender(inputDataFromUser));
                people.setPatronymic(checkPatronymic(inputDataFromUser));
                people.setSurname(checkSurname(inputDataFromUser));
                people.setPhoneNumber(checkPhoneNumber(inputDataFromUser));
                people.setDateOfBirth(checkDateOfBirth(inputDataFromUser));
                people.setName(checkName(inputDataFromUser));
                write(people.getSurname());
                scanner.close();
                return;
            } catch (RuntimeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void write(String name) throws RuntimeException, IOException {
        FileWriter writer = new FileWriter(name, true);
        writer.write(people.toString() + "\n");
        writer.close();
        System.out.println("Запись в файл завершена!");
    }
    private Gender checkGender(String data) {
        int count = 0;
        String genderinput = null;
        for (String i : data.split(" ")) {
            if (i.length() == 1 && (i.equals("m") || i.equals("f"))) {
                count++;
                genderinput = i;
            }
        }
        if (count == 1) {
            if( genderinput.equals("m")) {
                return Gender.m;
            } else {
                return Gender.f;
            }
        } else {
            throw new RuntimeException("Осутствует половая принадлежность! Либо введено несколько параметров, " +
                    "Попробуйте еще!");
        }
    }
    @Override
    public String toString() {
        return people +"";
    }
    private String checkPatronymic(String inputData) {
        String res = "";
        for (String i : inputData.split(" ")) {
            if (i.length() > 3) {
                String tmp = String.valueOf(i.charAt(i.length() - 3)) + String.valueOf(i.charAt(i.length() - 2)) +
                        String.valueOf(i.charAt(i.length() - 1));
                if (tmp.equals("вич") || tmp.equals("вна")) {
                    res = i;
                }
            }
        }
        if (res.equals("")) {
            throw new RuntimeException("Отсутствует Отчество! Попробуйте еще!");
        } else {
            return res;
        }
    }
    private String checkSurname(String inputData) {
        String res = "";
        for (String i : inputData.split(" ")) {
            if (i.length() > 3) {
                String tmp = String.valueOf(i.charAt(i.length() - 4)) + String.valueOf(i.charAt(i.length() - 3)) +
                        String.valueOf(i.charAt(i.length() - 2)) + String.valueOf(i.charAt(i.length() - 1));
                String tmp2 = String.valueOf(i.charAt(i.length() - 2)) +
                        String.valueOf(i.charAt(i.length() - 1));
                String tmp3 = String.valueOf(i.charAt(i.length() - 3)) + String.valueOf(i.charAt(i.length() - 2)) +
                        String.valueOf(i.charAt(i.length() - 1));
                if ((tmp.equals("цкий") || tmp.equals("ский") || tmp.equals("цкой") || tmp.equals("ской") ||
                tmp.equals("екая") || tmp.equals("цкая")) || (tmp3.equals("ова") || tmp3.equals("ева") ||
                        tmp3.equals("ина")) || (tmp2.equals("ов") || tmp2.equals("ев") || tmp2.equals("ин") ||
                        tmp2.equals("ын") || tmp2.equals("ой") || tmp2.equals("ая") || tmp2.equals("яя"))) {
                    res = i;
                }
            }
        }
        if (res.equals("")) {
            throw new RuntimeException("Отсутствует Фамилия! Попробуйте еще!");
        } else {
            return res;
        }
    }
    private boolean isNumeric(String data) {
        if (data == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(data);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    private Long checkPhoneNumber(String data) {
        long res = 0;
        for (String i: data.split(" ")) {
            if (isNumeric(i) && i.length() == 11) {
                res = Long.parseLong(i);
            }
        }
        if (res != 0) {
            return res;
        } else {
            throw new RuntimeException("Невенрно указан мобильный телефон! Попробуйте еще!");
        }
    }
    private boolean isDate(String str) {
        if (str == null) {
            return false;
        }
        try {
            LocalDate d = LocalDate.parse(str, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
    private String checkDateOfBirth(String str) {
        String res = "";
        LocalDate dateStop = LocalDate.now();
        LocalDate dateStart = LocalDate.of(1900,1,1);
        for (String i : str.split(" ")) {
            if (isDate(i) && LocalDate.parse(i, DateTimeFormatter.ofPattern("dd.MM.yyyy")).isBefore(dateStop)
            && LocalDate.parse(i, DateTimeFormatter.ofPattern("dd.MM.yyyy")).isAfter(dateStart)) {
                res = i;
            }
        }
        if (res.equals("")) {
            throw new RuntimeException("Отсутствует Дата рождения! Либо неправильно указана! Либо не в диапазоне " +
                    "01.01.1900 - актуальная дата! Попробуйте еще!");
        } else {
            return res;
        }
    }
    private String checkName(String inputData) {
        String res = "";
        for (String i : inputData.split(" ")) {
            if (!isNumeric(i) && !isDate(i) && i.length() > 1 && !i.equals(people.getSurname()) &&
                    !i.equals(people.getPatronymic())) {
                res = i;
            }
        }
        if (res.equals("")) {
            throw new RuntimeException("Отсутствует Имя! Попробуйте еще!");
        } else {
            return res;
        }
    }
}




