import rx.BackpressureOverflow;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BackPressure {

    public static void main(String[] args) {


        System.out.println("------Cold Observables------------");
        Observable.range(1, 1_000_000)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute);

        System.out.println("------Hot Observables------------");
        PublishSubject<Integer> source = PublishSubject.create();
        source.observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);

        IntStream.range(1, 1_000_000).forEach(source::onNext);

        System.out.println("------Buffering Overproducing Observable------------");
        source.buffer(1024)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute,
                        Throwable::printStackTrace,
                        System.out::println);

        System.out.println("------Batching Emitted Items------------");
        source.window(500)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute,
                        Throwable::printStackTrace,
                        System.out::println);

        System.out.println("------ Skipping Elements------------");
        source.sample(100, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute,
                        Throwable::printStackTrace,
                        System.out::println);


        System.out.println("------Handling a Filling Observable Buffer------------");
        Observable.range(1, 1_000_000)
                .onBackpressureBuffer(16, () -> {
                }, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST)
                .observeOn(Schedulers.computation())
                .subscribe(e -> {
                        },
                        Throwable::printStackTrace,
                        System.out::println);


        System.out.println("------Dropping All Overproduced Elements------------");
        Observable.range(1, 1_000_000)
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .doOnNext(ComputeFunction::compute)
                .subscribe(v -> {
                        },
                        Throwable::printStackTrace,
                        System.out::println);


    }

    public static class ComputeFunction {

        public static void compute(Integer v) {
            try {
                System.out.println("compute integer v: " + v);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void compute(List<Integer> v) {
            try {
                System.out.println("compute integer v: " + v);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void compute(Observable<Integer> v) {
            try {
                v.forEach(System.out::println);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void compute(Long v) {
            try {
                System.out.println("compute integer v: " + v);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
