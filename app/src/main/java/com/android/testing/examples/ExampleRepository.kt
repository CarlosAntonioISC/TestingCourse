package com.android.testing.examples


import com.android.testing.domain.models.Result
import com.android.testing.domain.models.ServerError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

class ExampleRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchData(): Result<String, ServerError> {
        return withContext(dispatcher) {
            delay(5.seconds)
            Result.Success("ok")
        }
    }

}