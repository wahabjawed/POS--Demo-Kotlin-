package com.coolblue.pos.util

import org.junit.Assert
import org.junit.Test

class StringUtilUnitTest {

    @Test
    fun euroFormat_isCorrect() {

        Assert.assertEquals("€ 20.0", StringUtil.euroFormat(20.0))
        Assert.assertNotEquals("20.0", StringUtil.euroFormat(20.0))
    }
}