import rx.Observable;
import rx.Observable.Operator;
import rx.Observable.Transformer;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class CustomOperator {


    public static void main(String[] args) {

        List<String> list = Arrays.asList("john_1", "tom-3");
        List<String> results = new ArrayList<>();

        Observable<String> observable = Observable.from(list)
                .lift(ToCleanString.toCleanString());
        observable.subscribe(results::add);

        System.out.println("Results: " + results);


        List<Integer> integers = newArrayList();
        Observable.from(list)
                .compose(ToLength.toLength())
                .subscribe(integers::add);

        System.out.println("Results: " + integers);


    }


    public static class ToCleanString implements Operator<String, String> {

        private ToCleanString() {
            super();
        }

        public static ToCleanString toCleanString() {
            return new ToCleanString();
        }

        @Override
        public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
            return new Subscriber<String>(subscriber) {
                @Override
                public void onCompleted() {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(t);
                    }
                }

                @Override
                public void onNext(String item) {
                    if (!subscriber.isUnsubscribed()) {
                        final String result = item.replaceAll("[^A-Za-z0-9]", "");
                        subscriber.onNext(result);
                    }
                }
            };
        }
    }


    public static class ToLength implements Transformer<String, Integer> {

        private ToLength() {
            super();
        }

        public static ToLength toLength() {
            return new ToLength();
        }

        @Override
        public Observable<Integer> call(Observable<String> source) {
            return source.map(String::length);
        }
    }


}
