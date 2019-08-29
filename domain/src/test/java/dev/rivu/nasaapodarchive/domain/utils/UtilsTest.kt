package dev.rivu.nasaapodarchive.domain.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class UtilsTest {
    @Test
    fun `Date#daysAgo should return new instance of Date, specified days ago of the instance called on`() {
        val date = GregorianCalendar(2018, 7, 25, 0,0).time

        //assert date instance is of 2018-08-25 date
        val calendar = Calendar.getInstance()
        calendar.time = date
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(7, calendar.get(Calendar.MONTH))
        assertEquals(25, calendar.get(Calendar.DAY_OF_MONTH))

        //get new date with daysAgo
        val fiveDaysAgo = date.daysAgo(5)

        //assert date is after fiveDaysAgo
        assertTrue(date.after(fiveDaysAgo))

        //assert fiveDaysAgo instance is of 2018-08-20 date
        calendar.time = fiveDaysAgo
        assertEquals(2018, calendar.get(Calendar.YEAR))
        assertEquals(7, calendar.get(Calendar.MONTH))
        assertEquals(20, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun `Date#format() should return correct date in string format`() {
        val calendar = GregorianCalendar(2018, 7, 25, 0,0)
        calendar.timeZone = TimeZone.getTimeZone("PST")
        val date = calendar.time
        //Checking with 2018-08-25 instead of 2018-07-25, as Calendar and GregorianCalendar are 0 based in month
        assertEquals("2018-08-25", date.format())
    }

    @Test
    fun `String#parseDate() should return correct date in parsed in Date`() {
        val calendar = GregorianCalendar(2018, 7, 25, 0,0)
        calendar.timeZone = TimeZone.getTimeZone("PST")
        val date = calendar.time
        //Checking with 2018-08-25 instead of 2018-07-25, as Calendar and GregorianCalendar are 0 based in month
        assertEquals(date, "2018-08-25".parseDate())
    }
}