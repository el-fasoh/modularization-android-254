package com.example.designer

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
     //   assert(solution(intArrayOf(1, 1, 3), 2))
        assert(solution(intArrayOf(1, 1, 3, 3, 3), 3))
    }

    fun solution(A: IntArray, K: Int): Boolean {
        val n = A.size

        for (i in 0 until n - 1) {
            if (A[i] > A[i + 1])
                return false
        }

        A.forEachIndexed { index, i ->
            if (i == K)
                return true
        }
//        if (A[0] != 1 && A[n - 1] != K)
//            return false
//        else
//            return true

//        for (i in 0 until n - 1) {
//            if (A[i] > A[i + 1])
//                return false
//        }
//
//        var contain = false
//        for (i in 0..K) {
//            if(A[i] == K){
//                contain = true
//            }
//        }
//        return contain
return false
    }

}