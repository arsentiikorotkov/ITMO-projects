package info.kgeorgiy.ja.korotkov.student;

import info.kgeorgiy.java.advanced.student.GroupName;
import info.kgeorgiy.java.advanced.student.Student;
import info.kgeorgiy.java.advanced.student.StudentQuery;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class StudentDB implements StudentQuery {
    private final Comparator<Student> NAME_COMPARATOR = Comparator.comparing(Student::getLastName)
            .thenComparing(Student::getFirstName).reversed()
            .thenComparing(Student::getId);
    private final Comparator<Student> ID_COMPARATOR = Student::compareTo;


    private <T, R extends Collection<T>> R getAfterMap(final List<Student> students,
                                                       final Function<Student, T> mapper,
                                                       final Collector<T, ?, R> collector) {
        return students.stream().map(mapper).collect(collector);
    }

    @Override
    public List<String> getFirstNames(List<Student> students) {
        return getAfterMap(students, Student::getFirstName, Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<String> getLastNames(List<Student> students) {
        return getAfterMap(students, Student::getLastName, Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<GroupName> getGroups(List<Student> students) {
        return getAfterMap(students, Student::getGroup, Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<String> getFullNames(List<Student> students) {
        return getAfterMap(students,
                student -> String.format("%s %s", student.getFirstName(), student.getLastName()),
                Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Set<String> getDistinctFirstNames(List<Student> students) {
        return getAfterMap(students, Student::getFirstName, Collectors.toCollection(TreeSet::new));
    }


    @Override
    public String getMaxStudentFirstName(List<Student> students) {
        return students.stream()
                .max(ID_COMPARATOR)
                .map(Student::getFirstName).orElse("");
    }


    private List<Student> sort(final Collection<Student> students, final Comparator<Student> cmp) {
        return students.stream().sorted(cmp).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Student> sortStudentsById(Collection<Student> students) {
        return sort(students, ID_COMPARATOR);
    }

    @Override
    public List<Student> sortStudentsByName(Collection<Student> students) {
        return sort(students, NAME_COMPARATOR);
    }


    private <T> List<Student> filter(final Collection<Student> students,
                                     final Function<Student, T> getValue,
                                     final T currentValue) {
        return students.stream()
                .filter(student -> Objects.equals(getValue.apply(student), currentValue))
                .sorted(NAME_COMPARATOR)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Student> findStudentsByFirstName(Collection<Student> students, String name) {
        return filter(students, Student::getFirstName, name);
    }

    @Override
    public List<Student> findStudentsByLastName(Collection<Student> students, String name) {
        return filter(students, Student::getLastName, name);
    }

    @Override
    public List<Student> findStudentsByGroup(Collection<Student> students, GroupName group) {
        return filter(students, Student::getGroup, group);
    }


    @Override
    public Map<String, String> findStudentNamesByGroup(Collection<Student> students, GroupName group) {
        return students.stream().filter(student -> Objects.equals(student.getGroup(), group))
                .collect(Collectors.toMap(Student::getLastName, Student::getFirstName,
                        BinaryOperator.minBy(String::compareTo)));
    }
}
