package com.gan.data.transformer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SummaryItem {
		long confirmedCount;
		long recoveredCount;

		public SummaryItem(long confirmedCount, long recoveredCount) {
			this.confirmedCount += confirmedCount;
			this.recoveredCount += recoveredCount;
		}

		public SummaryItem increaseConfirmationCount(long confirmedCount) {
			this.confirmedCount += confirmedCount;
			return this;
		}

		public SummaryItem increaseRecoveredCount(long recoveredCount) {
			this.recoveredCount += recoveredCount;
			return this;
		}

		public double getRecoveryRatio() {
			if(confirmedCount == 0)
				return 0.0d;
			return new BigDecimal(recoveredCount).divide(new BigDecimal(confirmedCount), 2, RoundingMode.CEILING).doubleValue();
		}

		public long getRecoveryCount() {
			return recoveredCount;
		}

		public long getConfirmedCount() {
			return confirmedCount;
		}

		@Override
		public String toString() {
			return "SummaryItem [confirmedCount=" + confirmedCount + ","
					+ " recoveredCount=" + recoveredCount  + ","
					+ "recoveryRatio=" + getRecoveryRatio() + "]";
		}
		
		
	}