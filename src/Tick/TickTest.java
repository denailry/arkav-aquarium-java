public class TickTest {
	public class DummyTick extends Tick {
		@Override
		public void tick(double delay) {
			this.time++;
		}
	}

	@Test
	public void testTick() {
		DummyTick tick = new DummyTick();
		int nTime = 2;
		for (int i = 0; i < nTime; i++) {
			tick.tick();
		}
		assertEquals("Wrong DummyTick configuration", nTime, nTime.getTime());
	}

	@Test
	public void testReset() {
		DummyTick tick = new DummyTick();
		int nTime = 2;
		for (int i = 0; i < nTime; i++) {
			tick.tick();
		}
		tick.tickReset();
		assertEquals("Tick reset should set time to zero", 0, tick.getTime());
	}

	@Test
	public void testDiff() {
		DummyTick tick = new DummyTick();
		int difference = 10;
		for (int i = 0; i < difference; i++) {
			tick.tick();
		}
		int time = tick.getTime();
		tick.tickReset();
		assertEquals("Tick diff do not work properly", difference, tick.tickDiff(time));
	}
}