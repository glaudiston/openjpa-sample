package blog.main;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@lombok.Data
public class DateBucket {
	final Instant from;
	final Instant to;

	public static List<DateBucket> bucketizeOld(ZonedDateTime fromDate, ZonedDateTime toDate, int bucketSize, ChronoUnit bucketSizeUnit) {
		List<DateBucket> buckets = new ArrayList<>();

		boolean reachDate = false;
		for (int i = 0; !reachDate; i++) {
			ZonedDateTime minDate = fromDate.plus(i * bucketSize, bucketSizeUnit);
			ZonedDateTime maxDate = fromDate.plus(i * bucketSize, bucketSizeUnit);
			reachDate = toDate.isBefore(maxDate);
			buckets.add(new DateBucket(minDate.toInstant(), maxDate.toInstant()));
		}
		return buckets;
	}

	public static List<DateBucket> bucketize(ZonedDateTime fromDate, ZonedDateTime toDate, int bucketSize, ChronoUnit bucketSizeUnit) {
		List buckets = Stream.iterate(
				new DateBucket(
					fromDate.toInstant(), 
					fromDate.plus(bucketSize, bucketSizeUnit).toInstant() 
				),
				(DateBucket db) -> new DateBucket(db.to, db.to.plus( bucketSize, bucketSizeUnit))
			)
			.limit(bucketSizeUnit.between(fromDate, toDate) / bucketSize)
			.collect(Collectors.toList());
		return buckets;
	}
}
