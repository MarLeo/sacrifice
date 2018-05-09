import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    private static String[] titles = {"title"};
    private static List<String> titleList = Arrays.asList(titles);

    private static Integer subscriber1 = 0;
    private static Integer subscriber2 = 0;


    static Observable<String> getTitle() {
        return Observable.from(titleList);
    }

    public static void main(String[] args) {

        final String[] result = {"", "", "", ""};

        /*Observable<String> observable = Observable.just("hello");
        observable.subscribe(s -> result[0] = s);

        System.out.println("Result: " + result[0]);*/

        Observable<String> observable1 = Observable.from(letters);
        observable1.subscribe(
                i -> result[0] += i, //OnNext
                Throwable::printStackTrace, // OnError
                () -> result[0] += "_Completed" // onCompleted

        );

        System.out.println("Result: " + result[0]);


        System.out.println();
        System.out.println("-------Map-----------");
        observable1.map(String::toUpperCase)
                .subscribe(letter -> result[1] += letter);

        System.out.println("Result: " + result[1]);


        System.out.println();
        System.out.println("-------FlatMap-----------");
        Observable.just("book1", "book2")
                .flatMap(s -> getTitle()).reduce((a, b) -> a + b)
                .subscribe(System.out::println/*letter -> result[2] += letter*/);
        System.out.println("Result: " + result[2]);


        System.out.println();
        System.out.println("--------Scan----------");
        Observable.from(letters)
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(System.out::println);

        System.out.println();
        System.out.println("------GroubBy------------");
        Observable.from(numbers)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
                .subscribe((group) -> group.subscribe((number) -> {
                    System.out.println(group.getKey() + " : " + number);
                }));

        System.out.println();
        System.out.println("-------Filter-----------");
        Observable.from(numbers)
                .filter(i -> (i % 2) == 1)
                .subscribe(System.out::print);

        System.out.println();
        System.out.println("------DefaultIfEmpty------------");
        String observable_is_empty = "Observable is empty";
        Observable.empty()
                .defaultIfEmpty(observable_is_empty)
                .subscribe(System.out::println);

        System.out.println();
        System.out.println("------DefaultIfEmpty-2-----------");
        Observable.from(letters)
                .defaultIfEmpty(observable_is_empty)
                .first()
                .subscribe(System.out::println);

        System.out.println();
        System.out.println("-------TakeWhile-----------");
        Observable.from(numbers)
                .takeWhile(i -> i < 5)
                .reduce((a, b) -> a + b)
                .subscribe(System.out::println);

        System.out.println();
        System.out.println("-------ConnectableObservable-----------");
        ConnectableObservable<Long> connectable = Observable.interval(200
                , TimeUnit.MILLISECONDS).publish();
        connectable.reduce((a, b) -> a + b)
                .subscribe(System.out::println);


        System.out.println();
        System.out.println("-------Single-----------");
        Single<String> single = Observable.just("Hello")
                .toSingle()
                .doOnSuccess(i -> result[3] += i)
                .doOnError(error -> {
                    throw new RuntimeException(error.getMessage());
                });
        single.subscribe(System.out::println);

        System.out.println();
        System.out.println("-------Subjects-----------");
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(getFirstObserver());
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(getSecondObserver());
        subject.onNext(4);
        subject.onCompleted();
        System.out.print("Subject result: " + (subscriber1 + subscriber2));


    }

    static Observer<Integer> getFirstObserver() {

        return new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Subscriber1 completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer value) {
                subscriber1 += value;
            }
        };
    }


    static Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Subscriber2 completed");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer value) {
                subscriber2 += value;
            }
        };
    }

}
