package perf;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import util.Lazy;
import util.PropertiesSingleton;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
public class LazyVsSingletonPerformance {

    Blackhole bh = new Blackhole();
    Lazy<Properties> lazyProperties = new Lazy<>();

    public static void main(String... args) throws Exception{
        Options opts = new OptionsBuilder()
                .include(LazyVsSingletonPerformance.class.getSimpleName())
                .warmupIterations(3)
                .forks(2)
                .measurementIterations(3)
                .mode(Mode.SampleTime)
                .measurementTime(TimeValue.seconds(10))
                .timeUnit(TimeUnit.NANOSECONDS)
                .build();

        new Runner(opts).run();
    }


    @Benchmark
    public void testLazy(){
        bh.consume(lazyProperties.getOrCompute(() -> new Properties()));
    }


    @Benchmark
    public void testSingleton(){
        bh.consume(PropertiesSingleton.getProperties());
    }

}
