import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GUI extends JFrame implements ActionListener {

    private final JLabel timeLabel = new JLabel();
    private final JLabel lessonLabel = new JLabel();
    private final JLabel timeTillLesson = new JLabel();
    private final JLabel timeTillFinish = new JLabel();

    private final DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern("mm:ss");

    public static LocalTime time = LocalTime.now();
    public Lesson[] lessons = new Lesson[9];

    String day;

    public GUI() {
        getDay();
        getContentPane().setLayout(null);
        timeLabel.setBounds(80, 60, 0, 0);
        getContentPane().add(timeLabel);

        JLabel dayToday = new JLabel("Heutiger Schultag: " + day);
        dayToday.setBounds(80, 5, 311, 23);
        getContentPane().add(dayToday);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(522, 283);

        timeLabel.setText("Zeit: " + dt.format(time));
        timeLabel.setBounds(80, 40, 192, 23);
        getContentPane().add(timeLabel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(522, 283);

        lessonLabel.setText("Aktuelle Lektion: N/A");
        lessonLabel.setBounds(80, 70, 311, 23);
        getContentPane().add(lessonLabel);

        timeTillLesson.setText("Zeit bis Lektionsende: 00:00");
        timeTillLesson.setBounds(80, 110, 311, 14);
        getContentPane().add(timeTillLesson);

        timeTillFinish.setText("Zeit bis Schulende: 00:00:00");
        timeTillFinish.setBounds(80, 144, 311, 14);
        getContentPane().add(timeTillFinish);

        new Timer(500, this).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
        SwingUtilities.invokeLater(() -> new GUI().pack());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        time = LocalTime.now();
        getDay();
        timeLabel.setText("Aktuelle Zeit: " + dt.format(time));
        if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.TUESDAY) || LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY))
            getDates();
    }

    public void GetLessonsMonday() {
        lessons[0] = new Lesson(LocalTime.of(7, 35), LocalTime.of(8, 20), "Modul 117 1");
        lessons[1] = new Lesson(LocalTime.of(8, 25), LocalTime.of(9, 10), "Modul 117 2");
        lessons[2] = new Lesson(LocalTime.of(9, 15), LocalTime.of(10, 0), "Sport");
        lessons[3] = new Lesson(LocalTime.of(11, 10), LocalTime.of(11, 55), "Modul 100 1");
        lessons[4] = new Lesson(LocalTime.of(12, 55), LocalTime.of(13, 40), "Modul 100 2");
        lessons[5] = new Lesson(LocalTime.of(13, 45), LocalTime.of(14, 30), "Modul 403 1");
        lessons[6] = new Lesson(LocalTime.of(14, 35), LocalTime.of(15, 20), "Modul 403 2");
        lessons[7] = new Lesson(LocalTime.of(15, 35), LocalTime.of(16, 20), "Modul 431 1");
        lessons[8] = new Lesson(LocalTime.of(16, 25), LocalTime.of(17, 10), "Modul 431 2");

    }

    public void GetLessonTuesday() {
        lessons[0] = new Lesson(LocalTime.of(7, 35), LocalTime.of(8, 20), "Mathematik 1");
        lessons[1] = new Lesson(LocalTime.of(8, 25), LocalTime.of(9, 10), "Mathematik 2");
        lessons[2] = new Lesson(LocalTime.of(9, 15), LocalTime.of(10, 0), "Mathematik 3");
        lessons[3] = new Lesson(LocalTime.of(10, 20), LocalTime.of(11, 05), "Chemie 1");
        lessons[4] = new Lesson(LocalTime.of(11, 10), LocalTime.of(11, 55), "Chemie 2");
        lessons[5] = new Lesson(LocalTime.of(12, 55), LocalTime.of(13, 40), "Französisch 1");
        lessons[6] = new Lesson(LocalTime.of(13, 45), LocalTime.of(14, 30), "Französisch 2");
        lessons[7] = new Lesson(LocalTime.of(14, 35), LocalTime.of(15, 20), "Englisch 1");
        lessons[8] = new Lesson(LocalTime.of(15, 35), LocalTime.of(16, 20), "Englisch 2");
    }

    public void getDates() {
        int i = 0;
        lessonLabel.setText("Keine Schule");
        if (time.isAfter(lessons[i].getLessonStart()) && time.isBefore(lessons[lessons.length - 1].getLessonEnd())) {
            while (time.isAfter(lessons[i].getLessonEnd()) && time.isBefore(lessons[lessons.length - 1].getLessonEnd()))
                i++;
            if (time.isAfter(lessons[i].getLessonStart()) && time.isBefore(lessons[i].getLessonEnd())) {
                lessonLabel.setText("Aktuelle Lektion: " + lessons[i].getLessonName() + " (" + (i + 1) + ". Lektion)");
                Duration dur = Duration.between(time, lessons[i].getLessonEnd());
                String durString = String.format("%02d:%02d",
                        dur.toMinutesPart(),
                        dur.toSecondsPart());

                timeTillLesson.setText("Zeit bis Lektionsende: " + durString);
            } else {
                lessonLabel.setText(String.format("Pause (Nächste Lektion: %s)", lessons[i].getLessonName()));
                Duration dur = Duration.between(time, lessons[i].getLessonStart());
                String durString = String.format("%02d:%02d",
                        dur.toMinutesPart(),
                        dur.toSecondsPart());
                timeTillLesson.setText("Zeit bis Pausenende: " + durString);
            }
            Duration dur = Duration.between(time, lessons[lessons.length - 1].getLessonEnd());
            if(i != lessons.length - 1) {
                String durString = String.format("%02d:%02d:%02d",
                        dur.toHours(),
                        dur.toMinutesPart(),
                        dur.toSecondsPart());
                timeTillFinish.setText("Zeit bis Schulende: " + durString);
            } else {
                timeTillFinish.setText("");
            }
        }
    }

    public void getDay() {
        if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            day = "Berufschule";
            GetLessonsMonday();

        } else if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
            day = "BMS";
            GetLessonTuesday();
        } else {
            day = "Heute keine Schule!";
        }
    }
}

