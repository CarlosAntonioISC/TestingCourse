package com.android.testing.data

import com.android.testing.examples.FeeProvider

class StubFeeProvider: FeeProvider {

    override val baseFee: Double
        get() = 5.0
    override val distanceFee: Double
        get() = 0.5
    override val weightFee: Double
        get() = 1.5

}