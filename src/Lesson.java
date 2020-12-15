import java.time.LocalTime;

public class Lesson {
    public LocalTime lessonStart;
    public LocalTime lessonEnd;
    public String lessonName;


    public Lesson(LocalTime startOfLesson, LocalTime endOfLesson, String lessonName)
    {
        this.lessonStart = startOfLesson;
        this.lessonEnd = endOfLesson;
        this.lessonName = lessonName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public LocalTime getLessonStart() {
        return lessonStart;
    }

    public LocalTime getLessonEnd() {
        return lessonEnd;
    }
}
