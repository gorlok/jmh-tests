package org.sample;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Fork(1)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 8)
@Warmup(iterations = 3)
@BenchmarkMode(Mode.Throughput)
public class StringFormatBenchmark {
	private String name = "UserName";
	private String lName = "LUserName";
	private String nick = "UserNick";

	@Benchmark
	public void stringFormat(Blackhole blackhole) {
		final String result = String.format("Contact {name=%s, lastName=%s, nickName=%s}", name, lName, nick);
		blackhole.consume(result);
	}

	@Benchmark
	public void stringBuilder(Blackhole blackhole) {
		final StringBuffer sb = new StringBuffer("Contact {");
		sb.append(", name='").append(name).append(", lastName='").append(lName).append(", nickName='").append(nick)
				.append('}');
		final String result = sb.toString();
		blackhole.consume(result);
	}

	/*
	 * public static void main(String[] args) throws RunnerException { Options opt =
	 * new OptionsBuilder() .include(StringFormatBenchmark.class.getSimpleName())
	 * .forks(1) .build(); new Runner(opt).run(); }
	 */
}