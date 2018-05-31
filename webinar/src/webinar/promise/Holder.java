package webinar.promise;

/**
 * Holder class for any 2 types.
 *
 * @param <T> Any type to be returned by getT()
 * @param <U> Any type to be returned by getU()
 */
public class Holder<T, U> {
	private final T t;
	private final U u;

	public Holder(T t, U u) {
		this.t = t;
		this.u = u;
	}

	public T getV() {
		return t;
	}

	public U getU() {
		return u;
	}
}