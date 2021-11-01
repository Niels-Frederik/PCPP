package exercises09;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;

public class ReadWords {
    public static void main(String[] args) { new ReadWords(); }

    public ReadWords(){
        readWords.subscribe(display);
    }

    final Observable<String> readWords = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
            new Thread() {
                @Override
                public void run() {
                    try {
                        String filename = "src/main/resources/english-words.txt";
                        BufferedReader reader = new BufferedReader(new FileReader(filename));
                        Stream<String> lines = reader.lines();
                        //lines.limit(100).forEach(s -> emitter.onNext(s));
                        lines.filter(x -> x.length() >= 22).forEach(s -> emitter.onNext(s));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }.start();
        }
    });

    final Observer<String> display = new Observer<String>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
        }

        @Override
        public void onNext(@NonNull String s) {
            System.out.println(s);
        }

        @Override
        public void onError(Throwable e) {System.out.println("onError: "); }

        @Override
        public void onComplete() { System.out.println("onComplete: All Done!");   }
    };
}
